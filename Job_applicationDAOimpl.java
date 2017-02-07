package com.niit.colloboration.DAO.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.colloboration.model.Job_application;
import com.niit.colloboration.model.User;
import com.niit.collobration.DAO.Job_applicationDAO;

@Repository("jobappDAO")
public class Job_applicationDAOimpl  implements Job_applicationDAO{
	@Autowired
	private SessionFactory sessionFactory;
	public Job_applicationDAOimpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
		// TODO Auto-generated constructor stub
	}
  @Transactional
	@Override
	public boolean save(Job_application job_application) {
	  try {
		sessionFactory.getCurrentSession().save(job_application);
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
	public boolean update(Job_application job_application) {
		try {
			sessionFactory.getCurrentSession().update(job_application);
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
		Job_application job_application=get(id);
		sessionFactory.getCurrentSession().delete(job_application);
	
		return true;
	}
		// TODO Auto-generated method stub
	

  @Transactional
	@Override
	public Job_application get(int id) {
		Job_application job_application=(Job_application) sessionFactory.getCurrentSession().get(Job_application.class, id);
		// TODO Auto-generated method stub
		return job_application;
	}
		// TODO Auto-generated method stub
	
	
   @Transactional
	@Override
	public List<Job_application> list() {
		String q="from Job_application";
		List<Job_application> li=sessionFactory.getCurrentSession().createQuery(q).list();
		if(li.isEmpty()||li==null)
			return null;
		else
		return li;
		// TODO Auto-generated method stub
		
	}
	@Transactional
@Override
public List<Job_application> applicationsforjob(int jobid) {
	// TODO Auto-generated method stub
	String s="from Job_application where job_id="+jobid;
	List<Job_application> li=sessionFactory.getCurrentSession().createQuery(s).list();
	if(li==null||li.isEmpty())
	return null;
	else
		return li;
}
@Transactional
	@Override
	public Job_application get(String userid, int jobid) {
		// TODO Auto-generated method stub
		String s="from Job_application where job_id="+jobid+"and user_id="+userid;
		List<Job_application> li=sessionFactory.getCurrentSession().createQuery(s).list();
		if(li==null||li.isEmpty())
		return null;
		else
			return li.get(0);
		
	}
	

}
