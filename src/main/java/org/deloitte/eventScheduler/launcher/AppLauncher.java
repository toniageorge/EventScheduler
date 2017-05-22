package org.deloitte.eventScheduler.launcher;

import org.deloitte.eventScheduler.service.EventService;
import org.deloitte.eventScheduler.service.EventServiceImpl;

/**
 * 
 * @author Tonia 
 * Main class to launch the application
 */
public class AppLauncher {
    
    public static void main(String[] args) {
	if(args.length>0){
	System.out.println("file path given is " + args[0]);
	EventService event = new EventServiceImpl();
	event.getAllEvents(args[0]);
	}
	else{
	    System.out.println("Please Enter a file path ");
	}
    }
}
