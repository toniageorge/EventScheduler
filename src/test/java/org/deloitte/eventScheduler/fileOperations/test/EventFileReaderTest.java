package org.deloitte.eventScheduler.fileOperations.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.deloitte.eventScheduler.fileOperations.EventFileReader;
import org.deloitte.eventScheduler.model.EventActivity;
import org.junit.Test;

/**
 * 
 * @author Tonia test class for EventFileReader
 */
public class EventFileReaderTest {

    @Test
    public void testReadFileNotEmpty() {
	File resourcesDirectory = new File("src/test/resources/activities.txt");
	List<EventActivity> eventlist = EventFileReader.readFile(resourcesDirectory.getAbsolutePath());
	assertTrue(!eventlist.isEmpty());
    }

    @Test
    public void testReadFileWithoutFileName() {
	File resourcesDirectory = new File("src/test/resources/");
	List<EventActivity> eventlist = EventFileReader.readFile(resourcesDirectory.getAbsolutePath());
	assertTrue(eventlist.isEmpty());
    }

    @Test
    public void testReadFileSize() {
	File resourcesDirectory = new File("src/test/resources/activities.txt");
	int filesize = 20;
	List<EventActivity> eventlist = EventFileReader.readFile(resourcesDirectory.getAbsolutePath());
	assertEquals(eventlist.size(), filesize);
    }

    @Test
    public void testReadFileContent() {
	File resourcesDirectory = new File("src/test/resources/event.txt");
	String content = "Duck Herding 60min";
	int minute = 60;
	List<EventActivity> eventlist = EventFileReader.readFile(resourcesDirectory.getAbsolutePath());
	for (EventActivity activity : eventlist) {
	    assertEquals(activity.getActivity(), content);
	    assertEquals(activity.getMinute(), minute);
	}
    }

    @Test
    public void testReadFileWithoutMinuteOrsprint() {
	File resourcesDirectory = new File("src/test/resources/activitiesWithoutSprintAndminute.txt");
	List<EventActivity> eventlist = EventFileReader.readFile(resourcesDirectory.getAbsolutePath());
	assertTrue(eventlist.isEmpty());
    }

    @Test
    public void testReadFileWithMinuteAndWithoutMinute() {
	File resourcesDirectory = new File("src/test/resources/activitiesWithMinuteAndWithoutMinute.txt");
	int filesize = 16;
	List<EventActivity> eventlist = EventFileReader.readFile(resourcesDirectory.getAbsolutePath());
	assertEquals(eventlist.size(), filesize);
    }

    @Test
    public void testForIsValidFilewithNull() {
	assertFalse(EventFileReader.isValidFile(null));
    }

    @Test
    public void testForIsValidFileWithWrongFileName() {
	assertFalse(EventFileReader.isValidFile("src/test/resources/abc.txt"));
    }

    @Test
    public void testForValidFile() {
	assertTrue(EventFileReader.isValidFile("src/test/resources/activities.txt"));
    }

}
