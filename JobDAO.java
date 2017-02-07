package com.niit.collobration.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.colloboration.model.Job;
@Repository("jobDAO")
public interface JobDAO {
	public boolean save(Job job);
	public boolean update(Job job);
	public boolean delete(int id);
	public Job get(int id);
	public List<Job> list();
	

}
