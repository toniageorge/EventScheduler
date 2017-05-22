package org.deloitte.eventScheduler.model;

/**
 * 
 * @author Tonia model class for EventActivity
 */
public class EventActivity {
  private  int minute;
  private String Activity;
    
    public int getMinute() {
	return minute;
    }
    public void setMinute(int minute) {
	this.minute = minute;
    }
    public String getActivity() {
	return Activity;
    }
    public void setActivity(String activity) {
	Activity = activity;
    }

}
