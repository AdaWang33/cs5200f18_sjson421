package edu.northeastern.cs5200.models;

import java.sql.Date;

public class User extends Person{
	private Boolean approvedUser;
	private Boolean userAgreement;

	public User(int id, String firstName, String lastName) {
		super();
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.userAgreement = false;
		this.approvedUser = false;
	}
	public User(int id, String firstName, String lastName, String username, 
			String password, String email, Date dob) {
		super();
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setUserAgreement(false);
		this.setApprovedUser(false);
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
		this.setDob(dob);
	}
	public Boolean getUserAgreement() {
		return userAgreement;
	}

	public void setUserAgreement(Boolean userAgreement) {
		this.userAgreement = userAgreement;
	}
	public Boolean getApprovedUser() {
		return approvedUser;
	}
	public void setApprovedUser(Boolean approvedUser) {
		this.approvedUser = approvedUser;
	}
}
