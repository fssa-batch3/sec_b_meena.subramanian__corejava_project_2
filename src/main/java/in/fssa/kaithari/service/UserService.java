package in.fssa.kaithari.service;

import java.util.Set;

import in.fssa.kaithari.dao.UserDAO;
import in.fssa.kaithari.model.User;
import in.fssa.kaithari.validator.UserValidator;

public class UserService {
	/**
	 * 
	 * @param newUser
	 * @throws Exception
	 */

	public void create(User newUser) throws Exception {

		UserValidator userValidator = new UserValidator();

		userValidator.validate(newUser);

		UserDAO userDao = new UserDAO();

		userDao.create(newUser);
	}

	/**
	 * 
	 * @param id
	 * @param newName
	 * @throws Exception
	 */

	public void updateName(int id, String newName) throws Exception {

		UserValidator userValidator = new UserValidator();

		userValidator.validateUser(id);
		userValidator.validateName(newName);

		UserDAO userDao1 = new UserDAO();

		userDao1.updateName(id, newName);

	}

}
