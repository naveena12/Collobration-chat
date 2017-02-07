package com.niit.collobration.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.colloboration.model.Event;



@Repository("eventDAO")
public interface EventDAO {
	public boolean save(Event event);
	public boolean update(Event event);
	public boolean delete(String id);
	public Event get(String id);
	public List<Event> list();
	
	
	
	

}
