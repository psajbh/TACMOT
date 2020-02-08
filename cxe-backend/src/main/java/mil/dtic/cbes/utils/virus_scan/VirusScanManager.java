/*
 * @(#) $Id: VirusScanManager.java,v 1.5 2008/04/28 20:17:17 beresh Exp $
 * 
 * NEEDS TO BE COMMENTED OUT FOR DEPLOYMENT V 2.1
 */
package mil.dtic.cbes.utils.virus_scan;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mil.dtic.cbes.utils.FileUtils;
import mil.dtic.cbes.utils.WhiteLister;
import mil.dtic.vscan.PartitionException;
import mil.dtic.vscan.SizeExceededException;
import mil.dtic.vscan.VirusException;

/**
 * The VirusScanManager class is responsible for integrating with DTIC's VirusScan library to scan files for viruses.
 */
@Service
public class VirusScanManager {
	protected String sourceDirectory="/sandbox/cxe/source";
	protected String targetDirectory="/sandbox/cxe/target";
	protected String applicationDirectory="/usr/local/uvscan/uvscan";
	@Value("${virus.scan.enabled:false}")
    protected boolean virusScannerEnabled=true; // Only used for local development where virus scan does not exist 
	
	private static final Logger logger = LoggerFactory.getLogger(VirusScanManager.class);

	/**
	 * Sets the source file directory to be used with the DTIC virus scan interface.
	 * 
	 * @param sourceDirectory directory to write source files to
	 */
	public void setSourceDirectory( String sourceDirectory ) {
		this.sourceDirectory = sourceDirectory;
	}
	
	/**
	 * Sets the target file directory to be used with the DTIC virus scan interface.
	 * 
	 * @param sourceDirectory directory the virus scan utility will write files to
	 */
	public void setTargetDirectory( String targetDirectory ) {
		this.targetDirectory = targetDirectory;
	}
	
	/**
	 * Sets the directory where the virus scan application actually resides on the file system.
	 * 
	 * @param sourceDirectory directory to write source files to
	 */
	public void setApplicationDirectory( String applicationDirectory ) {
		this.applicationDirectory = applicationDirectory;
	}
	
	/**
	 * Scans the file for a virus.
	 * 
	 * @param file
	 */
	public void scanFile(MultipartFile file) throws VirusScanException, SizeExceededException, FileNotFoundException, PartitionException, RuntimeException, IOException {
		try (InputStream inputStream = file.getInputStream()) {
			byte[] bytes = IOUtils.toByteArray(inputStream);
			String uuidStr = UUID.randomUUID().toString();
			scanFile(bytes, uuidStr);
		}
	}
	
