package edu.northeastern.cs5200.models;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Website {
	private int id;
	private String name;
	private String description;
	private Date created;
	private Date updated;
	private int visits;
	private int developerId;
	private List<WebsiteRole> websiteRoles;
	private List<WebsitePrivilege> websitePrivileges;
	private List<Page> pages;
			
	public Website(int id, String name, String description, Date created, Date updated, int visits) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.visits = visits;
		this.websiteRoles = new ArrayList<WebsiteRole>();
		this.websitePrivileges = new ArrayList<WebsitePrivilege>();
		this.pages = new ArrayList<Page>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public int getVisits() {
		return visits;
	}
	public void setVisits(int visits) {
		this.visits = visits;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Page> getPages() {
		return pages;
	}
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}
	public List<WebsiteRole> getWebsiteRoles() {
		return websiteRoles;
	}
	public void setWebsiteRoles(List<WebsiteRole> websiteRoles) {
		this.websiteRoles = websiteRoles;
	}
	public List<WebsitePrivilege> getWebsitePrivileges() {
		return websitePrivileges;
	}
	public void setWebsitePrivileges(List<WebsitePrivilege> websitePrivileges) {
		this.websitePrivileges = websitePrivileges;
	}
	public int getDeveloperId() {
		return developerId;
	}
	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}
}
