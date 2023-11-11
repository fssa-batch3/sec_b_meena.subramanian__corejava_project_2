package in.fssa.kaithari.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.kaithari.dao.CategoryDAO;
import in.fssa.kaithari.exception.PersistenceException;
import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.Category;

public class CategoryValidator {
	/**
	 * Validates a Category object to ensure it is not null, has a valid ID and a
	 * unique name.
	 *
	 * This method checks the provided Category object to ensure that it is not null
	 * and its attributes are valid. It verifies that the category ID is
	 * non-negative and that the category name is unique by querying the data
	 * source. If any validation check fails, a ValidationException is thrown with
	 * an appropriate error message.
	 *
	 * @param category The Category object to be validated.
	 * @throws ValidationException If the category is null, has an invalid ID, or a
	 *                             non-unique name.
	 * 
	 */
	public static void validateCategory(Category category) throws ValidationException {

		if (category == null) {
			throw new ValidationException("Invalid category input");
		}

		validateName(category.getName());

		CategoryDAO categoryDAO = new CategoryDAO();

		Category categoryCall = null;

		try {
			categoryCall = categoryDAO.findByCategoryName(category.getName());
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}
		if (categoryCall != null) {
			throw new ValidationException("product name already exists");
		}

	}

	public static void validateCategoryId(int id) throws ValidationException {
		if (id < 1) {
			throw new ValidationException("Invalid category id");
		}
	}

	/**
	 * Validates a category name to ensure it is not null, not empty, and follows a
	 * specific pattern.
	 *
	 * This method checks the provided category name to ensure that it is not null,
	 * not empty, and follows a specific pattern represented by a regular
	 * expression. If the name is found to be null, empty, or not matching the
	 * pattern, a ValidationException is thrown with an appropriate error message.
	 *
	 * @param categoryName The category name to be validated.
	 * @throws ValidationException If the category name is null, empty, or doesn't
	 *                             match the pattern.
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
	 * Validates a category ID to ensure it is a non-negative value and doesn't
	 * already exist.
	 *
	 * This method checks the provided category ID to ensure that it is a
	 * non-negative integer and that it does not correspond to an existing category
	 * in the data source. It queries the data source to find a category with the
	 * given ID. If the ID is negative or corresponds to an existing category, a
	 * ValidationException is thrown with an appropriate error message.
	 *
	 * @param categoryId The category ID to be validated.
	 * @throws ValidationException If the category ID is negative or already exists.
	 */
	public static void validateId(int categoryId) throws ValidationException {

		if (categoryId < 0) {
			throw new ValidationException("Invalid Id");
		}

		CategoryDAO categoryDAO = new CategoryDAO();

		Category category = null;

		try {
			category = new Category();
			category = categoryDAO.findByCategoryId(categoryId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

		if (category == null) {
			throw new ValidationException("category not exists");
		}
	}

	/**
	 * Validate the category ID for an update operation.
	 *
	 * This method is responsible for validating a category ID before performing an
	 * update operation. It checks whether the provided category ID is valid, exists
	 * in the data source, and is greater than or equal to zero.
	 *
	 * @param categoryId The category ID to be validated.
	 * @throws ValidationException If the provided category ID is not valid, is
	 *                             negative, or does not exist in the data source.
	 */

	public static void validateUpdateId(int categoryId) throws ValidationException {

		if (categoryId < 0) {
			throw new ValidationException("Invalid Id");
		}

		CategoryDAO categoryDAO = new CategoryDAO();

		Category category = null;

		try {
			category = new Category();
			category = categoryDAO.findByCategoryId(categoryId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

		if (category == null) {
			throw new ValidationException("id already not exists");
		}
	}
}
