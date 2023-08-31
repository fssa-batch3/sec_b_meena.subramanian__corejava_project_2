package in.fssa.kaithari.validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import in.fssa.kaithari.dao.CategoryDAO;
import in.fssa.kaithari.dao.ProductDAO;
import in.fssa.kaithari.exception.PersistenceException;
import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.Category;
import in.fssa.kaithari.model.Product;
import in.fssa.kaithari.util.ConnectionUtil;
import in.fssa.kaithari.util.StringUtil;

public class ProductValidator {

	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\s]*$";

	
	/**
	 * Validates a product before it is processed further.
	 *
	 * @param product The Product object to be validated.
	 * @throws ValidationException If the product is invalid or does not pass
	 *                             validation checks.
	 */
	public  void validateProduct(Product product) throws ValidationException {
		// Check if the input product is null
		if (product == null) {
			throw new ValidationException("Invalid Product input");
		}
		// Check if the product already exists in the database
		ProductDAO productDAO = new ProductDAO();
		Product ProductCall = null;

		try {
			ProductCall=new Product();
			ProductCall = productDAO.productExists(product.getName());
		} catch (PersistenceException e) {
			// An error occurred while accessing the database
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

		if (ProductCall != null) {
			throw new ValidationException("product already exist");
		}

		// Perform various validation checks on the product attributes
		//validateProductId1(product.getId());
		validateName(product.getName());
		validateDescription(product.getDescription());
		validatePrice(product.getPrice());
		validateCategoryId(product.getCategory_id());

	}

	/**
	 * Validates the name of a product.
	 *
	 * @param name The name to be validated.
	 * @throws ValidationException If the name is invalid or does not match the
	 *                             expected pattern.
	 */

	public void validateName(String name) throws ValidationException {
		// Reject if the name is empty or null
		StringUtil.rejectIfInvalidString(name, "Name");

		// Check if the name matches the pattern
		if (!Pattern.matches(NAME_PATTERN, name)) {
			throw new ValidationException("Name does not match the pattern");
		}

	}

	/**
	 * Validates a description string to ensure it is not null or empty.
	 *
	 * This method checks the provided description string to ensure that it is not
	 * null or empty. If the description is found to be invalid, a
	 * ValidationException is thrown with a corresponding error message.
	 *
	 * @param description The description string to be validated.
	 * @throws ValidationException If the description is null or empty.
	 */

	public void validateDescription(String description) throws ValidationException {

		StringUtil.rejectIfInvalidString(description, "description");
	}

	/**
	 * Validates a price to ensure it is a positive value.
	 *
	 * This method checks the provided price value to ensure that it is a positive
	 * integer. If the price is found to be non-positive (zero or negative), a
	 * ValidationException is thrown with a corresponding error message.
	 *
	 * @param price The price value to be validated.
	 * @throws ValidationException If the price is zero or negative.
	 */

	public void validatePrice(int price) throws ValidationException {

		if (price <= 0) {
			throw new ValidationException("Price cannot be negative or zero");
		}
	}

	/**
	 * Validates a product ID to ensure it is a non-negative value.
	 *
	 * This method checks the provided product ID to ensure that it is a
	 * non-negative integer. If the product ID is found to be negative, a
	 * ValidationException is thrown with a corresponding error message.
	 *
	 * @param productId The product ID to be validated.
	 * @throws ValidationException If the product ID is negative.
	 */
	public void validateProductId1(int productId) throws ValidationException {
		if (productId < 0) {
			throw new ValidationException("Id cannot be negative or zero");
		}
	}

	/**
	 * Validates a product ID to ensure it is a positive value and does not already
	 * exist.
	 *
	 * This method checks the provided product ID to ensure that it is a positive
	 * integer and that it does not already correspond to an existing product. It
	 * does so by querying a data source, and if the product ID is found to be
	 * non-positive or already associated with an existing product, a
	 * ValidationException is thrown with an appropriate error message.
	 *
	 * @param productId The product ID to be validated.
	 * @throws ValidationException If the product ID is non-positive or already
	 *                             exists.
	 */
	public void validateProductId(int productId) throws ValidationException {

		if (productId <= 0) {

			throw new ValidationException("Id cannot be negative or zero");
		}

		ProductDAO productDAO = new ProductDAO();
		Product product = null;

		try {
			product = productDAO.findProductById(productId);
		} catch (PersistenceException e) {

			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

		if (product == null) {
			throw new ValidationException("product id not exists");
		}

	}

	/**
	 * Validates a category ID to ensure it is a positive value and corresponds to
	 * an existing category.
	 *
	 * This method checks the provided category ID to ensure that it is a positive
	 * integer and that it corresponds to an existing category in the data source.
	 * It queries the data source to find a category with the given ID. If the ID is
	 * non-positive or does not correspond to an existing category, a
	 * ValidationException is thrown with an appropriate error message.
	 *
	 * @param categoryId The category ID to be validated.
	 * @throws ValidationException If the category ID is non-positive or the
	 *                             category does not exist.
	 */
	public void validateCategoryId(int categoryId) throws ValidationException {

		if (categoryId <= 0) {
			throw new ValidationException("Category ID cannot be negative or zero");
		}

		CategoryDAO categoryDAO = new CategoryDAO();

		Category category = null;

		try {
			category = categoryDAO.findByCategoryId(categoryId);
		} catch (PersistenceException e) {

			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

		if (category == null) {
			throw new ValidationException("category not found");
		}

	}
}