package com.niit.colloboration.DAO.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.colloboration.model.Job;
import com.niit.colloboration.model.User;
import com.niit.collobration.DAO.JobDAO;

@Repository("jobDAO")
public class JobDAOimpl implements JobDAO {
	@Autowired
	private SessionFactory sessionFactory;
	public JobDAOimpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	@Transactional
	@Override
	public boolean save(Job job) {
		try {
			sessionFactory.getCurrentSession().save(job);
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		// TODO Auto-generated method stub
		return true;
	}
 @Transactional
	@Override
	public boolean update(Job job) {
	 try {
		sessionFactory.getCurrentSession().update(job);
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
	public boolean delete(int id) {
	 Job job=get(id);
	 sessionFactory.getCurrentSession().delete(job);
	 return true;
 }
	 
		
	
		
		// TODO Auto-generated method stub
		
	
  @Transactional
	@Override
	public Job get(int id) {
	//String s="from Job where id='+id+"'";
	  //Job job=sessionFactory.getCurrentSession().createQuery(s).list();
		Job job=(Job) sessionFactory.getCurrentSession().get(Job.class, id);
		// TODO Auto-generated method stub
		return job;
	}
	
	
		// TODO Auto-generated method stub
	
	
   @Transactional
	@Override
	public List<Job> list() {
	   String q="from Job";
		List<Job> li=sessionFactory.getCurrentSession().createQuery(q).list();
		if(li.isEmpty()||li==null)
			return null;
		else
		return li;
   }
}
	
   

		
	
	


