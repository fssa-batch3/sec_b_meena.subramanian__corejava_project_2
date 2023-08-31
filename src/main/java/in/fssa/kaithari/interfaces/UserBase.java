package in.fssa.kaithari.interfaces;

import java.util.Set;

import in.fssa.kaithari.exception.PersistenceException;
import in.fssa.kaithari.model.User;

public interface UserBase<T> {

	public abstract void create(T object) throws PersistenceException;

	public abstract void updateName(int id, String newName) throws PersistenceException;

	public abstract void findAllUsers() throws PersistenceException;

	//void findAllUsers(int id) throws PersistenceException;

//	void findAllUsers(int id) throws PersistenceException;

	

}