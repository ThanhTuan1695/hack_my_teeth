package com.mgmsec.HackMyTeeth.HackMyTeeth.model;

public class Appointment {
	private int appID;
	private String title;
	private String time;
	private String cusName;
	private String denName;
	private String description;
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getDenName() {
		return denName;
	}
	public void setDenID(String denName) {
		this.denName = denName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Appointment(int appID, String title, String time, String cusName, String denName, String description) {
		super();
		this.appID = appID;
		this.title = title;
		this.time = time;
		this.cusName = cusName;
		this.denName = denName;
		this.description = description;
	}
	@Override
	public String toString() {
		return "Appointment [appID=" + appID + ", title=" + title + ", time=" + time + ", cusName=" + cusName + ", denName="
				+ denName + ", description=" + description + "]";
	}
	
}
