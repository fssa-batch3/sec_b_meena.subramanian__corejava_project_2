package in.fssa.kaithari.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.HashSet;
//import java.util.Set;

import in.fssa.kaithari.interfaces.UserInterface;
import in.fssa.kaithari.model.User;
import in.fssa.kaithari.util.ConnectionUtil;


public class UserDAO implements UserInterface {
	/**
	 * 
	 *
	 * @param 
	 * @throws 
	 */
	@Override
	public void create(User newuser) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO users (name,email,password)values(?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, newuser.getName());
			ps.setString(2, newuser.getEmail());
			ps.setString(3, newuser.getPassword());
			
			ps.executeUpdate();
			
			System.out.println("User created Successfully");
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
		finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * 
	 *
	 * @param 
	 * @throws 
	 */
	@Override
	public void updateName(int id, String newName) {

		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE users set name = ? WHERE id = ? AND is_active = 1";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, newName);
			ps.setInt(2, id);
			
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected > 0) {
				System.out.println("Name updated successfully");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);	
		}
		finally {
			ConnectionUtil.close(conn, ps);
		}
		
	}

}
