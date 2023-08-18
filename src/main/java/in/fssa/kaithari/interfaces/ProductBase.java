package in.fssa.kaithari.interfaces;

public interface ProductBase<T> {

	public abstract void create(T product);

	public abstract void updatePrice(int id, int price);

	public abstract void deleteProduct(int id);

	public abstract void updateProduct(T product);

}
