package com.niit.colloboration.DAO.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.colloboration.model.Event;
import com.niit.collobration.DAO.EventDAO;



@Repository("eventDAO")
public class EventDAOimpl implements EventDAO{
	@Autowired
	private SessionFactory sessionFactory;
	public EventDAOimpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
		// TODO Auto-generated constructor stub
	}
    @Transactional
	@Override
	public boolean save(Event event) {
    	try {
			sessionFactory.getCurrentSession().save(event);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}
   @Transactional
	@Override
	public boolean update(Event event) {
	   try {
		sessionFactory.getCurrentSession().update(event);
		return true;
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		// TODO Auto-generated method stub
		return false;
	}
   @Transactional
	@Override
	public boolean delete(String id) {
	   Event event=get(id);
		sessionFactory.getCurrentSession().delete(event);
		return true;
	   
		// TODO Auto-generated method stub
		
	}
    @Transactional
	@Override
	public Event get(String id) {
    	Event event=(Event) sessionFactory.getCurrentSession().get(Event.class, id);
		// TODO Auto-generated method stub
		return event;
	}
		// TODO Auto-generated method stub
	
	
    @Transactional
	@Override
	public List<Event> list() {
    	String q="from Event";
		List<Event> li=sessionFactory.getCurrentSession().createQuery(q).list();
		if(li.isEmpty()||li==null)
			return null;
		else
		return li;
	}
		// TODO Auto-generated method stub
	
	}

	
	



	


	