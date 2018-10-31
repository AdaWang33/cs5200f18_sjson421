package edu.northeastern.cs5200.daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.User;
import edu.northeastern.cs5200.models.Phone;

public class UserDao implements UserImpl{
	private static UserDao instance = null;
	private java.sql.Connection connection = null;
	private Statement statement = null;
	private PreparedStatement pStatement = null;
	private PreparedStatement pStatement1 = null;
	private PreparedStatement pStatement2 = null;
	private PreparedStatement pStatement3 = null;
	private ResultSet results = null;
	
	private static final String CREATE_USER = "INSERT INTO user VALUES (?,?,?)";
	private static final String CREATE_PERSON = "INSERT INTO person VALUES (?,?,?,?,?,?,?)";
	private static final String CREATE_ADDRESS = "INSERT INTO address VALUES (NULL, ?,?,?,?,?,?,?)";
	private static final String CREATE_PHONE = "INSERT INTO phone (phone, primary) VALUES (NULL, ?,?,?)";
	
	private UserDao() {
	}

	public static UserDao getInstance() {
		instance = new UserDao();
		return instance;
	}
	
	@Override
	public void createUser(User user) {
		connection = Connection.getInstance().getConnection();
		Boolean userAgreement = user.getUserAgreement();
		Boolean approvedUser = user.getApprovedUser();
		
		int id = user.getId();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String username = user.getUsername();
		String password = user.getPassword();
		String email = user.getEmail();
		Date dob = user.getDob();
		List<Address> addresses = user.getAddresses();
		List<Phone> phones = user.getPhones();

		try {
			pStatement = connection.prepareStatement(CREATE_USER);
			pStatement1 = connection.prepareStatement(CREATE_PERSON);
			pStatement2 = connection.prepareStatement(CREATE_ADDRESS);
			pStatement3 = connection.prepareStatement(CREATE_PHONE);

			pStatement.setInt(1, id);
			pStatement.setBoolean(2, userAgreement);
			pStatement.setBoolean(3, approvedUser);

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
	}
}
