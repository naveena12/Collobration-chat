package com.niit.colloboration.model;

import java.util.Date;

public class OutputMessage extends Message{
	private Date Time;
	
	public OutputMessage(Message original , Date time) {
		super(original.getId() , original.getMessage());
		this.Time = time;
	}
	public Date getTime() {
		return Time;
	}

	public void setTime(Date time) {
		Time = time;
	}
	

}
