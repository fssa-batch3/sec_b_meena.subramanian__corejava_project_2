package in.fssa.kaithari.validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.Category;
import in.fssa.kaithari.util.ConnectionUtil;

public class CategoryValidator {
	/**
	 * 
	 * @param category
	 * @throws ValidationException
	 */
	public static void validateCategory(Category category) throws ValidationException {

		if (category == null) {
			throw new ValidationException("Invalid category input");
		}
		if (category.getId() < 0) {
			throw new ValidationException("Invalid category id");
		}

		// Business validation

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM categories WHERE name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, category.getName());
			rs = ps.executeQuery();

			if (rs.next()) {
				throw new ValidationException("Category name already exist");
			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);

		} finally {
			ConnectionUtil.close(con, ps);
		}

		validateName(category.getName());
	}

	/**
	 * 
	 * @param Categoryname
	 * @throws ValidationException
	 */

	public static void validateName(String Categoryname) throws ValidationException {

		if (Categoryname == null || "".equals(Categoryname.trim())) {
			throw new ValidationException("Name cannot be null or empty");
		}

		String regexp = "^[A-Za-z][A-Za-z\\\\\\\\s]*$";
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(Categoryname);

		if (!matcher.matches()) {
			throw new ValidationException("Name doesn't match the pattern");
		}
	}

	/**
	 * 
	 * @param categoryId
	 * @throws ValidationException
	 */
	public static void validateId(int categoryId) throws ValidationException {

		if (categoryId < 0) {
			throw new ValidationException("Invalid Id");
		}

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM categories WHERE id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, categoryId);
			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new ValidationException("Category id doesn't exist");
			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}
}