package org.deloitte.eventScheduler.fileOperations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.deloitte.eventScheduler.model.EventActivity;

/**
 * 
 * @author Tonia
 *  This class is used to read the file from the path and convert
 *         this to a list of eventActivities.
 *
 */
public class EventFileReader {
    /**
     * This method reads the file by line and split with space to convert it
     * into words.then this word is split with min and sprint keyword. actvity
     * and minute will be set into EventActivity
     * 
     * @param fileName
     * @return List<EventActivity>
     */
    public static List<EventActivity> readFile(String fileName) {
	List<EventActivity> eventlist = new ArrayList<EventActivity>();
	String currentLine;
	if (isValidFile(fileName)) {
	    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		while ((currentLine = br.readLine()) != null) {
		    for (String word : currentLine.split("\\s")) {
			if (word != null && (word.contains("min") && Character.isDigit(word.charAt(0))
				|| word.equalsIgnoreCase("sprint"))) {
			    EventActivity eventActivity = new EventActivity();
			    eventActivity.setActivity(currentLine);			 
			    if (word.equalsIgnoreCase("sprint")) {
				eventActivity.setMinute(15);
			    } 			    
			    else {
				eventActivity.setMinute(Integer.parseInt(word.split("[a-z]")[0]));
			    }
			    eventlist.add(eventActivity);
			}
		    }
		}
	    } catch (IOException ex) {
		System.out.println(ex.getMessage());
	    }
	}
	return eventlist;
    }

    public static boolean isValidFile(String fileName) {
	if (fileName != null) {
	    File file = new File(fileName);
	    return file.exists();
	} else {
	    return false;
	}
    }

}
