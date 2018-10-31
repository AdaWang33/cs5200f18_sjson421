package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;
import java.util.List;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Phone;

public class DeveloperDao implements DeveloperImpl {
	private static DeveloperDao instance = null;
	private java.sql.Connection connection = Connection.getInstance().getConnection();
	private Statement statement = null;
	private PreparedStatement pStatement = null;
	private PreparedStatement pStatement1 = null;
	private PreparedStatement pStatement2 = null;
	private PreparedStatement pStatement3 = null;
	private ResultSet results = null;

	private static final String CREATE_DEVELOPER = "INSERT INTO developer VALUES (?,?)";
	private static final String CREATE_PERSON = "INSERT INTO person VALUES (?,?,?,?,?,?,?)";
	private static final String CREATE_ADDRESS = "INSERT INTO address VALUES (NULL, ?,?,?,?,?,?,?)";
	private static final String CREATE_PHONE = "INSERT INTO phone (phone, primary) VALUES (NULL, ?,?,?)";
	
	private static final String FIND_ALL_DEVELOPERS = "SELECT * FROM developer JOIN person ON "
			+ "developer.id = person.id JOIN address ON person.id = address.person_id "
			+ "JOIN phone ON person.id = phone.person_id";
	private static final String FIND_DEVELOPER_BY_ID = "SELECT * FROM developer JOIN person ON "
			+ "developer.id = person.id JOIN address ON person.id = address.person_id JOIN "
			+ "phone ON person.id = phone.person_id WHERE person.id = ?";
	private static final String FIND_DEVELOPER_BY_USERNAME = "SELECT * FROM developer JOIN person ON "
			+ "developer.personId = person.personId JOIN address ON person.id = address.person_id JOIN "
			+ "phone ON person.id = phone.person_id  WHERE person.username = ?";
	private static final String FIND_DEVELOPER_BY_CREDENTIALS = "SELECT * FROM developer JOIN person ON "
			+ "developer.personId = person.personId JOIN address ON person.id = address.person_id JOIN "
			+ "phone ON person.id = phone.person_id WHERE person.username = ? AND person.password=?";

	private static final String UPDATE_DEVELOPER = "UPDATE developer SET developer_key = ? WHERE id = ?";
	private static final String UPDATE_PERSON = "UPDATE person SET " + "first_name = ?, last_name = ?, username = ?,"
			+ "password = ?, email = ?, dob = ? WHERE id = ?";
	private static final String UPDATE_ADDRESS = "UPDATE address SET street1 = ?, street2 = ?, city = ?,"
			+ "state = ?, zip = ? WHERE person_id = ? AND primary = 1";
	private static final String UPDATE_PHONE = "UPDATE phone SET phone = ? WHERE person_id = ? AND primary = 1";

	private static final String DELETE_DEVELOPER = "DELETE FROM developer WHERE id = ?";

	private DeveloperDao() {
	}

	public static DeveloperDao getInstance() {
		instance = new DeveloperDao();
		return instance;
	}

