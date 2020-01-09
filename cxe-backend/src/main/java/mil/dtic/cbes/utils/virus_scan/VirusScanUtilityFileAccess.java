/*
 * $Id: VirusScanUtility.java,v 1.5 2008/09/09 18:35:05 sminogue Exp $
 * Vscan.java Created on Sep 22, 2004
 */
package mil.dtic.cbes.utils.virus_scan;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

//import mil.dtic.vscan.VirusScanUtility;

/**
 * File Scan Utility to scan files.  Executed via code.
 * <pre>
 * Note using scanFile(File source, File target):
 * 1.  Target gets created from the source.
 * 2.  Source file is automatically deleted upon a successful scan.
 * 
 * Note using scanFile(File source, File target, boolean deleteScanFile):
 * 1.  Target gets created from the source.
 * 2.  Source file is deleted upon a successful scan and deleteScanFile == true.
 * 
 * To execute via code:
  <code>
  	File toBeFile = new File("/permanent/file/home/vsuTestFile.txt");
   String test = "This is to test the virus scanner";
   VirusScanUtility vsu = null;
   try {
  	//TEMP FILE MUST EXIST ON FILE SYSTEM
  	writeFile(tempFile, test); //THIS WOULD EXIST IN YOUR CODE
  	
  	//CALL THE VIRUS SCANNER
  	vsu = new VirusScanUtility();
  	// optional, max/default is 100MB 
  	vsu.setFileSize(VirusScanUtility.KILOBYTE * 100); //100KB
  	
  	// optional, default is 90 
  	vsu.setMaxPercentCapacity(80); //80%
  	
  	// optional, default is /opt/local/admin/uvscan/uvscan 
  	vsu.setPathToVirusScanner("/new/path/to/uvscan/");
  	
  	// optional, default is 
  	// --secure 	Examine all files, unzip archive files, etc.
  	// "--summary 	Gives a summary of the scan.
  	// "--fam 		Gives the family the virus belonged to.
  	// "--delete 	Deletes the file if virus is found.
  	//
  	vsu.setCommands(new String[]{"--secure", "--summary", "--fam", "--delete"});
  	vsu.scanFile(tempFile, toBeFile);
   } catch(SizeExceededException see) {
  	//TEMP FILE IS NOT DELETED IF IT THROWS A SIZE EXCEPTION
  	System.out.println("There was a size exception.");
  	see.printStackTrace();
  	
   } catch(PartitionException pe) { 
  	//TEMP FILE IS NOT DELETED IF IT THROWS A PARTITION EXCEPTION
  	System.out.println("There was a partition exception.");
  	pe.printStackTrace();
  	
   } catch (RuntimeException re) {
  	//TEMP FILE IS NOT DELETED IF IT THROWS A RUNTIME EXCEPTION
  	System.out.println("There was a runtime exception.");
  	re.printStackTrace();
  	
   } catch (VirusException ve) {
  	//TEMP FILE IS DELETED IF IT THROWS A VIRUS EXCEPTION
  	System.out.println("There was a virus exception.");
  	ve.printStackTrace();
  	
   } catch (Exception ex) {
  	System.out.println("There was an exception.");
  	//TEMP FILE MIGHT BE DELETED IF IT THROWS ANY OTHER EXCEPTION
  	ex.printStackTrace();
   }
  </code>
 * </pre>
 *
 * @author jpeabody
 */
public class VirusScanUtilityFileAccess /* extends VirusScanUtility */ {
	
    public VirusScanUtilityFileAccess(String path) throws Throwable {
    	//super(path);
    }

    /**
     * Creates a new VirusScanUtility object.
     * @throws Exception 
    
     */
    public VirusScanUtilityFileAccess() throws Throwable{
    	super();
    }

    /**
     * Override to call the Java methods for file access
     *  
     * @param path path to the directory where to perform the 'df' command
     *
     * @return int value.
     */
    public int getPartitionCapacity(final String path) {
    	File file = new File(path);
    	if (file.getTotalSpace() == 0) {
    		return 0;
    	}
        return 100 - ((int) (100 * file.getFreeSpace() / file.getTotalSpace()));
    }

    
    /**
     * Override to call the Java methods for file access
     *
     * @param path path the to the directory where to perform the 'df' command
     *
     * @return int value in kbytes.
     */
    public long getPartitionAvail(final String path) {
    	File file = new File(path);
        return file.getFreeSpace();
    }

    /**
     * Override to call the Java methods for file access
     *
     * @param path path to the directory where to perform the 'df' command
     *
     * @return long value
     */
    public long getPartitionKBytes(final String path) {
    	File file = new File(path);
        return file.getTotalSpace() / 1000;
    }

    /**
     * Override to call the Java methods for file access
     *  
     * @param path path to the directory where to perform the 'df' command
     *
     * @return int value.
     */
    public long getPartitionUsed(final String path) {
    	File file = new File(path);
    	return file.getTotalSpace() - file.getFreeSpace();
    }
}

