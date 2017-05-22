package org.deloitte.eventScheduler.fileOperations;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author Tonia
 * 
 *         This class is to write the content to a txt file
 */
public class EventFileWritter {
    /**
     * This method gets the file path and create a new output file with the
     * 'input file name' + _result.txt file in the same folder the input file.
     * 
     * @param content
     * @param fileName
     * 
     */
    public static void writeFile(StringBuilder content, String fileName) {
	Path path = Paths.get(fileName);
	Path destinationPath = path.getParent();
	String destinationFilename = path.getFileName().toString();
	if (destinationFilename != null && destinationFilename.contains(".txt")) {
	    destinationFilename = destinationFilename.substring(0, destinationFilename.indexOf('.'));
	    try (BufferedWriter bw = new BufferedWriter(
		    new FileWriter(destinationPath + "\\" + destinationFilename + "_result.txt"))) {
		bw.write(content.toString());
		System.out.println(
			"output  file is created in " + destinationPath + "\\" + destinationFilename + "_result.txt");
	    } catch (IOException e) {
		System.out.println("an error occured while writing the output and the error is" + e.getMessage());
	    }
	    catch (Exception e) {
		System.out.println("an error occured while writing the output and the error is" + e.getMessage());
	    }
	}
    }
}
