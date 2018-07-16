package com.mgmsec.HackMyTeeth.HackMyTeeth.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name="customer")
public class Customer {
	@Id
	private long cusID;
	@Column(name="firstName")
	private String firstName;
	@Column(name="lastName")
	private String lastName;
	@Column(name="email")
	private String email;
	@Override
	public String toString() {
		return "Customer [cusID=" + cusID + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phone=" + phone + ", gender=" + gender + ", accountID=" + accountID + "]";
	}
	@Column(name="phone")
	private String phone;
	@Column(name="gender")
	private String gender;
	@Column(name="accountID")
	private long accountID;
	public long getcusID() {
		return cusID;
	}
	public void setcusID(long cusID) {
		this.cusID = cusID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getAccountID() {
		return accountID;
	}
	public void setAccountID(long accountID) {
		this.accountID = accountID;
	}
	public Customer(long cusID, String firstName, String lastName, String email, String phone, String gender,
			long accountID) {
		super();
		this.cusID=cusID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.accountID = accountID;
	}
	
	
	
}