	public void truncateAll() {
	    try {
	    	connection = Connection.getInstance().getConnection();
	    	statement = connection.createStatement();
	    	statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0; ");
	    	statement.executeUpdate("TRUNCATE address;");
	    	statement.executeUpdate("TRUNCATE developer; ");
	    	statement.executeUpdate("TRUNCATE page;");
	    	statement.executeUpdate("TRUNCATE page_priviledge;");
	    	statement.executeUpdate("TRUNCATE page_role;");
	    	statement.executeUpdate("TRUNCATE person;");
	    	statement.executeUpdate("TRUNCATE phone;");
	    	statement.executeUpdate("TRUNCATE user;");
	    	statement.executeUpdate("TRUNCATE website;");
	    	statement.executeUpdate("TRUNCATE website_priviledge;");
	    	statement.executeUpdate("TRUNCATE website_role;");
	    	statement.executeUpdate("TRUNCATE widget;");
	    	statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    } finally {
			try {
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
				if (pStatement3 != null)
					pStatement3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	} 
	@Override
	public void createDeveloper(Developer developer) {
		connection = Connection.getInstance().getConnection();
		String devKey = developer.getDeveloperKey();
		int id = developer.getId();
		String firstName = developer.getFirstName();
		String lastName = developer.getLastName();
		String username = developer.getUsername();
		String password = developer.getPassword();
		String email = developer.getEmail();
		Date dob = developer.getDob();
		List<Address> addresses = developer.getAddresses();
		List<Phone> phones = developer.getPhones();

		try {
			pStatement = connection.prepareStatement(CREATE_DEVELOPER);
			pStatement1 = connection.prepareStatement(CREATE_PERSON);
			pStatement2 = connection.prepareStatement(CREATE_ADDRESS);
			pStatement3 = connection.prepareStatement(CREATE_PHONE);

			pStatement.setInt(1, id);
			pStatement.setString(2, devKey);

			pStatement1.setInt(1, id);
			pStatement1.setString(2, firstName);
			pStatement1.setString(3, lastName);
			pStatement1.setString(4, username);
			pStatement1.setString(5, password);
			pStatement1.setString(6, email);
			pStatement1.setDate(7, dob);

			if (addresses != null) {
				for (Address address : addresses) {
					pStatement2.setString(1, address.getStreet1());
					pStatement2.setString(2, address.getStreet2());
					pStatement2.setString(3, address.getCity());
					pStatement2.setString(4, address.getState());
					pStatement2.setString(5, address.getZip());
					pStatement2.setBoolean(6, address.getPrimary());
					pStatement2.setInt(7, id);

					pStatement2.executeUpdate();
				}
			}
			if (phones != null) {
				for (Phone phone : phones) {
					pStatement3.setString(1, phone.getPhone());
					pStatement3.setBoolean(2, phone.getPrimary());
					pStatement3.setInt(3, id);

					pStatement3.executeUpdate();
				}
			}
			pStatement1.executeUpdate();
			pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
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
				if (pStatement3 != null)
					pStatement3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Collection<Developer> findAllDevelopers() {
		connection = Connection.getInstance().getConnection();
		Collection<Developer> allDev = new ArrayList<Developer>();
		try {
			statement = connection.createStatement();
			results = statement.executeQuery(FIND_ALL_DEVELOPERS);
			while (results.next()) {
				String devKey = results.getString("developer.developer_key");
				int id = results.getInt("person.id");
				String firstName = results.getString("person.first_name");
				String lastName = results.getString("person.last_name");
				String username = results.getString("person.username");
				String password = results.getString("person.password");
				String email = results.getString("person.email");
				Date dob = results.getDate("person.dob");
				Developer newDev = new Developer(devKey, id, firstName, lastName, username, password, email, dob);
				allDev.add(newDev);
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
				if (pStatement3 != null)
					pStatement3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return allDev;
	}

	@Override
	public Developer findDeveloperById(int developerId) {
		connection = Connection.getInstance().getConnection();
		Developer dev = null;
		try {
			pStatement = connection.prepareStatement(FIND_DEVELOPER_BY_ID);
			pStatement.setInt(1, developerId);
			results = pStatement.executeQuery();
			if (results.next()) {
				String devKey = results.getString("developer.developer_key");
				int id = results.getInt("person.id");
				String firstName = results.getString("person.first_name");
				String lastName = results.getString("person.last_name");
				String username = results.getString("person.username");
				String password = results.getString("person.password");
				String email = results.getString("person.email");
				Date dob = results.getDate("person.dob");
				dev = new Developer(devKey, id, firstName, lastName, username, password, email, dob);
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
				if (pStatement3 != null)
					pStatement3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dev;
	}

	@Override
	public Developer findDeveloperByUsername(String username) {
		connection = Connection.getInstance().getConnection();
		Developer dev = null;
		try {
			pStatement = connection.prepareStatement(FIND_DEVELOPER_BY_USERNAME);
			pStatement.setString(1, username);
			results = pStatement.executeQuery();
			if (results.next()) {
				String devKey = results.getString("developer.developer_key");
				int id = results.getInt("person.id");
				String firstName = results.getString("person.first_name");
				String lastName = results.getString("person.last_name");
				String userName = results.getString("person.username");
				String password = results.getString("person.password");
				String email = results.getString("person.email");
				Date dob = results.getDate("person.dob");
				dev = new Developer(devKey, id, firstName, lastName, userName, password, email, dob);
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
				if (pStatement3 != null)
					pStatement3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dev;
	}

	@Override
	public Developer findDeveloperByCredentials(String username, String password) {
		connection = Connection.getInstance().getConnection();
		Developer dev = null;
		try {
			pStatement = connection.prepareStatement(FIND_DEVELOPER_BY_CREDENTIALS);
			pStatement.setString(1, username);
			pStatement.setString(2, password);

			results = pStatement.executeQuery();
			if (results.next()) {
				String devKey = results.getString("developer.developer_key");
				int id = results.getInt("person.id");
				String firstName = results.getString("person.first_name");
				String lastName = results.getString("person.last_name");
				String userName = results.getString("person.username");
				String passWord = results.getString("person.password");
				String email = results.getString("person.email");
				Date dob = results.getDate("person.dob");
				dev = new Developer(devKey, id, firstName, lastName, userName, passWord, email, dob);
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
				if (pStatement3 != null)
					pStatement3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dev;
	}

	@Override
	public int updateDeveloper(int developerId, Developer developer) {
		connection = Connection.getInstance().getConnection();
		int success = 0;
		try {
			int id = developer.getId();
			String devKey = developer.getDeveloperKey();
			String firstName = developer.getFirstName();
			String lastName = developer.getLastName();
			String username = developer.getUsername();
			String password = developer.getPassword();
			String email = developer.getEmail();
			Date dob = developer.getDob();
			List<Address> addresses = developer.getAddresses();
			List<Phone> phones = developer.getPhones();

			Address primaryAddress = null;
			Phone primaryPhone = null;

			for (Address address : addresses) {
				if (address.getPrimary() == true) {
					primaryAddress = address;
					break;
				}
			}
			for (Phone phone : phones) {
				if (phone.getPrimary() == true) {
					primaryPhone = phone;
					break;
				}
			}

			pStatement = connection.prepareStatement(UPDATE_DEVELOPER);
			pStatement.setString(1, devKey);
			pStatement.setInt(2, id);

			pStatement1 = connection.prepareStatement(UPDATE_PERSON);
			pStatement1.setString(1, firstName);
			pStatement1.setString(2, lastName);
			pStatement1.setString(3, username);
			pStatement1.setString(4, password);
			pStatement1.setString(5, email);
			pStatement1.setDate(6, dob);
			pStatement1.setInt(7, id);

			pStatement2 = connection.prepareStatement(UPDATE_ADDRESS);
			pStatement2.setString(1, primaryAddress.getStreet1());
			pStatement2.setString(2, primaryAddress.getStreet2());
			pStatement2.setString(3, primaryAddress.getCity());
			pStatement2.setString(4, primaryAddress.getState());
			pStatement2.setString(5, primaryAddress.getZip());
			pStatement2.setInt(6, primaryAddress.getPerson().getId());

			pStatement3 = connection.prepareStatement(UPDATE_PHONE);
			pStatement3.setString(1, primaryPhone.getPhone());
			pStatement3.setInt(2, primaryPhone.getPerson().getId());

			pStatement.executeUpdate();
			pStatement1.executeUpdate();
			pStatement2.executeUpdate();
			pStatement3.executeUpdate();

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
				if (pStatement3 != null)
					pStatement3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	// Cascade is on MySQL side
	@Override
	public int deleteDeveloper(int developerId) {
		connection = Connection.getInstance().getConnection();
		int success = 0;
		try {
			pStatement = connection.prepareStatement(DELETE_DEVELOPER);
			pStatement.setInt(1, developerId);

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
				if (pStatement3 != null)
					pStatement3.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}
}
