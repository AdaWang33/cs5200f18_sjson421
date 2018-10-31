package edu.northeastern.cs5200.daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Page;

public class PageDao implements PageImpl {

	private static PageDao instance = null;
	private java.sql.Connection connection = null;
	private Statement statement = null;
	private PreparedStatement pStatement = null;
	private ResultSet results = null;

	private static final String CREATE_PAGE = "INSERT INTO page VALUES (?,?,?,?,?,?,?)";

	private static final String FIND_ALL_PAGES = "SELECT * FROM page";
	private static final String FIND_PAGE_BY_ID = "SELECT * FROM page WHERE id = ?";
	private static final String FIND_ALL_PAGE_FOR_WEB = "SELECT * FROM website JOIN website_role"
			+ "ON website.id = website_role.website_id JOIN page ON website_role.page_id ="
			+ "page.id WHERE website_role.role = 'OWNER' AND website.id = ?";
	private static final String UPDATE_PAGE = "UPDATE page SET title=?,description=?,created=?,updated=?,"
			+ "views=? WHERE id = ?";

	private static final String DELETE_PAGE = "DELETE FROM page WHERE id = ?";

	private PageDao() {
	}

	public static PageDao getInstance() {
		instance = new PageDao();
		return instance;
	}

	@Override
	public void createPageForWebsite(int websiteId, Page page) {
		connection = Connection.getInstance().getConnection();
		try {
			int id = page.getId();
			String name = page.getTitle();
			String description = page.getDescription();
			Date created = page.getCreated();
			Date updated = page.getUpdated();
			int visits = page.getViews();

			pStatement = connection.prepareStatement(CREATE_PAGE);

			pStatement.setInt(1, id);
			pStatement.setString(2, name);
			pStatement.setString(3, description);
			pStatement.setDate(4, created);
			pStatement.setDate(5, updated);
			pStatement.setInt(6, visits);
			pStatement.setInt(7, websiteId);
			pStatement.executeUpdate();
		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (pStatement != null)
					pStatement.close();
				if (results != null)
					results.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Collection<Page> findAllPages() {
		connection = Connection.getInstance().getConnection();
		Collection<Page> pages = new ArrayList<Page>();
		try {
			statement = connection.createStatement();
			results = statement.executeQuery(FIND_ALL_PAGES);
			while (results.next()) {
				int id = results.getInt("page.id");
				String name = results.getString("page.title");
				String description = results.getString("page.description");
				Date created = results.getDate("page.created");
				Date updated = results.getDate("page.updated");
				int visits = results.getInt("page.views");

				Page w = new Page(id, name, description, created, updated, visits);
				pages.add(w);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (pStatement != null)
					pStatement.close();
				if (results != null)
					results.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pages;
	}

	@Override
	public Page findPageById(int pageId) {
		connection = Connection.getInstance().getConnection();
		Page w = null;
		try {
			pStatement = connection.prepareStatement(FIND_PAGE_BY_ID);
			pStatement.setInt(1, pageId);
			results = pStatement.executeQuery();
			if (results.next()) {
				int id = results.getInt("page.id");
				String name = results.getString("page.title");
				String description = results.getString("page.description");
				Date created = results.getDate("page.created");
				Date updated = results.getDate("page.updated");
				int visits = results.getInt("page.views");

				w = new Page(id, name, description, created, updated, visits);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (pStatement != null)
					pStatement.close();
				if (results != null)
					results.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return w;
	}

	@Override
	public Collection<Page> findPagesForWebsite(int websiteId) {
		connection = Connection.getInstance().getConnection();
		Collection<Page> pages = new ArrayList<Page>();
		try {
			pStatement = connection.prepareStatement(FIND_ALL_PAGE_FOR_WEB);
			pStatement.setInt(1, websiteId);
			results = pStatement.executeQuery();

			while (results.next()) {
				int id = results.getInt("page.id");
				String name = results.getString("page.title");
				String description = results.getString("page.description");
				Date created = results.getDate("page.created");
				Date updated = results.getDate("page.updated");
				int visits = results.getInt("page.views");

				pages.add(new Page(id, name, description, created, updated, visits));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (pStatement != null)
					pStatement.close();
				if (results != null)
					results.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pages;
	}

	@Override
	public int updatePage(int pageId, Page page) {
		connection = Connection.getInstance().getConnection();
		int success = 0;
		try {
			pStatement = connection.prepareStatement(UPDATE_PAGE);
			pStatement.setString(1, page.getTitle());
			pStatement.setString(2, page.getDescription());
			pStatement.setDate(4, page.getCreated());
			pStatement.setDate(5, page.getUpdated());
			pStatement.setInt(6, page.getViews());
			pStatement.setInt(7, pageId);

			pStatement.executeUpdate();
			success = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (pStatement != null)
					pStatement.close();
				if (results != null)
					results.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	// Cascade on MySql side
	@Override
	public int deletePage(int pageId) {
		connection = Connection.getInstance().getConnection();
		int success = 0;
		try {
			pStatement = connection.prepareStatement(DELETE_PAGE);
			pStatement.setInt(1, pageId);

			pStatement.executeUpdate();
			success = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (pStatement != null)
					pStatement.close();
				if (results != null)
					results.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}
}
