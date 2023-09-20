package in.fssa.kaithari.validator;

import in.fssa.kaithari.dao.SellerDAO;
import in.fssa.kaithari.exception.PersistenceException;
import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.Seller;
import in.fssa.kaithari.util.StringUtil;

public class SellerValidator {
	/**
	 * Validate a Seller object before performing operations.
	 *
	 * This method is responsible for validating a Seller object before performing
	 * various operations related to sellers. It checks whether the provided Seller
	 * object is valid according to predefined validation rules.
	 *
	 * @param seller A Seller object to be validated.
	 * @throws ValidationException If the provided Seller object is not valid
	 *                             according to predefined validation rules.
	 */

	public static void validate(Seller seller) throws ValidationException {
		if (seller == null) {
			throw new ValidationException("Invalid seller input");
		}

		StringUtil.rejectIfInvalidString(seller.getName(), "Name");
		StringUtil.rejectIfInvalidString(seller.getEmail(), "Email");
		StringUtil.rejectIfInvalidString(seller.getProofImage(), "Proof Image");
		StringUtil.rejectIfInvalidString(seller.getIdImage(), "ID Image");
		StringUtil.rejectIfInvalidString(seller.getPassword(), "Password");

		validateName(seller.getName());
		validateEmail(seller.getEmail());
		validatePassword(seller.getPassword());

	}

	/**
	 * Validate a seller's name.
	 *
	 * This method is responsible for validating a seller's name according to
	 * predefined validation rules.
	 *
	 * @param name The name of the seller to be validated.
	 * @throws ValidationException If the provided name is empty, null, or does not
	 *                             meet the required format.
	 */

	private static void validateName(String name) throws ValidationException {
		if (name == null || name.trim().isEmpty()) {
			throw new ValidationException("Name cannot be empty");
		}
		if (!name.matches("^[A-Za-z][A-Za-z\\\\s]*$")) {
			throw new ValidationException("Invalid Name format");
		}

	}

	/**
	 * Validate an email address.
	 *
	 * This method is responsible for validating an email address according to
	 * predefined validation rules.
	 *
	 * @param email The email address to be validated.
	 * @throws ValidationException If the provided email address is empty, null, or
	 *                             does not meet the required format.
	 */

	private static void validateEmail(String email) throws ValidationException {
		if (email == null || email.trim().isEmpty()) {
			throw new ValidationException("Email cannot be empty");
		}
		if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new ValidationException("Invalid email format");
		}
	}

	/**
	 * Validate a password.
	 *
	 * This method is responsible for validating a password according to predefined
	 * validation rules.
	 *
	 * @param password The password to be validated.
	 * @throws ValidationException If the provided password is empty, null, or does
	 *                             not meet the required format.
	 */

	private static void validatePassword(String password) throws ValidationException {
		if (password == null || password.trim().isEmpty()) {
			throw new ValidationException("Password cannot be empty");
		}
		if (!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$")) {
			throw new ValidationException("Invalid Password format");
		}
	}

	/**
	 * Validate a seller ID.
	 *
	 * This method is responsible for validating a seller ID according to predefined
	 * validation rules.
	 *
	 * @param id The seller ID to be validated.
	 * @throws ValidationException If the provided seller ID is less than 1
	 *                             (invalid), a ValidationException is thrown with
	 *                             an appropriate error message.
	 */

	public static void validateSeller(int id) throws ValidationException {
		if (id < 1) {
			throw new ValidationException("Invalid seller id");
		}
	}

	/**
	 * Check if a seller with the specified ID exists.
	 *
	 * This method is responsible for checking whether a seller with the provided ID
	 * exists in the data source.
	 *
	 * @param sellerId The ID of the seller to be checked for existence.
	 * @throws ValidationException  If the provided seller ID is less than 1
	 *                              (invalid), a ValidationException is thrown with
	 *                              an appropriate error message.
	 * @throws PersistenceException If an error occurs during the check or if the
	 *                              seller with the specified ID does not exist, a
	 *                              PersistenceException is thrown with an
	 *                              appropriate error message.
	 */

	public static void checkSellerIdExist(int sellerId) throws ValidationException, PersistenceException {
		SellerDAO sellerDAO = new SellerDAO();
		sellerDAO.checkSellerIdExist(sellerId);
	}
}
