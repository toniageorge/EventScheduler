package org.deloitte.eventScheduler.utility.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.deloitte.eventScheduler.model.EventTime;
import org.deloitte.eventScheduler.utility.EventUtility;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Tonia Test class for EventUtility
 */
public class EventUtilityTest {

    EventTime time;

    @Before
    public void setUp() throws Exception {
	time = new EventTime();
    }

    @Test
    public void testisAmorPmForAm() {
	int hour = 9;
	String expectedValue = "am";
	String actualValue = EventUtility.checkAmorPm(hour);
	assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testisAmorPmForPm() {
	int hour = 13;
	String expectedValue = "pm";
	String actualValue = EventUtility.checkAmorPm(hour);
	assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testIsLunchBreakTrue() {
	int hour = 11;
	int minute = 60;
	assertTrue(EventUtility.isLunchBreak(hour, minute));
    }

    @Test
    public void testIsLunchBreakFalse() {
	int hour = 10;
	int minute = 60;
	assertFalse(EventUtility.isLunchBreak(hour, minute));
    }

    @Test
    public void testisSessionTimeTrue() {
	int hour = 16;
	int minute = 60;
	assertTrue(EventUtility.isPresenationTime(hour, minute));
    }

    @Test
    public void testisSessionTimeFalse() {
	int hour = 14;
	int minute = 60;
	assertFalse(EventUtility.isPresenationTime(hour, minute));
    }

    @Test
    public void testPrintwithTwoParams() {
	final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	System.setOut(new PrintStream(outContent));
	StringBuilder content = new StringBuilder("");
	EventUtility.print(content, "Team -1");
	assertEquals("Team -1", outContent.toString().trim());
    }

    @Test
    public void testPrintwithThreeParams() {
	final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	System.setOut(new PrintStream(outContent));
	StringBuilder content = new StringBuilder("");
	EventTime time = new EventTime();
	time.setAmOrPm("am");
	time.setHour(9);
	time.setMinute(10);
	EventUtility.print(content, time, "Actvity 1");
	assertEquals("09:10 am: Actvity 1", outContent.toString().trim());
    }

    @Test
    public void testGetTime() {
	EventTime time = new EventTime();
	time.setAmOrPm("am");
	time.setHour(9);
	time.setMinute(50);
	EventUtility.getTime(time, 30);
	assertEquals("10:20 am: ", time.toString());
    }

}
