package in.fssa.kaithari.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.kaithari.exception.PersistenceException;
import in.fssa.kaithari.interfaces.ProductInterface;
import in.fssa.kaithari.model.Product;
import in.fssa.kaithari.util.ConnectionUtil;

public class ProductDAO implements ProductInterface {

	/**
	 * Creates a new product.
	 *
	 * This method creates a new product in the data source with the provided
	 * details. It uses the provided Product object to extract the necessary
	 * information and inserts the data into the appropriate table in the database.
	 * If the creation is successful (i.e., a row is affected), a success message is
	 * printed. If the creation fails, a PersistenceException is thrown with an
	 * appropriate error message.
	 *
	 * @param product The Product object containing the details of the product to be
	 *                created.
	 * @throws PersistenceException If an error occurs while persisting the product
	 *                              data.
	 */
	@Override
	public void create(Product product) throws PersistenceException {

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO products (product_name, category_id, description, price,user_id) VALUES (?,?,?,?,?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			
			ps.setString(1, product.getName());
			ps.setInt(2, product.getCategory_id());
			ps.setString(3, product.getDescription());
			ps.setInt(4, product.getPrice());
			ps.setInt(5, product.getUserId());

			int rowCreated = ps.executeUpdate();

			if (rowCreated > 0) {
				System.out.println("Product created Successfully");
			}

			else {
				throw new PersistenceException("Product Creation fails");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Updates an existing product.
	 *
	 * This method updates the details of an existing product in the data source
	 * with the provided Product object. It uses the provided Product object to
	 * extract the necessary information and updates the data in the appropriate
	 * table in the database. If the update operation is successful (i.e., rows are
	 * affected), a success message is printed. If the update operation fails, a
	 * PersistenceException is thrown with an appropriate error message.
	 *
	 * @param product The Product object containing the updated details of the
	 *                product.
	 * @throws PersistenceException If an error occurs while updating the product
	 *                              data.
	 */
	@Override
	public void updateProduct(int id, Product product) throws PersistenceException {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE products SET product_name = ?, category_id = ?, description = ?, price=? , user_id=? WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, product.getName());
			ps.setInt(2, product.getCategory_id());
			ps.setString(3, product.getDescription());
			ps.setInt(4, product.getPrice());
			ps.setInt(5,product.getUserId());
			ps.setInt(6, id);
			

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Product updated successfully");
			} else {
				System.out.println("product updated failed");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Updates the price of a product.
	 *
	 * This method updates the price of an existing product in the data source with
	 * the provided ID. The new price is specified through the `price` parameter. It
	 * uses the ID to locate the product to update and modifies the price according
	 * to the new value. If the update operation is successful (i.e., rows are
	 * affected), a success message is printed. If the update operation fails, a
	 * PersistenceException is thrown with an appropriate error message.
	 *
	 * @param id    The ID of the product to be updated.
	 * @param price The new price to be assigned to the product.
	 * @throws PersistenceException If an error occurs while updating the product
	 *                              price.
	 */
	@Override
	public void updatePrice(int id, int price) throws PersistenceException {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE products set price = ? WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, price);
			ps.setInt(2, id);

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Price updated successfully");
			} else {
				System.out.println("price updation fails");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		}finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	public Product productExists(String productname) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product product = null;

		try {
			String query = "SELECT * FROM products WHERE product_name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, productname);
			rs = ps.executeQuery();

			if (rs.next()) {
				product=new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("product_name"));
				product.setCategory_id(rs.getInt("category_id"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getInt("price"));
				product.setUserId(rs.getInt("user_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps);
		}

		return product;
	}

	/**
	 * Deletes a product.
	 *
	 * This method deletes a product from the data source with the provided ID. It
	 * uses the ID to locate the product to be deleted and removes it from the
	 * appropriate table in the database. If the deletion is successful (i.e., a row
	 * is affected), a success message is printed. If the deletion fails, a
	 * PersistenceException is thrown with an appropriate error message.
	 *
	 * @param id The ID of the product to be deleted.
	 * @throws PersistenceException If an error occurs while deleting the product.
	 */
	@Override
	public void deleteProduct(int id) throws PersistenceException {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "DELETE FROM products WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();

			System.out.println("Product deleted Successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Lists all products.
	 *
	 * This method retrieves all products from the data source and returns them as a
	 * Set of Product objects. It queries the database to fetch all product details
	 * and creates Product objects for each product found. These Product objects are
	 * added to a Set, which is then returned. If an SQLException occurs during the
	 * database operation, it is caught and handled, and a PersistenceException is
	 * thrown with an appropriate error message.
	 *
	 * @return A Set of all Product objects available in the data source.
	 * @throws PersistenceException If an error occurs while retrieving the
	 *                              products.
	 */

	@Override
	public Set<Product> listAllProducts() throws PersistenceException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Set<Product> allProducts = new HashSet<>();

		try {
			String query = "SELECT product_name,id,category_id,description,price,user_id FROM products";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("product_name"));
				product.setCategory_id(rs.getInt("category_id"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getInt("price"));
				product.setUserId(rs.getInt("user_id"));
				allProducts.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps);
		}
		return allProducts;
	}

	/**
	 * Lists all products by category ID.
	 *
	 * This method retrieves all products from the data source that belong to a
	 * specific category ID. It queries the database to fetch product details for
	 * the given category ID and creates Product objects for each product found.
	 * These Product objects are added to a Set, which is then returned. If an
	 * SQLException occurs during the database operation, it is caught and handled,
	 * and a PersistenceException is thrown with an appropriate error message.
	 *
	 * @param category_id The ID of the category for which to retrieve products.
	 * @return A Set of Product objects that belong to the specified category.
	 * @throws PersistenceException If an error occurs while retrieving the products
	 *                              by category ID.
	 */
	@Override
	public Set<Product> listAllProductsByCategoryId(int category_id) throws PersistenceException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Set<Product> listOfProductsByCategoryId = new HashSet<>();

		try {
			String query = "SELECT id, product_name, category_id, description, price,user_id FROM products WHERE category_id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, category_id);
			rs = ps.executeQuery();

			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("product_name"));
				product.setCategory_id(rs.getInt("category_id"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getInt("price"));
				product.setUserId(rs.getInt("user_id"));
				listOfProductsByCategoryId.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps);
		}
		return listOfProductsByCategoryId;

	}
	
	/**
	 * Retrieves a product from the database by its unique identifier (ID).
	 *
	 * This method establishes a database connection, executes an SQL query to fetch the product
	 * information based on the provided ID, and constructs a Product object with the retrieved data.
	 *
	 * @param id The unique identifier (ID) of the product to retrieve.
	 * @return A Product object representing the retrieved product from the database, or null if
	 *         no product with the specified ID is found.
	 * @throws PersistenceException If an error occurs while performing database operations, a
	 *         PersistenceException is thrown with an error message.
	*/
	public Product findProductById(int id) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Product product = null;
		

		try {
			String query = "SELECT id,product_name, category_id, description,price,user_id  FROM products WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				    product = new Product();
				    product.setId(rs.getInt("id"));
				    product.setName(rs.getString("product_name"));
	                product.setCategory_id(rs.getInt("category_id"));
	                product.setDescription(rs.getString("description"));
	                product.setPrice(rs.getInt("price"));
	                product.setUserId(rs.getInt("user_id"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new PersistenceException(e.getMessage());

		} finally {
			ConnectionUtil.close(con, ps,rs);
		}

		return product;

	}
	
	/**
	 * Retrieves a set of products associated with a specific user based on their user ID.
	 *
	 * This method establishes a database connection, executes an SQL query to fetch products
	 * that belong to the specified user, and constructs a set of Product objects with the retrieved data.
	 *
	 * @param user_id The unique identifier (ID) of the user for whom to retrieve products.
	 * @return A Set of Product objects representing the products associated with the specified user,
	 *         or an empty set if no products are found for the user.
	 * @throws PersistenceException If an error occurs while performing database operations, a
	 *         PersistenceException is thrown with an error message.
	 */
	public Set<Product> listAllProductsByUserId(int user_id) throws PersistenceException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Set<Product> listOfProductsByUserId = new HashSet<>();

		try {
			String query = "SELECT id, product_name, category_id, description, price,user_id FROM products WHERE user_id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, user_id);
			rs = ps.executeQuery();

			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("product_name"));
				product.setUserId(rs.getInt("user_id"));
				product.setCategory_id(rs.getInt("category_id"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getInt("price"));
				listOfProductsByUserId.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps);
		}
		return listOfProductsByUserId;

	}

}