package com.niit.collobration.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.colloboration.model.User;

@Repository("userDAO")
public interface UserDAO {
	public boolean save(User user);
	public boolean update(User user);
	public boolean delete(String id);
	public User get(String id);
	public List<User> list();
	public User validate(String id,String password);
	
	

}
