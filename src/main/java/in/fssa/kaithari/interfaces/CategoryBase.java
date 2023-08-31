package in.fssa.kaithari.interfaces;

import in.fssa.kaithari.exception.PersistenceException;

public interface CategoryBase<T> {
	public abstract void create(T category) throws PersistenceException;

	public abstract void updateName(int id, String categoryName) throws PersistenceException;

	public abstract void delete(int id);
}
