package in.fssa.kaithari.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.kaithari.exception.PersistenceException;
import in.fssa.kaithari.model.Seller;
import in.fssa.kaithari.util.ConnectionUtil;

public class SellerDAO {
	public void create(Seller newSeller) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO sellers (name, email, proof_image, id_image, password) VALUES (?, ?, ?, ?, ?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, newSeller.getName());
			ps.setString(2, newSeller.getEmail());
			ps.setString(3, newSeller.getProofImage());
			ps.setString(4, newSeller.getIdImage());
			ps.setString(5, newSeller.getPassword());

			ps.executeUpdate();

			System.out.println("Seller created Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	// Add other methods for updating, deleting, and retrieving sellers as needed

	// Example method to retrieve all active sellers
	public List<Seller> getAllActiveSellers() throws PersistenceException {
	        List<Seller> sellers = new ArrayList<>();
	        Connection conn = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        try {
	            String query = "SELECT * FROM sellers WHERE is_active = true";
	            conn = ConnectionUtil.getConnection();
	            ps = conn.prepareStatement(query);
	            rs = ps.executeQuery();

	            while (rs.next()) {
	                Seller seller = new Seller();
	                seller.setId(rs.getInt("id"));
	                seller.setName(rs.getString("name"));
	                seller.setEmail(rs.getString("email"));
	                seller.setProofImage(rs.getString("proof_image"));
	                seller.setIdImage(rs.getString("id_image"));
	                seller.setPassword(rs.getString("password"));
	                seller.setisActive(rs.getBoolean("is_active"));

	                sellers.add(seller);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new PersistenceException(e.getMessage());
	        } finally {
	            ConnectionUtil.close(conn, ps, rs);
	        }

	        return sellers;
	    }



	public void update(Seller seller) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE sellers SET name = ?, email = ?, proof_image = ?, id_image = ?, password = ? WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, seller.getName());
			ps.setString(2, seller.getEmail());
			ps.setString(3, seller.getProofImage());
			ps.setString(4, seller.getIdImage());
			ps.setString(5, seller.getPassword());
			ps.setInt(6, seller.getId());

			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated == 0) {
				throw new PersistenceException("Seller not found with ID: " + seller.getId());
			}

			System.out.println("Seller updated Successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}
	
	 public Seller findById(int sellerId) throws PersistenceException {
	        Seller seller = null;
	        Connection conn = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        try {
	            String query = "SELECT * FROM sellers WHERE id = ?";
	            conn = ConnectionUtil.getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setInt(1, sellerId);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                seller = new Seller();
	                seller.setId(rs.getInt("id"));
	                seller.setName(rs.getString("name"));
	                seller.setEmail(rs.getString("email"));
	                seller.setProofImage(rs.getString("proof_image"));
	                seller.setIdImage(rs.getString("id_image"));
	                seller.setPassword(rs.getString("password"));
	                seller.setisActive(rs.getBoolean("is_active"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new PersistenceException(e.getMessage());
	        } finally {
	            ConnectionUtil.close(conn, ps, rs);
	        }

	        return seller;
	    }
	 
	 
	 public Seller findByEmail(String email) throws PersistenceException {
	        Seller seller = null;
	        Connection conn = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        try {
	            String query = "SELECT * FROM sellers WHERE email = ?";
	            conn = ConnectionUtil.getConnection();
	            ps = conn.prepareStatement(query);
	            ps.setString(1, email);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                seller = new Seller();
	                seller.setId(rs.getInt("id"));
	                seller.setName(rs.getString("name"));
	                seller.setEmail(rs.getString("email"));
	                seller.setProofImage(rs.getString("proof_image"));
	                seller.setIdImage(rs.getString("id_image"));
	                seller.setPassword(rs.getString("password"));
	                seller.setisActive(rs.getBoolean("is_active"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new PersistenceException(e.getMessage());
	        } finally {
	            ConnectionUtil.close(conn, ps, rs);
	        }

	        return seller;
	    }

	 
	 public void checkSellerIdExist(int id) throws PersistenceException {
		    Connection con = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;

		    try {
		        String query = "SELECT name FROM sellers WHERE id = ?";
		        con = ConnectionUtil.getConnection();
		        ps = con.prepareStatement(query);
		        ps.setInt(1, id);
		        rs = ps.executeQuery();
		        
		        if (!rs.next()) {
		            throw new PersistenceException("Seller doesn't exist");
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
