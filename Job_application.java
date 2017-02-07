package com.niit.colloboration.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Job_application  extends BaseDomain{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private int job_app_id;
private String user_id;
private int job_id;
private String app_date;
private String status;

public int getJob_app_id() {
	return job_app_id;
}
public void setJob_app_id(int job_app_id) {
	this.job_app_id = job_app_id;
}
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}

public int getJob_id() {
	return job_id;
}
public void setJob_id(int job_id) {
	this.job_id = job_id;
}
public String getApp_date() {
	return app_date;
}
public void setApp_date(String app_date) {
	this.app_date = app_date;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

}
