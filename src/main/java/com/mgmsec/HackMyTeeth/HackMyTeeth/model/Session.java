package com.mgmsec.HackMyTeeth.HackMyTeeth.model;

public class Session {
	private long userID;
	private String sessCookie;
	private String username;
	private int role;
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public String getSessCookie() {
		return sessCookie;
	}
	public void setSessCookie(String sessCookie) {
		this.sessCookie = sessCookie;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public Session(long userID, String sessCookie, String username, int role) {
		super();
		this.userID = userID;
		this.sessCookie = sessCookie;
		this.username = username;
		this.role = role;
	}
	@Override
	public String toString() {
		return "Session [userID=" + userID + ", sessCookie=" + sessCookie + ", username=" + username + ", role=" + role
				+ "]";
	}
	
}
