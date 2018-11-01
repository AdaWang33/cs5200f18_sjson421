package edu.northeastern.cs5200.models;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Page {
	private int id;
	private String title;
	private String description;
	private Date created;
	private Date updated;
	private int views;
	private int websiteId;
	private List<PageRole> pageRoles;
	private List<PagePrivilege> pagePrivileges;
	private List<Widget> widgets;
	
	public Page(int id, String title, String description, Date created, Date updated, int views) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.views = views;
		this.websiteId = 0;
		this.pageRoles = new ArrayList<PageRole>();
		this.pagePrivileges = new ArrayList<PagePrivilege>();
		this.widgets = new ArrayList<Widget>();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWebsiteId() {
		return websiteId;
	}
	public void setWebsiteId(int websiteId) {
		this.websiteId = websiteId;
	}
	public List<Widget> getWidgets() {
		return widgets;
	}
	public void setWidgets(List<Widget> widgets) {
		this.widgets = widgets;
	}
	public List<PageRole> getPageRoles() {
		return pageRoles;
	}
	public void setPageRoles(List<PageRole> pageRoles) {
		this.pageRoles = pageRoles;
	}
	public List<PagePrivilege> getPagePrivileges() {
		return pagePrivileges;
	}
	public void setPagePrivileges(List<PagePrivilege> pagePrivileges) {
		this.pagePrivileges = pagePrivileges;
	}
}