	/**
	 * Scans the byte[] array representing a file for a virus.
	 * 
	 * @param fileBytes the byte[] array representing a file
	 * @param filename the name of the file to written/scanned
	 */
	public void scanFile( byte[] fileBytes, String filename ) throws VirusScanException, SizeExceededException, FileNotFoundException, PartitionException {
		logger.debug("Scanning: " + filename);
		
		if( !virusScannerEnabled ) {
            logger.info("VIRUS SCANNER DISABLED - ONLY SHOULD HAPPEN ON LOCAL DEV MACHINES, Skipping scan");
			return;
		}

		String localFilename = WhiteLister.safetext(filename);
		File sourceFile = getSourceFile(fileBytes, localFilename);
		File targetFile = getTargetFile(localFilename);
		
		VirusScanUtilityFileAccess vsu = null;
		try {
//			if (true) {
//				throw new VirusException("testing VirusException");
//			}
			
			// CALL THE VIRUS SCANNER
			vsu = new VirusScanUtilityFileAccess();
			
			// optional, default is /opt/local/admin/uvscan/uvscan
			vsu.setPathToVirusScanner( applicationDirectory );
			
			// optional, default is
			// --secure Examine all files, unzip archive files, etc.
			// "--summary Gives a summary of the scan.
			// "--fam Gives the family the virus belonged to.
			// "--delete Deletes the file if virus is found.
			//
			vsu.setCommands( new String[] {
			"--secure", "--summary", "--fam", "--delete"
			} );
			vsu.scanFile( sourceFile, targetFile, false); // set delete to false and delete it here, because it's not working in the virus scanner
		} catch( SizeExceededException see ) {
			// TEMP FILE IS NOT DELETED IF IT THROWS A SIZE EXCEPTION
			logger.warn( "VirusScanManager - size exceeded exception", see );
			throw new SizeExceededException( "The file exceeded the max size amount allowed to be uploaded" );
		} catch( PartitionException pe ) {
			// TEMP FILE IS NOT DELETED IF IT THROWS A PARTITION EXCEPTION
			logger.warn( "VirusScanManager - partition exception");
			throw new PartitionException( "There was a internal problem scanning the file.  Please try again later" );
		} catch( RuntimeException re ) {
			// TEMP FILE IS NOT DELETED IF IT THROWS A RUNTIME EXCEPTION
			logger.warn( "VirusScanManager - runtime exception", re );
			throw new RuntimeException( re );
		} catch( VirusException ve ) {
			// TEMP FILE IS DELETED IF IT THROWS A VIRUS EXCEPTION
			logger.warn( "VirusScanManager - virus exception");
			throw new VirusScanException( ve );
		} catch( FileNotFoundException pe ) {
			// TEMP FILE IS NOT DELETED IF IT THROWS A PARTITION EXCEPTION
			logger.warn( "VirusScanManager - file not found exception", pe );
			throw new FileNotFoundException( "There was a problem uploading the file" );
		} catch( Exception ex ) {
			// TEMP FILE MIGHT BE DELETED IF IT THROWS ANY OTHER EXCEPTION
			logger.warn( "VirusScanManager - unknown exception");
			throw new VirusScanException( ex );
		} catch (Throwable e) {
			logger.warn( "VirusScanManager - unknown exception");
		}
		finally{
			if (!targetFile.delete()) {
			    logger.error( "There was an error deleting the target file after virus scanning: " + localFilename);
			}
			if (!sourceFile.delete()) {
			    logger.error( "There was an error deleting the source file after virus scanning: " + localFilename);
			}
		}
	}
	
	/**
	 * This method takes the byte[] array parameter, writes out a file for the given filename and returns a File object.
	 * 
	 * @param fileBytes the byte[] array representing a file
	 * @param filename the name of the file to written
	 */
	protected File getSourceFile( byte[] fileBytes, String filename ) throws VirusScanException {
		String sourceFilename = FileUtils.uniqueFileName(sourceDirectory, filename);
		File sourceFile = new File( sourceFilename );
		try (ByteArrayInputStream in = new ByteArrayInputStream( fileBytes );
				FileOutputStream fos = new FileOutputStream( sourceFile )) 
		{
			int c;
			while ((c = in.read()) != -1) {
				fos.write(c);
			}
			fos.flush();
		} catch( FileNotFoundException fnfe ) {
			logger.error( "VirusScanManager.getSourceFile file not found");
			throw new VirusScanException( fnfe );
		} catch( IOException ioe ) {
			logger.error( "VirusScanManager.getSourceFile IO Problem");
			throw new VirusScanException( ioe );
		}
		return sourceFile;
	}
	
	/**
	 * This method returns a File object for the target filename.
	 * 
	 * @param filename the target filename
	 */
	protected File getTargetFile( String filename ) {
		String targetFilename = FileUtils.uniqueFileName(targetDirectory, filename);
		return new File( targetFilename );
	}

    public void setVirusScannerEnabledSetting(boolean disabled) {
        if (disabled) {
            logger.info("VIRUS SCANNER DISABLED - ONLY SHOULD HAPPEN ON LOCAL DEV MACHINES");
            virusScannerEnabled = false;
        } else {
            // logger.info("VIRUS SCANNER ENABLED");
        }
    }

    public boolean getVirusScannerEnabledSetting() {
        return true;
    }
    

}

