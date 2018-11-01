package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Role;

public class RoleDao implements RoleImpl {

	private static RoleDao instance = null;
	private java.sql.Connection connection = null;
	private Statement statement = null;
	private PreparedStatement pStatement = null;
	private ResultSet results = null;

	private static final String ASSIGN_WEBSITE_ROLE = "INSERT INTO website_role VALUES (NULL,?,?,?)";
	private static final String ASSIGN_PAGE_ROLE = "INSERT INTO page_role VALUES (NULL,?,?,?)";

	private static final String UPDATE_WEBSITE_ROLE = "UPDATE website_role SET role = ? WHERE developer_id = ? AND website_id = ?";
	private static final String UPDATE_PAGE_ROLE = "UPDATE page_role SET role = ? WHERE developer_id = ? AND page_id = ?";

	private static final String DELETE_WEBSITE_ROLE = "DELETE FROM website_role WHERE "
			+ "role = ?, developer_id = ?, website_id = ?";
	private static final String DELETE_PAGE_ROLE = "DELETE FROM page_role WHERE "
			+ "role = ?, developer_id = ?, website_id = ?";

	private RoleDao() {
	}

	public static RoleDao getInstance() {
		instance = new RoleDao();
		return instance;
	}

	@Override
	public void assignWebsiteRole(int developerId, int websiteId, int roleId) {
		connection = Connection.getInstance().getConnection();
		try {
			pStatement = connection.prepareStatement(ASSIGN_WEBSITE_ROLE);

			pStatement.setString(1, Role.values()[roleId].toString());
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
	
	public void updateWebsiteRole(int developerId, int websiteId, int roleId) {
		connection = Connection.getInstance().getConnection();
		try {
			pStatement = connection.prepareStatement(UPDATE_WEBSITE_ROLE);

			pStatement.setString(1, Role.values()[roleId].toString());
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

	@Override
	public void assignPageRole(int developerId, int pageId, int roleId) {
		connection = Connection.getInstance().getConnection();
		try {
			pStatement = connection.prepareStatement(ASSIGN_PAGE_ROLE);

			pStatement.setString(1, Role.values()[roleId].toString());
			pStatement.setInt(2, developerId);
			pStatement.setInt(3, pageId);
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

	public void updatePageRole(int developerId, int pageId, int roleId) {
		connection = Connection.getInstance().getConnection();
		try {
			pStatement = connection.prepareStatement(UPDATE_PAGE_ROLE);

			pStatement.setString(1, Role.values()[roleId].toString());
			pStatement.setInt(2, developerId);
			pStatement.setInt(3, pageId);
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

	@Override
	public int deleteWebsiteRole(int developerId, int websiteId, int roleId) {
		connection = Connection.getInstance().getConnection();
		int success = 0;
		try {
			pStatement = connection.prepareStatement(DELETE_WEBSITE_ROLE);
			pStatement.setString(1, Role.values()[roleId].toString());
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
	public int deletePageRole(int developerId, int pageId, int roleId) {
		connection = Connection.getInstance().getConnection();
		int success = 0;
		try {
			pStatement = connection.prepareStatement(DELETE_PAGE_ROLE);
			pStatement.setString(1, Role.values()[roleId].toString());
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
