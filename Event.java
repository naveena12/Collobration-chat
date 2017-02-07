package com.niit.colloboration.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Event extends BaseDomain{
	@Id
	private String e_id;
	private String e_name;
	private String e_venue;
	private String e_description;
	private String e_datetime;
	public String getE_id() {
		return e_id;
	}
	public void setE_id(String e_id) {
		this.e_id = e_id;
	}
	public String getE_name() {
		return e_name;
	}
	public void setE_name(String e_name) {
		this.e_name = e_name;
	}
	public String getE_venue() {
		return e_venue;
	}
	public void setE_venue(String e_venue) {
		this.e_venue = e_venue;
	}
	public String getE_description() {
		return e_description;
	}
	public void setE_description(String e_description) {
		this.e_description = e_description;
	}
	public String getE_datetime() {
		return e_datetime;
	}
	public void setE_datetime(String e_datetime) {
		this.e_datetime = e_datetime;
	}
	

}
