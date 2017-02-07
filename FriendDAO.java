package com.niit.collobration.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.colloboration.model.Friend;
@Repository("friendDAO")
public interface FriendDAO {
	public boolean save(Friend friend);
	public boolean update(Friend friend);
	//public boolean delete(String id);
	public List<Friend> get(String uid);
	public List<Friend> list();
	

}
