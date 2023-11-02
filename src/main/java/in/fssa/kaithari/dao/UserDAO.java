package in.fssa.kaithari.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import in.fssa.kaithari.exception.PersistenceException;
import in.fssa.kaithari.interfaces.UserInterface;
import in.fssa.kaithari.model.User;
import in.fssa.kaithari.util.ConnectionUtil;
import in.fssa.kaithari.util.PasswordUtil;

public class UserDAO implements UserInterface {
	/**
	 * Creates a new user.
	 *
	 * This method creates a new user in the data source with the provided user
	 * details. It uses the provided User object to extract the necessary
	 * information and inserts the data into the appropriate table in the database.
	 * If the creation is successful (i.e., a row is affected), a success message is
	 * printed. If the creation fails, a PersistenceException is thrown with an
	 * appropriate error message.
	 *
	 * @param newuser The User object containing the details of the new user to be
	 *                created.
	 * @throws PersistenceException If an error occurs while persisting the user
	 *                              data.
	 */
	@Override
	public void create(User newuser) throws PersistenceException {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO users (name, email, password, district, pincode, village, mobile_number, address) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, newuser.getName());
			ps.setString(2, newuser.getEmail());
			String hashPassword = PasswordUtil.encryptPassword(newuser.getPassword());
			ps.setString(3, hashPassword);
			ps.setString(4, newuser.getDistrict());
			ps.setInt(5, newuser.getPincode());
			ps.setString(6, newuser.getVillage());
			ps.setLong(7, newuser.getMobileNumber());
			ps.setString(8, newuser.getAddress());

      ps.executeUpdate();

      System.out.println("User created Successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Updates the name of a user.
	 *
	 * This method updates the name of an existing user in the data source with the
	 * provided ID. The new name is specified through the `newName` parameter. It
	 * uses the ID to locate the user to update and modifies the name according to
	 * the new value. The update only occurs for active users. If the update
	 * operation is successful (i.e., rows are affected), a success message is
	 * printed. If the update operation fails, a PersistenceException is thrown with
	 * an appropriate error message.
	 *
	 * @param id      The ID of the user whose name is to be updated.
	 * @param newName The new name to be assigned to the user.
	 * @throws PersistenceException If an error occurs while updating the user's
	 *                              name.
	 */
	@Override
	public void updateName(int id, String newName) throws PersistenceException {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE users set name = ? WHERE id = ? AND is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, newName);
			ps.setInt(2, id);

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Name updated successfully");
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps);
		}

	}
	
	/**
	 * 
	 * @param updatedUser
	 * @throws PersistenceException
	 */
	 public void updateUser(User updatedUser) throws PersistenceException {
	        Connection conn = null;
	        PreparedStatement ps = null;

	        try {
	            String query = "UPDATE users SET name = ?, district = ?, pincode = ?, village = ?, mobile_number = ?, address = ? WHERE id = ? AND is_active = 1";
	            conn = ConnectionUtil.getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setString(1, updatedUser.getName());
	            ps.setString(2, updatedUser.getDistrict());
	            ps.setInt(3, updatedUser.getPincode());
	            ps.setString(4, updatedUser.getVillage());
	            ps.setLong(5, updatedUser.getMobileNumber());
	            ps.setString(6, updatedUser.getAddress()); 
	            ps.setInt(7, updatedUser.getId());

	            int rowsAffected = ps.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("User updated successfully");
	            } else {
	                // Handle the case where no rows were updated (user not found or inactive)
	                System.out.println("No user with the specified ID found or user is not active.");
	            }
	        } catch (SQLException e) {
	            throw new PersistenceException(e.getMessage());
	        } finally {
	            ConnectionUtil.close(conn, ps);
	        }
	    }


	/**
	 * Finds a user by their ID.
	 *
	 * This method retrieves user details from the data source based on the provided
	 * user ID. It uses the ID to query the database and fetches the corresponding
	 * user's details. If a matching user is found and is marked as active
	 * (is_active=1), the user information is extracted from the ResultSet and used
	 * to create a User object. The created User object is then returned. If no
	 * matching active user is found, the method returns null. If an SQLException
	 * occurs during the database operation, it is caught and handled, and a
	 * PersistenceException is thrown with an appropriate error message.
	 *
	 * @param id The ID of the user to be retrieved.
	 * @return The retrieved User object if a matching active user is found, or null
	 *         if not found.
	 * @throws PersistenceException If an error occurs while retrieving the user by
	 *                              ID.
	 */
	@Override
	public User findById(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;

		try  {
	        String query = "SELECT id, name, email, password, district, pincode, village, mobile_number, address FROM users WHERE is_active = 1 AND id = ?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setInt(1, id);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            user = new User();
	            user.setId(rs.getInt("id"));
	            user.setName(rs.getString("name"));
	            user.setEmail(rs.getString("email"));
	            user.setPassword(rs.getString("password"));
	            user.setDistrict(rs.getString("district"));
	            user.setPincode(rs.getInt("pincode"));
	            user.setVillage(rs.getString("village"));
	            user.setMobileNumber(rs.getLong("mobile_number"));
	            user.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}

		return user;
	}

	/**
	 * Finds a user by their ID.
	 *
	 * This method retrieves user details from the data source based on the provided
	 * user ID. It uses the ID to query the database and fetches the corresponding
	 * user's details. If a matching user is found and is marked as active
	 * (is_active=1), the user information is extracted from the ResultSet and used
	 * to create a User object. The created User object is then returned. If no
	 * matching active user is found, the method returns null. If an SQLException
	 * occurs during the database operation, it is caught and handled, and a
	 * PersistenceException is thrown with an appropriate error message.
	 *
	 * @param id The ID of the user to be retrieved.
	 * @return The retrieved User object if a matching active user is found, or null
	 *         if not found.
	 * @throws PersistenceException If an error occurs while retrieving the user by
	 *                              ID.
	 */
	@Override
	public User findByEmail(String email) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;

		try {
			String query = "SELECT id, name, email, password, district, pincode, village, mobile_number, address FROM users WHERE is_active = 1 AND email = ?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setString(1, email);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            user = new User();
	            user.setId(rs.getInt("id"));
	            user.setName(rs.getString("name"));
	            user.setEmail(rs.getString("email"));
	            user.setPassword(rs.getString("password"));
	            user.setDistrict(rs.getString("district"));
	            user.setPincode(rs.getInt("pincode"));
	            user.setVillage(rs.getString("village"));
	            user.setMobileNumber(rs.getLong("mobile_number"));
	            user.setAddress(rs.getString("address"));
			}

		} catch (SQLException e) {

			e.printStackTrace();
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);
		}

		return user;
	}

	@Override
	public void findAllUsers() throws PersistenceException {

	}

	/**
	 * Check if a user with the specified ID exists in the data source.
	 *
	 * This method queries the data source to determine if a user with the provided
	 * ID exists. If a matching user is found, the method does nothing. If no
	 * matching user is found, it throws a PersistenceException with an appropriate
	 * error message.
	 *
	 * @param id The ID of the user to check for existence.
	 * @throws PersistenceException If the user with the specified ID does not exist
	 *                              or if an error occurs while checking.
	 */

	public void CheckIdExist(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT name FROM users WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new PersistenceException("user doesn't exists");
			}
		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);
		}
	}

}
