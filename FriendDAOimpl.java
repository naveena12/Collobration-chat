package com.niit.colloboration.DAO.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.colloboration.model.Friend;
import com.niit.colloboration.model.User;
import com.niit.collobration.DAO.FriendDAO;
@Repository("friendDAO")
public class FriendDAOimpl implements FriendDAO{
	@Autowired
	private SessionFactory sessionFactory;
	public FriendDAOimpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
		// TODO Auto-generated constructor stub
	}
   @Transactional
	@Override
	public boolean save(Friend friend) {
	   try {
		sessionFactory.getCurrentSession().save(friend);
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
	public boolean update(Friend friend) {
	   try {
		sessionFactory.getCurrentSession().update(friend);
		return true;
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		// TODO Auto-generated method stub
		return false;
	}
  /* @Transactional
	@Override
	public boolean delete(String id) {
	   Friend friend=get(id);
		sessionFactory.getCurrentSession().delete(friend);
	
		return true;
	}
		*/// TODO Auto-generated method stub
	
	
   @Transactional
	@Override
	public List<Friend> get(String uid) {
	   String s="from Friend where user_id="+uid;
	   List<Friend> friends=sessionFactory.getCurrentSession().createQuery(s).list();
		return friends;
	}
		// TODO Auto-generated method stub
		
	
   @Transactional
	@Override
	public List<Friend> list() {
	   String q="from Friend";
		List<Friend> li=sessionFactory.getCurrentSession().createQuery(q).list();
		if(li.isEmpty()||li==null)
			return null;
		else
		return li;
		// TODO Auto-generated method stub

	}

	

}
