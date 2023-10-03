package in.fssa.kaithari.service;

import in.fssa.kaithari.dao.UserDAO;
import in.fssa.kaithari.exception.PersistenceException;
import in.fssa.kaithari.exception.ServiceException;
import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.User;
import in.fssa.kaithari.validator.UserValidator;

public class UserService {
	/**
	 * Creates a new user.
	 *
	 * This method creates a new user based on the provided User object. It first
	 * validates the attributes of the user using the UserValidator.validate method.
	 * If the validation is successful, the User object is passed to the UserDAO to
	 * perform the creation operation. If the creation operation is successful, the
	 * new user is added to the data source. If the creation operation fails, a
	 * ServiceException is thrown with an appropriate error message. If the
	 * validation operation throws a ValidationException, it is caught and re-thrown
	 * as a ServiceException.
	 *
	 * @param newUser The User object containing the information of the new user.
	 * @throws ServiceException    If there is an issue with validation or
	 *                             persistence during user creation.
	 * @throws ValidationException If any of the user's attributes fail validation.
	 */
	public void create(User newUser) throws ServiceException, ValidationException {

		UserValidator.validate(newUser);

		UserDAO userDAO = new UserDAO();

		try {
			userDAO.create(newUser);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	public User findByEmail(String email) throws ServiceException, ValidationException {

		UserDAO userDAO = new UserDAO();

		try {
			return userDAO.findByEmail(email);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Updates the name of a user by their ID.
	 *
	 * This method updates the name of a user based on the provided user ID and new
	 * name. It first validates the user ID using the UserValidator.validateUser
	 * method and the new name using the UserValidator.validateName method. If the
	 * validation is successful, the user's ID and new name are passed to the
	 * UserDAO to perform the update operation. If the update operation is
	 * successful, the user's name is updated in the data source. If the update
	 * operation fails, a ServiceException is thrown with an appropriate error
	 * message. If the validation operation throws a ValidationException, it is
	 * caught and re-thrown as a ServiceException.
	 *
	 * @param id      The ID of the user whose name needs to be updated.
	 * @param newName The new name to be assigned to the user.
	 * @throws ServiceException     If there is an issue with validation or
	 *                              persistence during name update.
	 * @throws ValidationException  If the provided user ID or new name fails
	 *                              validation.
	 * @throws PersistenceException
	 */

	public void updateName(int id, String newName) throws ServiceException, ValidationException, PersistenceException {

		UserValidator.validateUser(id);
		UserValidator.checkUserIdExist(id);
		UserValidator.validateName(newName);

		UserDAO userDAO1 = new UserDAO();

		try {
			userDAO1.updateName(id, newName);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	public void updateAddress(int id, String newName, String newAddress, String newDistrict,
	        long newMobileNumber, int newPincode, String newVillage)
	        throws ServiceException, ValidationException, PersistenceException {
	    UserValidator.validateUser(id);
	    UserValidator.checkUserIdExist(id);
	    UserValidator.validateName(newName);
	    UserValidator.validateAddress(newAddress);
	    UserValidator.validateDistrict(newDistrict);
	    UserValidator.validatePincode(newPincode);
	    UserValidator.validateVillage(newVillage);
	    UserValidator.validateMobileNumber(newMobileNumber);

		UserDAO userDAO1 = new UserDAO();

		try {
			userDAO1.updateName(id, newName);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Find a user by their ID.
	 *
	 * This method retrieves a user from the data source based on the provided user
	 * ID. It utilizes a UserDAO instance to interact with the data source and
	 * perform the retrieval.
	 *
	 * @param id The ID of the user to be retrieved.
	 * @return A User object representing the user with the specified ID, or null if
	 *         no matching user is found.
	 * @throws ServiceException    If an error occurs during the retrieval of the
	 *                             user or if input validation fails.
	 * @throws ValidationException If the provided user ID is not valid or if other
	 *                             input validation checks fail.
	 */
	public  User findById(int id) throws ServiceException, ValidationException {

		UserDAO userDAO = new UserDAO();

		try {
			return userDAO.findById(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

}
