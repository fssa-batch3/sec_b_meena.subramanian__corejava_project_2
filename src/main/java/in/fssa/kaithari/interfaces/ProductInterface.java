package in.fssa.kaithari.interfaces;

import java.util.Set;

import in.fssa.kaithari.model.Product;

public interface ProductInterface extends ProductBase<Product> {

	public abstract Set<Product> listAllProducts();

	public abstract Set<Product> listAllProductsByCategoryId(int category_id);


}