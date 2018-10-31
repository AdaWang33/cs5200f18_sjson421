package edu.northeastern.cs5200.daos;

public interface RoleImpl {
	public void assignWebsiteRole(int developerId, int websiteId, int roleId);
	public void assignPageRole(int developerId, int pageId, int roleId);
	public int deleteWebsiteRole(int developerId, int websiteId, int roleId);
	public int deletePageRole(int developerId, int pageId, int roleId);
}
