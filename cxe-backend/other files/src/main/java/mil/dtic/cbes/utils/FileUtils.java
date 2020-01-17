package mil.dtic.cbes.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
	
	private static Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);
	
  /**
   * This method reads the file identified by the file path and returns the
   * content as a String.
   * 
   * @param file
   *          file object to read
   */
  public static String readInputStreamAsString(InputStream inputStream) {
    StringBuilder result = new StringBuilder();
    try (Reader r = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(r)) {

        char[] buf = new char[1024];

        int numRead = reader.read(buf);
        while (numRead != -1) {
            result.append(buf, 0, numRead);
            numRead = reader.read(buf);
        }
    } catch (IOException e) {
    	LOGGER.info("Cannot read file properly!");
	}
    return result.toString();
  }

  /**
   * This method reads the file identified by the file path and returns the
   * content as a String.
   * 
   * @param file
   *          file object to read
   */
  public static String readFileAsString(File file) {
    String fileData = "";
    try (InputStream is = new FileInputStream(file)) {
        fileData = readInputStreamAsString(is);
    } catch (FileNotFoundException e) {
    	LOGGER.info("File not found!");
	} catch (IOException e) {
		LOGGER.info("Something's wrong with the file!");
	}

    return fileData;
  }

  /**
   * This method reads the file identified by the file path and returns the
   * content as a String.
   * 
   * @param filePath
   *          file path to a file
   */
  public static String readFileAsString(String filePath)
      throws java.io.IOException {
    return readFileAsString(new File(filePath));
  }

  public static String uniqueFileName(String directory, String filename) {
      String base = directory + File.separator + filename;
      String newFilename = base;
      int counter = 0;
      while (new File(newFilename).isFile()) {
          counter++;
          newFilename = base + "_" + counter;
      }

      return newFilename;
  }
}

