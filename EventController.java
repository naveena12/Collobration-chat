package com.niit.colloboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.colloboration.model.Event;

import com.niit.collobration.DAO.EventDAO;

@RestController
	public class EventController {
		
		@Autowired
		Event event;
		
	     @Autowired
	     EventDAO eventDAO;
	     
	     @Autowired
	     HttpSession httpSession;
	     
	     private static final Logger Logger = LoggerFactory.getLogger(EventController.class);
	     
	     @RequestMapping(value="/events",method = RequestMethod.GET)
	 	public ResponseEntity<List<Event>> listAllUsers()
	 	{
	 		//log.debug("->->->calling method listAllUsers");
	 		List<Event> events  = eventDAO.list();
	 		
	 		if(events==null||events.isEmpty()) 
	 		{
	 			event=new Event();
	 			event.setErrorCode("404");
	 			 event.setErrorMessage("no events are avaliable");
	 			
	 		}

	 		return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
	 	}
	     @RequestMapping("/Eevents")
	     public ResponseEntity<List<Event>> getAlluserDetails()
	     {
	     
	     List<Event> events = eventDAO.list();
	     //
	     if(events.isEmpty())
	     {
	    	 event.setErrorCode("404");
	    	 event.setErrorMessage("No events are available");
	    	 events.add(event);
	     }
	     return  new ResponseEntity<List<Event>>(events,HttpStatus.OK);
	     }
	     
	   
	     
	     @RequestMapping("/events/{id}")
	     public ResponseEntity<Event> getEventByID(@PathVariable("id") String userID)
	     {
	    	 
	    	 event = eventDAO.get(userID);
	    	 if(event==null)
	    	 {
	    		 event = new Event();
	    		 event.setErrorCode("404");
	    	  	 event.setErrorMessage("User does not found with id"+userID);
	    	 }
	     return  new ResponseEntity<Event>(event,HttpStatus.OK);
	}
	    

	    /* @RequestMapping(value = "/tAuthenticate/",method =RequestMethod.POST)
	     public ResponseEntity<Event> authenticate(@RequestBody Event event)
	     {
	    	 event = eventDAO.validate(event.getEvent_Id(),event.getPassword());
	    	 if(event==null)
	    	 {
	        event = new Event();
	    	 event = eventDAO.get("404");
	    	 event.setErrorMessage("Invalid credentials...Please try again ");
	    	 }
	    	 else
	    	 {
	    		 httpSession.setAttribute("loggedInUserId",event.getEvent_Id());
	    	 }
	     
	     return  new ResponseEntity<Event>(event,HttpStatus.OK);
	}
	   */
	     
	   
	     

	     @RequestMapping(value = "/ERegister/",method =RequestMethod.POST)
	     public ResponseEntity<Event> register(@RequestBody Event event)
	     {
	  
	    	 if (eventDAO.get(event.getE_id()) !=null){
	    		 event.setErrorCode("404");
	    		 event.setErrorMessage("");
	    	 }
	    	 else{
	    		 eventDAO.save(event);
	    		 event.setErrorCode("200");
	    		 event.setErrorMessage("Thanks for Registration");
	    	 }
	     
	     return  new ResponseEntity<Event>(event,HttpStatus.OK);
	}
	     
	     

	     @RequestMapping(value = "/EUpdate/",method =RequestMethod.PUT)
	     public ResponseEntity<Event> Update(@RequestBody Event event)
	     {
	    	
	    	 if(eventDAO.update(event)==false)
	    	 {
	    		 
	    	 event.setErrorCode("404");
	    	 event.setErrorMessage("The update is not success.Please try after some time");
	    	 }
	    	 else{
	    		 event.setErrorCode("200");
	        	 event.setErrorMessage("Successfullyupdated the information");
	    	 }
	     return  new ResponseEntity<Event>(event,HttpStatus.OK);
	}
	    
	     @RequestMapping(value = "/event/{id}" , method = RequestMethod.DELETE)
	     public ResponseEntity<Event> deletevent(@PathVariable("id") String id, @RequestBody Event event)
	     {
	  	   Logger.debug("->->-> calling method deleteEvent");
	  	   if(eventDAO.get(id) == null)
	  	   { 
	  		   Logger.debug("->->->->Event does not exist with id " +id);
	  		   event = new Event();
	  		   event.setErrorMessage("Event does not exist with id ");
	  		   return new ResponseEntity<Event>(event, HttpStatus.NOT_FOUND);
	  	   }
	  	   else
	  	   {
	  	   event.setErrorCode("200");
	  	   event.setErrorMessage("Event Deleted Successfully with id "+event.getE_id());
	  	   eventDAO.delete(id);
	  	   
	  	   Logger.debug("->->->Event Deleted Successfully");
	  	   }
	  	   return new ResponseEntity<Event>(event, HttpStatus.OK); 
	     }
}
	