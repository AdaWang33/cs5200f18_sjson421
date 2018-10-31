package edu.northeastern.cs5200.models;

public class WebsitePrivilege {
	private int id;
	private Priviledge p;
	private int dId;
	private int wId;
	
	public WebsitePrivilege(Priviledge p, int dId, int wId) {
		this.p = p;
		this.dId = dId;
		this.wId = wId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Priviledge getPrivilege() {
		return p;
	}
	public void setPrivilege(Priviledge p) {
		this.p = p;
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
