package com.niit.collobration.DAO;

import java.util.List;

import com.niit.colloboration.model.Forum;

public interface ForumDAO {
	public boolean save(Forum forum);
	public boolean update(Forum forum);
	public boolean delete(String id);
	public boolean get(String id);
	public List<Forum> list();
	public void validate(String id,String password);


}
