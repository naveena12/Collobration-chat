package com.niit.collobration.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.colloboration.model.Job_application;
@Repository("jobappDAO")
public interface Job_applicationDAO {
	public boolean save(Job_application job_application);
	public boolean update(Job_application job_application);
	public boolean delete(int id);
	public Job_application get(int id);
	public Job_application get(String userid,int jobid);
	public List<Job_application> list();
	public List<Job_application> applicationsforjob(int jobid);

}
