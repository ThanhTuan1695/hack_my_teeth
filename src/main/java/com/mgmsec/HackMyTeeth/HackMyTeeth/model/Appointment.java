package com.mgmsec.HackMyTeeth.HackMyTeeth.model;

public class Appointment {
	private int appID;
	private String title;
	private String time;
	private int cusID;
	private int denID;
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
	public int getCusID() {
		return cusID;
	}
	public void setCusID(int cusID) {
		this.cusID = cusID;
	}
	public int getDenID() {
		return denID;
	}
	public void setDenID(int denID) {
		this.denID = denID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Appointment(int appID, String title, String time, int cusID, int denID, String description) {
		super();
		this.appID = appID;
		this.title = title;
		this.time = time;
		this.cusID = cusID;
		this.denID = denID;
		this.description = description;
	}
	@Override
	public String toString() {
		return "Appointment [appID=" + appID + ", title=" + title + ", time=" + time + ", cusID=" + cusID + ", denID="
				+ denID + ", description=" + description + "]";
	}
	
}
