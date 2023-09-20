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
	/**
	 * Creates a new seller in the data source.
	 *
	 * This method inserts a new seller into the data source, such as a database
	 * table. The provided Seller object contains the necessary information for
	 * creating the seller, including name, email, proof image, ID image, and
	 * password.
	 *
	 * @param newSeller A Seller object representing the new seller to be created.
	 * @throws PersistenceException If an error occurs while creating the seller.
	 */

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

	/**
	 * Retrieves a list of all active sellers.
	 *
	 * This method retrieves a list of all sellers from the data source where the
	 * 'is_active' flag is set to true. It returns the list of active sellers as a
	 * List of Seller objects.
	 *
	 * @return A List of Seller objects representing all active sellers, or an empty
	 *         List if no active sellers are found.
	 * @throws PersistenceException If an error occurs while retrieving the active
	 *                              sellers.
	 */

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

	/**
	 * Updates an existing seller's information in the data source.
	 *
	 * This method updates the information of an existing seller in the data source,
	 * such as a database table. The provided Seller object contains the updated
	 * information, including name, email, proof image, ID image, and password. The
	 * update is performed based on the seller's ID.
	 *
	 * @param seller A Seller object representing the updated seller information.
	 * @throws PersistenceException If an error occurs while updating the seller's
	 *                              information or if the seller with the specified
	 *                              ID is not found.
	 */

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

	/**
	 * Find a seller by their ID.
	 *
	 * This method retrieves a seller from the data source based on the provided
	 * seller ID. It queries the data source and fetches the seller's details using
	 * the specified ID. If a matching seller is found, the method creates a Seller
	 * object and returns it. If no matching seller is found, it returns null.
	 *
	 * @param sellerId The ID of the seller to be retrieved.
	 * @return A Seller object representing the seller with the specified ID, or
	 *         null if no matching seller is found.
	 * @throws PersistenceException If an error occurs while retrieving the seller.
	 */

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

	/**
	 * Find a seller by their email address.
	 *
	 * This method retrieves a seller from the data source based on the provided
	 * email address. It queries the data source and fetches the seller's details
	 * using the specified email address. If a matching seller is found, the method
	 * creates a Seller object and returns it. If no matching seller is found, it
	 * returns null.
	 *
	 * @param email The email address of the seller to be retrieved.
	 * @return A Seller object representing the seller with the specified email
	 *         address, or null if no matching seller is found.
	 * @throws PersistenceException If an error occurs while retrieving the seller.
	 */

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

	/**
	 * Check if a seller with the specified ID exists in the data source.
	 *
	 * This method queries the data source to determine if a seller with the
	 * provided ID exists. If a matching seller is found, the method does nothing.
	 * If no matching seller is found, it throws a PersistenceException with an
	 * appropriate error message.
	 *
	 * @param id The ID of the seller to check for existence.
	 * @throws PersistenceException If the seller with the specified ID does not
	 *                              exist or if an error occurs while checking.
	 */
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
