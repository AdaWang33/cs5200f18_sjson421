package edu.northeastern.cs5200.daos;

import java.util.Collection;

import edu.northeastern.cs5200.models.Page;

public interface PageImpl {
	public void createPageForWebsite(int websiteId, Page page);

	public Collection<Page> findAllPages();

	public Page findPageById(int pageId);

	public Collection<Page> findPagesForWebsite(int websiteId);

	public int updatePage(int pageId, Page page);

	public int deletePage(int pageId);
}
