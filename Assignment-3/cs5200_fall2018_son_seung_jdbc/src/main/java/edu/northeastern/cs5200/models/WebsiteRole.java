package edu.northeastern.cs5200.models;

public class WebsiteRole {
	private int id;
	private Role role;
	private int dId;
	private int wId;
	
	public WebsiteRole(Role role, int dId, int wId) {
		this.role = role;
		this.dId = dId;
		this.wId = wId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getDeveloperId() {
		return dId;
	}
	public void setDeveloperId(int dId) {
		this.dId = dId;
	}
	public int getWebsiteId() {
		return wId;
	}
	public void setWebsiteId(int wId) {
		this.wId = wId;
	}
}
