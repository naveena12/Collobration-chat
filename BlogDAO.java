package com.niit.collobration.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.colloboration.model.Blog;
@Repository("blogDAO")
public interface BlogDAO {
	public boolean save(Blog blog);
	public boolean update(Blog blog);
	public void delete(String id);
	public Blog get(String id);
	public List<Blog> list();


}
