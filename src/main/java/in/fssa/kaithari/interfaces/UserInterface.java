package in.fssa.kaithari.interfaces;

import java.util.List;

import in.fssa.kaithari.exception.PersistenceException;
import in.fssa.kaithari.model.User;

public interface UserInterface extends UserBase<User> {

	public User findById(int id) throws PersistenceException;

	public User findByEmail(String email) throws PersistenceException;

	

}
