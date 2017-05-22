package org.deloitte.eventScheduler.fileOperations.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.deloitte.eventScheduler.fileOperations.EventFileWritter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
/**
 * 
 * @author Tonia
 * Test class for EventFileWritter
 */
public class EventFileWritterTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
 
    @Test
    public void testWriteFile() {
       File file = new File("src/test/resources/activities.txt");
       File expectedResultFile = new File("src/test/resources/activities_result.txt");
        StringBuilder content = new StringBuilder("Duck Herding 60min");
        if (expectedResultFile.exists()) {
            expectedResultFile.delete();
        }
        EventFileWritter.writeFile(content, file.getAbsolutePath());
        assertTrue(expectedResultFile.exists());
    }

    @Test
    public void testWriteFileWithNoFileName() {
       File expectedResultFile = new File("src/test/resources/activities_result.txt");
        StringBuilder content = new StringBuilder("Duck Herding 60min");
        if (expectedResultFile.exists()) {
            expectedResultFile.delete();
        }
        EventFileWritter.writeFile(content, "src/test/resources");
        assertTrue(!expectedResultFile.exists());
    }

    @Test
    public void testWriteFileNullContent() {
       File expectedResultFile = new File("src/test/resources/activities_result.txt");
        StringBuilder content = null;
        if (expectedResultFile.exists()) {
            expectedResultFile.delete();
        }
        EventFileWritter.writeFile(content, "src/test/resources/activities.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(expectedResultFile.getAbsolutePath()))) {
           assertTrue(br.readLine()==null);
        } catch (Exception ex) {
        
        }
    }

    @Test
    public void testWriteFileContent() {
        String currentLine;
        StringBuilder content = new StringBuilder("Duck Herding 60min");
        File expectedResultFile = new File("src/test/resources/activities_result.txt");
        File file = new File("src/test/resources/activities.txt");
        if (expectedResultFile.exists()) {
            expectedResultFile.delete();
        }
        EventFileWritter.writeFile(content, file.getAbsolutePath());
        assertTrue(expectedResultFile.exists());        
        try (BufferedReader br = new BufferedReader(new FileReader(expectedResultFile.getAbsolutePath()))) {
            while ((currentLine = br.readLine()) != null) {
                assertEquals(currentLine, content.toString());
            }
        } catch (Exception ex) {
        
        }

    }
}
