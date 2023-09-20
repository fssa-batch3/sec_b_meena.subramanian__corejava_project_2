package in.fssa.kaithari.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import in.fssa.kaithari.exception.PersistenceException;
import in.fssa.kaithari.interfaces.CategoryInterFace;
import in.fssa.kaithari.model.Category;
import in.fssa.kaithari.util.ConnectionUtil;

public class CategoryDAO implements CategoryInterFace {

	/**
	 * Creates a new category.
	 *
	 * This method creates a new category in the data source with the provided
	 * details. It uses the provided Category object to extract the necessary
	 * information and inserts the data into the appropriate table in the database.
	 * If an SQLException occurs during the database operation, it is caught and
	 * handled, and a PersistenceException is thrown with an appropriate error
	 * message.
	 *
	 * @param category The Category object containing the details of the category to
	 *                 be created.
	 * @throws PersistenceException If an error occurs while persisting the category
	 *                              data.
	 */
	@Override
	public void create(Category category) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO categories (name) values(?)";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, category.getName());

			ps.executeUpdate();

			System.out.println("Category created Successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Updates the name of a category.
	 *
	 * This method updates the name of an existing category in the data source with
	 * the provided ID. The new name is specified through the `categoryName`
	 * parameter. It uses the ID to locate the category to update, then modifies the
	 * name according to the new value. If the update operation is successful (i.e.,
	 * rows are affected), a success message is printed. If an SQLException occurs
	 * during the database operation, it is caught and handled, and a
	 * PersistenceException is thrown with an appropriate error message.
	 *
	 * @param id           The ID of the category to be updated.
	 * @param categoryName The new name to be assigned to the category.
	 * @throws PersistenceException If an error occurs while updating the category
	 *                              name.
	 */

	@Override
	public void updateName(int id, String categoryName) throws PersistenceException {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE categories set name = ? WHERE id = ?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, categoryName);
			ps.setInt(2, id);

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("category name updated successfully");
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}finally {
			ConnectionUtil.close(conn, ps);
		}
		
	}
	


	@Override
	public void delete(int id) {


	}

	/**
	 * Retrieves a list of all categories.
	 *
	 * This method retrieves a list of all categories from the data source, such as a database table,
	 * and returns them as a Set of Category objects.
	 *
	 * @return A Set of Category objects representing all available categories,
	 *         or an empty Set if no categories are found.
	 * @throws PersistenceException If an error occurs while retrieving the categories.
	 */
	
	public Set<Category> listAllCategroy() throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Category category = null;
		
		Set<Category> listofCategory=new HashSet<>();

		try {
			String query = "SELECT id,name FROM categories";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();

			while (rs.next()) {
				category=new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				
				listofCategory.add(category);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps);
		}

		return listofCategory;
	}
  
	/**
	 * Finds a category by its ID.
	 *
	 * This method retrieves a category from the data source based on the provided
	 * ID. It uses the ID to query the database and fetches the corresponding
	 * category's details. If a matching category is found, its information is
	 * extracted from the ResultSet and used to create a Category object. The
	 * created Category object is then returned. If an SQLException occurs during
	 * the database operation, it is caught and handled, and a PersistenceException
	 * is thrown with an appropriate error message.
	 *
	 * @param id The ID of the category to be retrieved.
	 * @return The retrieved Category object, or null if no matching category is
	 *         found.
	 * @throws PersistenceException If an error occurs while retrieving the
	 *                              category.
	 */

	public Category findByCategoryId(int id) throws PersistenceException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Category category = null;

		try {
			String query = "SELECT id,name FROM categories WHERE id=?";
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				category=new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistenceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps);
		}

		return category;

	}

	/**
	 * Finds a category by its name.
	 *
	 * This method retrieves a category from the data source based on the provided
	 * category name. It uses the category name to query the database and fetches
	 * the corresponding category's details. If a matching category is found, its
	 * information is extracted from the ResultSet and used to create a Category
	 * object. The created Category object is then returned. If an SQLException
	 * occurs during the database operation, it is caught and handled, and a
	 * PersistenceException is thrown with an appropriate error message.
	 *
	 * @param categoryName The name of the category to be retrieved.
	 * @return The retrieved Category object, or null if no matching category is
	 *         found.
	 * @throws PersistenceException If an error occurs while retrieving the
	 *                              category.
	 */

	public Category findByCategoryName(String categoryName) throws PersistenceException {

	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Category category = null;

	    try {
	        String query = "SELECT id, name FROM categories WHERE name=?";
	        conn = ConnectionUtil.getConnection();
	        ps = conn.prepareStatement(query);
	        ps.setString(1, categoryName);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            category = new Category();  
	            category.setId(rs.getInt("id"));
	            category.setName(rs.getString("name"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("DAO cat");
	        System.out.println(e.getMessage());
	        throw new PersistenceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(conn, ps, rs);  
	    }

	    return category;
	}

	@Override
	public Set<Category> listAllCategroyByCategoryId(int categoryId) {

		return null;
	}



}
