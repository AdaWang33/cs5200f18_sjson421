package edu.northeastern.cs5200.models;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Developer extends Person{
	private String developerKey;
	private List<WebsiteRole> websiteRoles;
	private List<WebsitePrivilege> websitePrivileges;
	private List<PageRole> pageRoles;
	private List<PagePrivilege> pagePrivileges;

	public Developer(String developerKey, int id, String firstName, String lastName) {
		super();
		Date date = new Date(System.currentTimeMillis());
		this.developerKey = developerKey;
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setUsername("jDoe");
		this.setPassword("jDoe");
		this.setEmail("jdoe@gmail.com");
		this.setDob(date);
		this.pageRoles = new ArrayList<PageRole>();
		this.pagePrivileges = new ArrayList<PagePrivilege>();
		this.websiteRoles = new ArrayList<WebsiteRole>();
		this.websitePrivileges = new ArrayList<WebsitePrivilege>();
	}
	public Developer(String developerKey, int id, String firstName, String lastName, String username, 
			String password, String email, Date dob) {
		super();
		this.developerKey = developerKey;
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
		this.setDob(dob);
		this.pageRoles = new ArrayList<PageRole>();
		this.pagePrivileges = new ArrayList<PagePrivilege>();
		this.websiteRoles = new ArrayList<WebsiteRole>();
		this.websitePrivileges = new ArrayList<WebsitePrivilege>();
	}
	public Developer(String developerKey, int id, String firstName, String lastName, String username, 
			String password, String email, Date dob, List<Address> addresses, List<Phone> phones) {
		super();
		this.developerKey = developerKey;
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
		this.setDob(dob);
		this.setAddresses(addresses);
		this.setPhones(phones);
		this.pageRoles = new ArrayList<PageRole>();
		this.pagePrivileges = new ArrayList<PagePrivilege>();
		this.websiteRoles = new ArrayList<WebsiteRole>();
		this.websitePrivileges = new ArrayList<WebsitePrivilege>();
	}

	public String getDeveloperKey() {
		return developerKey;
	}

	public void setDeveloperKey(String developerKey) {
		this.developerKey = developerKey;
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
