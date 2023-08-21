package in.fssa.kaithari.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.kaithari.interfaces.ProductInterface;
import in.fssa.kaithari.model.Product;
import in.fssa.kaithari.util.ConnectionUtil;

public class ProductDAO implements ProductInterface {
	
	/**
	 * 
	 *
	 * @param 
	 * @throws 
	 */
	@Override
	public void create(Product product) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO products (product_name, category_id, description, price) VALUES (?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);  
			ps.setString(1,product.getName());
			ps.setInt(2, product.getCategory_id());
			ps.setString(3, product.getDescription());
			ps.setInt(4, product.getPrice());
			
			int rowCreated = ps.executeUpdate();
			
			if(rowCreated > 0) {				
			System.out.println("Product created Successfully");
			}
			
			else {
			throw new RuntimeException("Product Creation fails");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
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
	public void updateProduct(Product product ) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
		String query = "UPDATE products SET name = ?, category_id = ?, description = ?, WHERE id = ?";
		conn = ConnectionUtil.getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, product.getName());
		ps.setInt(2, product.getCategory_id());
		ps.setString(3, product.getDescription());
		ps.setInt(10, product.getId());
		
		
		int rowsAffected = ps.executeUpdate();
		
		if (rowsAffected > 0) {
		System.out.println("Product updated successfully");
		} else {
		System.out.println("product updated fails");
		}
		
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
		throw new RuntimeException(e.getMessage());
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
	public void updatePrice(int id, int price) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String query = "UPDATE products set price = ? WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, price);
			ps.setInt(2, id);
			
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected > 0) {
				System.out.println("Price updated successfully");
			}else {
				System.out.println("price updation fails");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);	
		}
	}

	/**
	 * 
	 *
	 * @param 
	 * @throws 
	 */
	@Override
	public void deleteProduct(int id) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String query = "DELETE FROM products WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			
			System.out.println("Product deleted Successfully");
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
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
	public Set<Product> listAllProducts() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Set<Product> allProducts = new HashSet<>(); 
		
		try {
			String query = "SELECT * FROM products";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setCategory_id(rs.getInt("category_id"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getInt("price"));			
				allProducts.add(product);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		finally {
			ConnectionUtil.close(conn, ps);
		}
		return allProducts;
	}

	
	/**
	 * 
	 *
	 * @param 
	 * @throws 
	 */
	@Override
	public Set<Product> listallProductsByCategoryId(int category_id) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Set<Product> listOfProductsByCategoryId = new HashSet<>(); 
		
		try {
			String query = "SELECT * FROM products WHERE category_id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1,category_id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setCategory_id(rs.getInt("category_id"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getInt("price"));	
				listOfProductsByCategoryId.add(product);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		finally {
			ConnectionUtil.close(conn, ps);
		}
		return listOfProductsByCategoryId;
		
	}



}