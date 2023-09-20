package in.fssa.kaithari.validator;

import java.util.regex.Pattern;

import in.fssa.kaithari.dao.UserDAO;
import in.fssa.kaithari.exception.PersistenceException;
import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.User;

import in.fssa.kaithari.util.StringUtil;

public class UserValidator {

	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\s]*$";
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+([a-zA-Z0-9_+\\-\\. ]*[a-zA-Z0-9]+)?@[a-zA-Z0-9]+([a-zA-Z0-9\\-\\.]*[a-zA-Z0-9])?\\.[a-zA-Z]{2,}$";
	private static final String P_PATTERN = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";

	/**
	 * Validates a User object to ensure it is not null and its attributes are
	 * valid.
	 *
	 * This method checks the provided User object to ensure that it is not null and
	 * that its attributes (name, email, and password) are valid. It verifies that
	 * the attributes are not null or empty using a utility method and throws a
	 * ValidationException with an appropriate error message if any of the checks
	 * fail.
	 *
	 * @param user The User object to be validated.
	 * @throws ValidationException If the user is null or any of its attributes are
	 *                             invalid.
	 */

	public static void validate(User user) throws ValidationException {
		if (user == null) {
			throw new ValidationException("invalid user input");
		}

		StringUtil.rejectIfInvalidString(user.getName(), "Name");
		StringUtil.rejectIfInvalidString(user.getEmail(), "email");
		StringUtil.rejectIfInvalidString(user.getPassword(), "password");

		validateName(user.getName());
		validateEmail(user.getEmail());
		validatePassword(user.getPassword());

	}

	/**
	 * Validates a user ID to ensure it is a positive value and corresponds to an
	 * existing user.
	 *
	 * This method checks the provided user ID to ensure that it is a positive
	 * integer and that it corresponds to an existing user in the data source. It
	 * queries the data source to find a user with the given ID. If the ID is
	 * non-positive or does not correspond to an existing user, a
	 * ValidationException is thrown with an appropriate error message.
	 *
	 * @param id The user ID to be validated.
	 * @throws ValidationException If the user ID is non-positive or the user does
	 *                             not exist.
	 */

	public static void validateUser(int id) throws ValidationException {

		if (id < 1) {
			throw new ValidationException("Invalid user id");
		}

	}

	/**
	 * Validates a name to ensure it is not null, not empty, and matches a specific
	 * pattern.
	 *
	 * This method checks the provided name to ensure that it is not null, not
	 * empty, and matches a specific pattern represented by a regular expression. It
	 * uses a utility method to perform the basic null and empty checks, and if the
	 * name does not match the pattern, a ValidationException is thrown with an
	 * appropriate error message.
	 *
	 * @param name The name to be validated.
	 * @throws ValidationException If the name is null, empty, or doesn't match the
	 *                             pattern.
	 */

	public static void validateName(String name) throws ValidationException {

		StringUtil.rejectIfInvalidString(name, "Name");

		if (!Pattern.matches(NAME_PATTERN, name)) {
			throw new ValidationException("Name does not match the pattern");
		}

	}

	public static void checkUserIdExist(int userId) throws ValidationException, PersistenceException {

		UserDAO userDAO = new UserDAO();
		userDAO.CheckIdExist(userId);
	}

	/**
	 * Validates an email address to ensure it is not null, not empty, matches a
	 * specific pattern, and corresponds to an existing user.
	 *
	 * This method checks the provided email address to ensure that it is not null,
	 * not empty, matches a specific pattern represented by a regular expression,
	 * and corresponds to an existing user in the data source. It uses a utility
	 * method to perform the basic null and empty checks, and if the email does not
	 * match the pattern or the user is not found, a ValidationException is thrown
	 * with an appropriate error message.
	 *
	 * @param email The email address to be validated.
	 * @throws ValidationException If the email is null, empty, doesn't match the
	 *                             pattern, or the user does not exist.
	 */
	public static void validateEmail(String email) throws ValidationException {

		StringUtil.rejectIfInvalidString(email, "Email");

		if (!Pattern.matches(EMAIL_PATTERN, email)) {
			throw new ValidationException("Email does not match the pattern");
		}
		UserDAO userDAO = new UserDAO();
		User user = new User();

		try {
			userDAO.findByEmail(email);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());

		}
	}

	/**
	 * Validates a password to ensure it is not null, not empty, meets length
	 * requirements, and matches a specific pattern.
	 *
	 * This method checks the provided password to ensure that it is not null, not
	 * empty, meets the minimum length requirement, and matches a specific pattern
	 * represented by a regular expression. It uses a utility method to perform the
	 * basic null and empty checks, and if the password doesn't meet the
	 * requirements or the pattern, a ValidationException is thrown with an
	 * appropriate error message.
	 *
	 * @param password The password to be validated.
	 * @throws ValidationException If the password is null, empty, doesn't meet
	 *                             length requirements, or doesn't match the
	 *                             pattern.
	 */

	public static void validatePassword(String password) throws ValidationException {

		if (password.length() < 8) {
			throw new ValidationException("Password must contain atleast 8 characters");
		}

		if (!Pattern.matches(P_PATTERN, password)) {
			throw new ValidationException("Password does not match the pattern");
		}
	}

	public static void validateAddress(String address) throws ValidationException {
		if (address == null || address.trim().isEmpty()) {
			throw new ValidationException("Address cannot be null or empty");
		}
	}

	public static void validateDistrict(String district) throws ValidationException {
		if (district == null || district.trim().isEmpty()) {
			throw new ValidationException("District cannot be null or empty");
		}
		String districtPattern = "^[A-Za-z\\s-]+$"; // Adjust the pattern as needed

		if (!Pattern.matches(districtPattern, district)) {
			throw new ValidationException("District does not match the expected pattern");
		}
	}

	public static void validateMobileNumber(long newMobileNumber) throws ValidationException {
		long minValidMobileNumber = 1000000000L; // Represents the minimum 10-digit mobile number
		long maxValidMobileNumber = 9999999999L; // Represents the maximum 10-digit mobile number

		if (newMobileNumber < minValidMobileNumber || newMobileNumber > maxValidMobileNumber) {
			throw new ValidationException("Mobile number does not match the expected pattern");
		}

	}

	public static void validatePincode(int pincode) throws ValidationException {
		if (pincode < 100000 || pincode > 999999) {
			throw new ValidationException("PIN code does not match the expected pattern");
		}
	}

	public static void validateVillage(String village) throws ValidationException {
		if (village == null || village.trim().isEmpty()) {
			throw new ValidationException("Village name cannot be null or empty");
		}
		if (!village.matches("^[A-Za-z\\s]+$")) {
			throw new ValidationException("Village name should contain only letters and spaces");
		}
	}
}