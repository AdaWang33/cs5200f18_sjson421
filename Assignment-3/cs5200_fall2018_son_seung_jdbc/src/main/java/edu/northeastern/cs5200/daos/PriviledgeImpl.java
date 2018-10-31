package edu.northeastern.cs5200.daos;

public interface PriviledgeImpl {
	public void assignWebsitePriviledge(int developerId, int websiteId, String priviledge);
	public void assignPagePriviledge(int developerId, int pageId, String priviledge);
	public int deleteWebsitePriviledge(int developerId, int websiteId, String priviledge);
	public int deletePagePriviledge(int developerId, int pageId, String priviledge);
}
