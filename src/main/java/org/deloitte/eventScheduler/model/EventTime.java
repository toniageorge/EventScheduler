package org.deloitte.eventScheduler.model;

/**
 * 
 * @author Tonia
 *
 */
public class EventTime {
    private int hour;
    private int minute;
    private String amOrPm;

    public int getHour() {
	return hour;
    }

    public void setHour(int hour) {
	this.hour = hour;
    }

    public int getMinute() {
	return minute;
    }

    public void setMinute(int minute) {
	this.minute = minute;
    }

    public String getAmOrPm() {
	return amOrPm;
    }

    public void setAmOrPm(String amOrPm) {
	this.amOrPm = amOrPm;
    }

    public String toString() {
	if (hour > 12) {
	    return String.format("%02d", hour % 12) + ":" + String.format("%02d", minute) + " " + amOrPm + ": ";
	} else {
	    return String.format("%02d", hour) + ":" + String.format("%02d", minute) + " " + amOrPm + ": ";
	}
    }

    public void getTime(int eventMinute) {
	minute = minute + eventMinute;
	if (minute >= 60) {
	    hour = hour + (minute) / 60;
	    minute = minute % 60;
	}
    }
}
