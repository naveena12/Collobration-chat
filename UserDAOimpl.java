package com.niit.colloboration.DAO.impl;

import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.colloboration.model.User;
import com.niit.collobration.DAO.UserDAO;
@Repository("userDAO")
public class UserDAOimpl  implements UserDAO{
	@Autowired
	private SessionFactory sessionFactory;
	public UserDAOimpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
		// TODO Auto-generated constructor stub
	}
    @Transactional
	public boolean save(User user) {
    	try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}
  @Transactional
	public boolean update(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}
  @Transactional
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		User user=get(id);
		sessionFactory.getCurrentSession().delete(user);
	
		return true;
	}
  @Transactional
	public User get(String id) {
	  //String s="from User where id='+id+"'";
	  //User user=sessionFactory.getCurrentSession().createQuery(s).list();
		User user=(User) sessionFactory.getCurrentSession().get(User.class, id);
		// TODO Auto-generated method stub
		return user;
	}
  @Transactional
	public List<User> list() {
		
		// TODO Auto-generated method stub
		String q="from User";
		List<User> li=sessionFactory.getCurrentSession().createQuery(q).list();
		if(li.isEmpty()||li==null)
			return null;
		else
		return li;
	}
    @Transactional
	public User validate(String id, String password) {
    	String hql="from User where user_id= '"+id+"'and password='"+password+"'";
    	Query query = sessionFactory.getCurrentSession().createQuery(hql);
    	User user = (User) query.uniqueResult();
return user;
		// TODO Auto-generated method stub
		
	}

}
