package edu.northeastern.cs5200.models;

public class PageRole {
	private int id;
	private Role role;
	private int dId;
	private int wId;
	
	public PageRole(Role role, int dId, int wId) {
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
	public int getPageId() {
		return wId;
	}
	public void setPageId(int wId) {
		this.wId = wId;
	}
}
