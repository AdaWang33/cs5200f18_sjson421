package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.northeastern.cs5200.Connection;

public class PriviledgeDao implements PriviledgeImpl {

	private static PriviledgeDao instance = null;
	private java.sql.Connection connection = null;
	private Statement statement = null;
	private PreparedStatement pStatement = null;
	private ResultSet results = null;
	
	private static final String ASSIGN_WEBSITE_PRIVILEGE = "INSERT INTO website_priviledge VALUES (NULL,?,?,?)";
	private static final String ASSIGN_PAGE_PRIVILEGE = "INSERT INTO page_priviledge VALUES (NULL,?,?,?)";

	private static final String UPDATE_WEBSITE_PRIVILEGE = "UPDATE website_priviledge SET priviledge = ? WHERE developer_id = ? AND website_id = ?";
	private static final String UPDATE_PAGE_PRIVILEGE = "UPDATE page_priviledge SET priviledge = ? WHERE developer_id = ? AND page_id = ?";

	private static final String DELETE_WEBSITE_PRIVILEGE = "DELETE FROM website_priviledge WHERE "
			+ "priviledge = ?, developer_id = ?, website_id = ?";
	private static final String DELETE_PAGE_PRIVILEGE = "DELETE FROM page_priviledge WHERE "
			+ "priviledge = ?, developer_id = ?, website_id = ?";

	private PriviledgeDao() {
	}

	public static PriviledgeDao getInstance() {
		if (instance == null) {
			instance = new PriviledgeDao();
		}
		return instance;
	}

	@Override
	public void assignWebsitePriviledge(int developerId, int websiteId, String priviledge) {
		connection = Connection.getInstance().getConnection();
		try {
			pStatement = connection.prepareStatement(ASSIGN_WEBSITE_PRIVILEGE);

			pStatement.setString(1, priviledge);
			pStatement.setInt(2, developerId);
			pStatement.setInt(3, websiteId);
				
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateWebsitePriviledge(int developerId, int pageId, String priviledge) {
		connection = Connection.getInstance().getConnection();
		try {
			pStatement = connection.prepareStatement(UPDATE_PAGE_PRIVILEGE);

			pStatement.setString(1, priviledge);
			pStatement.setInt(2, developerId);
			pStatement.setInt(3, pageId);
			
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
	public void updatePagePriviledge(int developerId, int pageId, String priviledge) {
		connection = Connection.getInstance().getConnection();
		try {
			pStatement = connection.prepareStatement(UPDATE_PAGE_PRIVILEGE);

			pStatement.setString(1, priviledge);
			pStatement.setInt(2, developerId);
			pStatement.setInt(3, pageId);
			
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
	public void assignPagePriviledge(int developerId, int pageId, String priviledge) {
		connection = Connection.getInstance().getConnection();
		try {
			pStatement = connection.prepareStatement(ASSIGN_PAGE_PRIVILEGE);

			pStatement.setString(1, priviledge);
			pStatement.setInt(2, developerId);
			pStatement.setInt(3, pageId);
			
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
	public int deleteWebsitePriviledge(int developerId, int websiteId, String priviledge) {
		connection = Connection.getInstance().getConnection();
		int success = 0;
		try {
			pStatement = connection.prepareStatement(DELETE_WEBSITE_PRIVILEGE);
			pStatement.setString(1, priviledge);
			pStatement.setInt(2, developerId);
			pStatement.setInt(3, websiteId);

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

	@Override
	public int deletePagePriviledge(int developerId, int pageId, String priviledge) {
		connection = Connection.getInstance().getConnection();
		int success = 0;
		try {
			pStatement = connection.prepareStatement(DELETE_PAGE_PRIVILEGE);
			pStatement.setString(1, priviledge);
			pStatement.setInt(2, developerId);
			pStatement.setInt(3, pageId);

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
