package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Website;

public class WebsiteDao implements WebsiteImpl {

	private static WebsiteDao instance = null;
	private java.sql.Connection connection = null;
	private Statement statement = null;
	private PreparedStatement pStatement = null;
	private PreparedStatement pStatement1 = null;
	private PreparedStatement pStatement2 = null;
	private PreparedStatement pStatementPages = null;
	private ResultSet results = null;

	private static final String CREATE_WEBSITE = "INSERT INTO website VALUES (?,?,?,?,?,?,?)";
	
	private static final String FIND_ALL_WEBSITES = "SELECT * FROM website";
	
	private static final String FIND_ALL_WEB_FOR_DEV = "SELECT * FROM website JOIN website_role"
			+ "ON website.id = website_role.website_id JOIN developer ON website_role.developer_id ="
			+ "developer.id WHERE website_role.role = 'OWNER' AND developer.id = ?";
	
	private static final String FIND_WEBSITE_BY_ID = "SELECT * FROM website WHERE id = ?";
	
	private static final String UPDATE_WEBSITE = "UPDATE website SET name=?,description=?,created=?,updated=?,"
			+ "visit=?, developer_id=? WHERE id = ?";
	
	private static final String DELETE_WEBSITE = "DELETE FROM website WHERE id = ?";
	
	private WebsiteDao() {
	}

	public static WebsiteDao getInstance() {
		instance = new WebsiteDao();
		return instance;
	}

	@Override
	public void createWebsiteForDeveloper(int developerId, Website website) {
		connection = Connection.getInstance().getConnection();
		try {
			int id = website.getId();
			String name = website.getName();
			String description = website.getDescription();
			Date created = website.getCreated();
			Date updated = website.getUpdated();
			int visits = website.getVisits();

			pStatement = connection.prepareStatement(CREATE_WEBSITE);

			pStatement.setInt(1, id);
			pStatement.setString(2, name);
			pStatement.setString(3, description);
			pStatement.setDate(4, created);
			pStatement.setDate(5, updated);
			pStatement.setInt(6, visits);
			pStatement.setInt(7, developerId);
			pStatement.executeUpdate();
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
				if (pStatement1 != null)
					pStatement1.close();
				if (pStatement2 != null)
					pStatement2.close();
				if (pStatementPages != null)
					pStatementPages.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Collection<Website> findAllWebsites() {
		connection = Connection.getInstance().getConnection();
		Collection<Website> websites = new ArrayList<Website>();
		try {
			statement = connection.createStatement();
			results = statement.executeQuery(FIND_ALL_WEBSITES);
			while (results.next()) {
				int id = results.getInt("website.id");
				String name = results.getString("website.name");
				String description = results.getString("website.description");
				Date created = results.getDate("website.created");
				Date updated = results.getDate("website.updated");
				int visits = results.getInt("website.visits");
				
				Website w = new Website(id, name, description, created, updated, visits);
				websites.add(w);
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
				if (pStatement1 != null)
					pStatement1.close();
				if (pStatement2 != null)
					pStatement2.close();
				if (pStatementPages != null)
					pStatementPages.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return websites;
	}

	@Override
	public Collection<Website> findWebsitesForDeveloper(int developerId) {
		connection = Connection.getInstance().getConnection();
		Collection<Website> websites = new ArrayList<Website>();
		try {
			pStatement = connection.prepareStatement(FIND_ALL_WEB_FOR_DEV);
			pStatement.setInt(1, developerId);
			results = pStatement.executeQuery();
			
			while (results.next()) {
				int id = results.getInt("website.id");
				String name = results.getString("website.name");
				String description = results.getString("website.description");
				Date created = results.getDate("website.created");
				Date updated = results.getDate("website.updated");
				int visits = results.getInt("website.visits");
				
				websites.add(new Website(id, name, description, created, updated, visits));
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
				if (pStatement1 != null)
					pStatement1.close();
				if (pStatement2 != null)
					pStatement2.close();
				if (pStatementPages != null)
					pStatementPages.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return websites;
	}

	@Override
	public Website findWebsiteById(int websiteId) {
		connection = Connection.getInstance().getConnection();
		Website w = null;
		try {
			pStatement = connection.prepareStatement(FIND_WEBSITE_BY_ID);
			pStatement.setInt(1, websiteId);
			results = pStatement.executeQuery();
			if (results.next()) {
				int id = results.getInt("website.id");
				String name = results.getString("website.name");
				String description = results.getString("website.description");
				Date created = results.getDate("website.created");
				Date updated = results.getDate("website.updated");
				int visits = results.getInt("website.visits");
				
				w = new Website(id, name, description, created, updated, visits);
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
				if (pStatement1 != null)
					pStatement1.close();
				if (pStatement2 != null)
					pStatement2.close();
				if (pStatementPages != null)
					pStatementPages.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return w;
	}

	@Override
	public int updateWebsite(int websiteId, Website website) {
		connection = Connection.getInstance().getConnection();
		int success = 0;
		try {
			pStatement = connection.prepareStatement(UPDATE_WEBSITE);
			pStatement.setString(1, website.getName());
			pStatement.setString(2, website.getDescription());
			pStatement.setDate(4, website.getCreated());
			pStatement.setDate(5, website.getUpdated());
			pStatement.setInt(6, website.getVisits());
			pStatement.setInt(7, website.getDeveloperId());
			pStatement.setInt(8, websiteId);

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
				if (pStatement1 != null)
					pStatement1.close();
				if (pStatement2 != null)
					pStatement2.close();
				if (pStatementPages != null)
					pStatementPages.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	//Cascade is on MySQL Side
	@Override
	public int deleteWebsite(int websiteId) {
		connection = Connection.getInstance().getConnection();
		int success = 0;
		try {
			pStatement = connection.prepareStatement(DELETE_WEBSITE);
			pStatement.setInt(1, websiteId);

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
				if (pStatement1 != null)
					pStatement1.close();
				if (pStatement2 != null)
					pStatement2.close();
				if (pStatementPages != null)
					pStatementPages.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}
}
