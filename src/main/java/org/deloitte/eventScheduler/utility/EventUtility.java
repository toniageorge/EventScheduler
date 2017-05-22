package org.deloitte.eventScheduler.utility;

import org.deloitte.eventScheduler.model.EventTime;

/**
 * 
 * @author Tonia 
 * This is a utility class that helps to organise an event.
 */
public class EventUtility {

    /**
     * @param hour
     * @return String this method checks whether it is am or pm based on the
     *         hour given
     */
    public static String checkAmorPm(int hour) {
	if (hour >= 12) {
	    return "pm";
	} else {
	    return "am";
	}
    }

    /**
     * @param hour
     * @param minutes
     * @return boolean this method checks whether its time for lunch break
     */
    public static boolean isLunchBreak(int hour, int minutes) {
	int newHour = hour + (minutes) / 60;
	if (newHour >= 12 && newHour < 13) {
	    return true;
	}
	return false;
    }

    /**
     * This method check whether it is the time for presenation
     * 
     * @param hour
     * @param minutes
     * @return boolean
     */
    public static boolean isPresenationTime(int hour, int minutes) {
	if ((hour + (minutes / 60)) >= 17) {
	    return true;
	}
	return false;
    }

    /**
     * This method takes the time object and add the minutes and hour. Then the
     * new value will be set back to object
     * 
     * @param currentTime
     * @param eventDuration
     *            Duration of the current event to be added.
     *
     */
    public static void getTime(EventTime currentTime, int eventDuration) {
	int minute = currentTime.getMinute() + eventDuration;
	if ((minute) >= 60) {
	    currentTime.setHour(currentTime.getHour() + (minute / 60));
	    currentTime.setMinute(minute % 60);
	} else {
	    currentTime.setMinute(minute);
	}
    }

    /**
     *  appends a new line
     * @param builder
     * @param value
     *           
     */
    public static void appendString(StringBuilder builder, String value) {
	builder.append(value + System.getProperty("line.separator"));
    }

    /**
     *  prints the content
     * @param content
     * @param time
     * @param activity
     *           
     */
    public static void print(StringBuilder content, EventTime time, String activity) {
	time.setAmOrPm(checkAmorPm(time.getHour()));
	System.out.println(time.toString() + activity);
	appendString(content, time.toString() + activity);
    }

    /**
     *  prints the content
     * @param content
     * @param name
     *           
     */
    public static void print(StringBuilder content, String name) {
	System.out.println(name);
	appendString(content, name);
    }
}
