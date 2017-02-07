 package com.niit.colloboration.DAO.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.colloboration.model.Blog;
import com.niit.collobration.DAO.BlogDAO;

@Repository("blogDAO")
public class BlogDAOimpl implements BlogDAO {
	@Autowired (required=true)
	private SessionFactory sessionFactory;
	public BlogDAOimpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
		// TODO Auto-generated constructor stub
	}
		
		@Transactional
	public boolean save(Blog blog) {
		try {
			sessionFactory.getCurrentSession().save(blog);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}
		@Transactional
	public boolean update(Blog blog) {
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}
		@Transactional
	public void delete(String id) {
		try {
		Blog blog=get(id);
			sessionFactory.getCurrentSession().delete(blog);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}
		@Transactional
	public Blog get(String id) {
		String hql="from Blog where id="+"'"+id+"'";;
  		 @SuppressWarnings({ "rawtypes", "deprecation" })
			Query query= sessionFactory.getCurrentSession().createQuery(hql);
  		 @SuppressWarnings("deprecation")
			List<Blog> li=(List<Blog>)query.list();
  		 if(li==null||li.isEmpty())
  			 return null;
  		 else
  			 return li.get(0);
		
			// TODO Auto-generated method stub
			
		}

		// TODO Auto-generated method stub
		
	
		@Transactional
	public List<Blog> list() {
		@SuppressWarnings("unchecked")
		String s="from Blog";
		  List<Blog> li=sessionFactory.getCurrentSession().createQuery(s).list();
			return li;
	   	
	// TODO Auto-generated method stub
}

		// TODO Auto-generated method stub
		
	
	

}
