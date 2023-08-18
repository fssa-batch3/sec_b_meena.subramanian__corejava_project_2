package in.fssa.kaithari.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import in.fssa.kaithari.interfaces.CategoryInterFace;
import in.fssa.kaithari.model.Category;
import in.fssa.kaithari.util.ConnectionUtil;

public class CategoryDAO implements CategoryInterFace{

	/**
	 * 
	 *
	 * @param 
	 * @throws 
	 */
	@Override	
	public void create(Category category) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String query = "INSERT INTO categories (name) values(?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, category.getName());
			
			ps.executeUpdate();
			
			System.out.println("Category created Successfully");
			
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
	public void updateName(int id, String categoryName) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE categories set name = ? WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, categoryName);
			ps.setInt(2, id);
			
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected > 0) {
				System.out.println("category name updated successfully");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);	
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Category> listAllCategroyByCategoryId(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}



















