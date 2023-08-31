package in.fssa.kaithari.interfaces;

import in.fssa.kaithari.exception.PersistenceException;

public interface ProductBase<T> {

	public abstract void create(T product) throws PersistenceException;

	public abstract void updatePrice(int id, int price) throws PersistenceException;

	public abstract void deleteProduct(int id) throws PersistenceException;

	public abstract void updateProduct(int id, T product) throws PersistenceException;

}
