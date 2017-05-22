package org.deloitte.eventScheduler.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.deloitte.eventScheduler.service.EventService;
import org.deloitte.eventScheduler.service.EventServiceImpl;
import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Tonia
 * Test class for EventServiceImpl
 */
public class EventServiceImplTest {

    EventService service;

    @Before
    public void setUp() throws Exception {

        service = new EventServiceImpl();
    }

    @Test
    public void testResultIsCreated() {
        String fileName = "src/test/resources/activities_result.txt";
        deleteFile(fileName);
        File file = new File("src/test/resources/activities_result.txt");
        if (file.exists()) {
            file.delete();
        }
        service.getAllEvents("src/test/resources/activities.txt");
        assertTrue(file.exists());
    }

    @Test
    public void testResultforMultipleTeam() {
        String fileName = "src/test/resources/activitiesWithThreeTeams_result.txt";
        deleteFile(fileName);
        service.getAllEvents("src/test/resources/activitiesWithThreeTeams.txt");
        assertContentType(fileName, 3);
    }

    @Test
    public void testResultforSingleTeam() {
        String fileName = "src/test/resources/activitiesWithSingleTeam_result.txt";
        deleteFile(fileName);
        service.getAllEvents("src/test/resources/activitiesWithSingleTeam.txt");
        assertContentType(fileName, 1);
    }

    @Test
    public void testResultforNullFIleName() {
        String fileName = "src/test/resources/activitiesWithTwoEvents_result.txt";
        File file = new File(fileName);
        deleteFile(fileName);
        service.getAllEvents(null);
        assertFalse(file.exists());
    }

    private void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    private void assertContentType(String fileName, int expectedCount) {
        int teamCount = 0;
        int countLunchBreak = 0;
        int countStaffMotivePresentation = 0;
        String currentLine;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((currentLine = br.readLine()) != null) {
                if (currentLine.startsWith("Team ")) {
                    teamCount++;
                }
                if (currentLine.contains("Lunch Break")) {
                    countLunchBreak++;
                }
                if (currentLine.contains("Staff Motivation")) {
                    countStaffMotivePresentation++;
                }
            }
        } catch (Exception ex) {
            System.out.println("Error in testResultWithTeamCount_3");
        }
        assertEquals(expectedCount, teamCount);
        assertEquals(expectedCount, countLunchBreak);
        assertEquals(expectedCount, countStaffMotivePresentation);
    }
    
    
}
