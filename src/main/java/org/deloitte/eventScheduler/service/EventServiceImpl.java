package org.deloitte.eventScheduler.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.deloitte.eventScheduler.fileOperations.EventFileReader;
import org.deloitte.eventScheduler.fileOperations.EventFileWritter;
import org.deloitte.eventScheduler.model.EventActivity;
import org.deloitte.eventScheduler.model.EventTime;
import org.deloitte.eventScheduler.utility.EventUtility;

/**
 * 
 * @author Tonia This class handles the implementation and organise the events
 */

public class EventServiceImpl implements EventService {
    private List<EventActivity> eventActivityList;
    private String fileName;
    private List<Integer> alreadyAddedLists = new ArrayList<Integer>();
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.deloitte.eventScheduler.service.EventService#getAllEvents(java.lang.
     * String)
     */
    public void getAllEvents(String filePathName) {
	if (EventFileReader.isValidFile(filePathName)) {
	    fileName = filePathName;
	    eventActivityList = EventFileReader.readFile(fileName);
	    if (!eventActivityList.isEmpty()) {
		arrangeEvents();
	    }
	} else {
	    System.out.println("Entered file Path is not valid");
	}
    }

    /**
     * This method is to organise events.Iterate through the list.EventTime
     * object handles the time in which event occur.
     */
    private void arrangeEvents() {
	int teamCount = 1;
	EventTime time = new EventTime();
	StringBuilder content = new StringBuilder();
	EventUtility.print(content, "Team " + teamCount + ":");
	ListIterator<EventActivity> iter = eventActivityList.listIterator();
	initialiseTime(time);
	while (iter.hasNext()) {
	    EventActivity event = iter.next();
	    if(!alreadyAddedLists.contains(iter.previousIndex())){
	    String activity = "";	    
	    if (EventUtility.isLunchBreak(time.getHour(), event.getMinute() + time.getMinute())) {
		EventUtility.print(content, time, "Lunch Break 60min");
		time.setHour(time.getHour() + 1);
		EventUtility.print(content, time, event.getActivity());
	    }
	    else if (EventUtility.isPresenationTime(time.getHour(), event.getMinute() + time.getMinute())) {
		activity = manageTimeForEventsJustBeforeSession(iter.previousIndex(), time);
		if (activity != null) {
		    EventUtility.print(content, time, activity);
		}
		setPresentationTime(time);
		EventUtility.print(content, time, "Staff Motivation Presentation");
		if(iter.hasNext() || !event.getActivity().equalsIgnoreCase(activity)){
		teamCount++;
		initialiseTime(time);
		EventUtility.print(content, "Team " + teamCount + ":");
		EventUtility.print(content, time, event.getActivity());
		}		
	    }
	    else if (!iter.hasNext()) {
		 EventUtility.print(content, time, event.getActivity());
		setPresentationTime(time);
		EventUtility.print(content, time, "Staff Motivation Presentation");		 
	    }	    
	    else {
		EventUtility.print(content, time, event.getActivity());
	    }	    
	    EventUtility.getTime(time, event.getMinute());

	}
	}
	EventFileWritter.writeFile(content, fileName);
    }

    /**
     * This method is to identify a time which can fit to the condition. For
     * example if time is 4.45 and we have 15 more minutes for presentation. But
     * the current activity takes 45 minutes. so we can iterate the list from
     * the current index and see any other events can handle the situation.
     */
    private String manageTimeForEventsJustBeforeSession(int index, EventTime time) {
	String activity = null;
	
	for (int i = index; i < eventActivityList.size(); i++) {
	    int newminute = time.getMinute() + eventActivityList.get(i).getMinute();
	    if ((time.getHour() + (newminute) / 60) <= 17 && (newminute % 60) == 0) {
		activity = eventActivityList.get(i).getActivity();
		alreadyAddedLists.add(i);
		break;
	    }
	}
	return activity;
    }

    /**
     * This method initialise time object to 9:00 am
     * 
     * @param time
     */
    private void initialiseTime(EventTime time) {
	time.setAmOrPm("am");
	time.setHour(9);
	time.setMinute(00);
    }

    /**
     * This method initialise presentation time to 5 pm
     * 
     * @param time
     */

    private void setPresentationTime(EventTime time) {
	time.setHour(17);
	time.setMinute(00);
	time.setAmOrPm("pm");
    }
}