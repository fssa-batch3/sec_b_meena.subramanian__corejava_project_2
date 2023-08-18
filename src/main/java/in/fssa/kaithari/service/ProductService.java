package in.fssa.kaithari.service;

import java.util.Set;

import in.fssa.kaithari.dao.ProductDAO;
import in.fssa.kaithari.model.Product;
import in.fssa.kaithari.validator.ProductValidator;

public class ProductService {

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Set<Product> listAllProduct() throws Exception {

		ProductDAO product = new ProductDAO();
		Set<Product> allProducts = product.listAllProducts();

		return allProducts;
	}

	/**
	 * 
	 * @param category_id
	 * @return
	 * @throws Exception
	 */

	public Set<Product> findProductByCategoryId(int category_id) throws Exception {
		ProductValidator validator = new ProductValidator();
		validator.validateCategoryId(category_id);

		ProductDAO productDAO = new ProductDAO();
		Set<Product> products = productDAO.listallProductsByCategoryId(category_id);

		return products;
	}

	/**
	 * 
	 * @param id
	 * @param price
	 * @throws Exception
	 */

	public void updateProductPrice(int id, int price) throws Exception {

		ProductValidator validator = new ProductValidator();
		validator.validateProductId(id);
		validator.validatePrice(price);

		ProductDAO productDAO = new ProductDAO();
		productDAO.updatePrice(id, price);

	}

	/**
	 * 
	 * @param product
	 * @throws Exception
	 */

	public void updateProduct(Product product) throws Exception {

		ProductValidator validator = new ProductValidator();

		validator.validateProductId(product.getId());
		validator.validateName(product.getName());
		validator.validateCategoryId(product.getCategory_id());
		validator.validateDescription(product.getDescription());
		validator.validatePrice(product.getPrice());

		ProductDAO productDAO = new ProductDAO();
		productDAO.updateProduct(product);

	}

	/**
	 * 
	 * @param product
	 * @throws Exception
	 */

	public void createProduct(Product product) throws Exception {

		ProductValidator validator = new ProductValidator();
		validator.validateProduct(product);

		ProductDAO productDAO = new ProductDAO();
		productDAO.create(product);

	}

	/**
	 * 
	 * @param productId
	 * @throws Exception
	 */

	public void deleteProduct(int productId) throws Exception {

		ProductValidator validator = new ProductValidator();
		validator.validateProductId(productId);

		ProductDAO productDAO = new ProductDAO();
		productDAO.deleteProduct(productId);

	}

}
