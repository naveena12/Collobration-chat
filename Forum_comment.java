package com.niit.collobration.DAO;

import java.util.List;

public interface Forum_comment {
	public boolean save(Forum_comment forum_comment);
	public boolean update(Forum_comment forum_comment);
	public boolean delete(String id);
	public boolean get(String id);
	public List<Forum_comment> list();
	public void validate(String id,String password);


}
