package in.fssa.kaithari.service;

import java.util.Set;

import in.fssa.kaithari.dao.ProductDAO;
import in.fssa.kaithari.exception.PersistenceException;
import in.fssa.kaithari.exception.ServiceException;
import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.Product;
import in.fssa.kaithari.validator.ProductValidator;

public class ProductService {
	/**
	 * Retrieves a set of all products.
	 *
	 * This method retrieves a set of all products available in the data source. It
	 * first invokes the listAllProducts method from the ProductDAO class to fetch
	 * all products. If the operation is successful, the retrieved set of products
	 * is returned. If the operation fails, a ServiceException is thrown with an
	 * appropriate error message.
	 *
	 * @return A set containing all available products.
	 * @throws ServiceException If there is an issue with persistence during product
	 *                          listing.
	 */
	public Set<Product> listAllProduct() throws ServiceException, ValidationException {

		ProductDAO product = new ProductDAO();
		Set<Product> allProducts;
		try {
			allProducts = product.listAllProducts();
			return allProducts;
		} catch (PersistenceException e) {

			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Retrieves a set of products by category ID.
	 *
	 * This method retrieves a set of products that belong to the specified category
	 * ID. It first validates the provided category ID using the
	 * ProductValidator.validateCategoryId method. If the validation is successful,
	 * the category ID is passed to the ProductDAO to fetch the products associated
	 * with that category. If the operation is successful, the retrieved set of
	 * products is returned. If the operation fails, a ServiceException is thrown
	 * with an appropriate error message.
	 *
	 * @param category_id The ID of the category for which products need to be
	 *                    retrieved.
	 * @return A set of products belonging to the specified category ID.
	 * @throws ServiceException    If there is an issue with validation or
	 *                             persistence during product retrieval.
	 * @throws ValidationException If the provided category ID fails validation.
	 */

	public Set<Product> findProductByCategoryId(int category_id) throws ServiceException, ValidationException {
		ProductValidator validator = new ProductValidator();
		validator.validateCategoryId(category_id);

		ProductDAO productDAO = new ProductDAO();
		Set<Product> products;
		try {
			products = productDAO.listAllProductsByCategoryId(category_id);
			return products;
		} catch (PersistenceException e) {

			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	/**
	 * Retrieves a product by its ID.
	 *
	 * This method retrieves a product that matches the provided product ID.
	 * If the operation is successful, the retrieved product is returned.
	 * If the operation fails, a ServiceException is thrown with an appropriate error message.
	 *
	 * @param id The ID of the product to retrieve.
	 * @return The product with the specified ID.
	 * @throws ServiceException If there is an issue with persistence during product retrieval.
	 */
	public Product findProductById(int id) throws ServiceException {
	    ProductDAO productDAO = new ProductDAO();
	    ProductValidator productValidator = new ProductValidator();
		productValidator.validateProductId(id);
	    try {
	        return productDAO.findProductById(id);
	    } catch (PersistenceException e) {
	        e.printStackTrace();
	        throw new ServiceException(e.getMessage());
	    }
	}
	

	/**
	 * Updates the price of a product by its ID.
	 *
	 * This method updates the price of a product based on the provided product ID
	 * and the new price. It first validates the provided product ID using the
	 * ProductValidator.validateProductId method and the new price using the
	 * ProductValidator.validatePrice method. If both validations are successful,
	 * the product's ID and the new price are passed to the ProductDAO to perform
	 * the update operation. If the update operation is successful, the product's
	 * price is modified in the data source. If the update operation fails, a
	 * ServiceException is thrown with an appropriate error message. If either
	 * validation operation throws a ValidationException, they are caught and
	 * re-thrown as ServiceException instances.
	 *
	 * @param id    The ID of the product to be updated.
	 * @param price The new price to update for the product.
	 * @throws ServiceException    If there is an issue with validation or
	 *                             persistence during product price update.
	 * @throws ValidationException If the provided product ID or new price fails
	 *                             validation.
	 */
	

	public void updateProductPrice(int id, int price) throws ServiceException, ValidationException {

		ProductValidator validator = new ProductValidator();
		validator.validateProductId(id);
		validator.validatePrice(price);

		ProductDAO productDAO = new ProductDAO();
		try {
			productDAO.updatePrice(id, price);
		} catch (PersistenceException e) {

			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Updates a product's information.
	 *
	 * This method updates the information of a product based on the provided
	 * Product object. It first validates the various attributes of the product
	 * using the appropriate validation methods from the ProductValidator class. If
	 * all validations are successful, the Product object is passed to the
	 * ProductDAO to perform the update operation. If the update operation is
	 * successful, the product's information is modified in the data source. If the
	 * update operation fails, a ServiceException is thrown with an appropriate
	 * error message. If any of the validation operations throws a
	 * ValidationException, they are caught and re-thrown as ServiceException
	 * instances.
	 *
	 * @param product The Product object containing the updated information.
	 * @throws ServiceException    If there is an issue with validation or
	 *                             persistence during product update.
	 * @throws ValidationException If any of the product's attributes fail
	 *                             validation.
	 */
	public void updateProduct(int id, Product product) throws ServiceException, ValidationException {

		ProductValidator validator = new ProductValidator();

		validator.validateProductId(id);
		validator.validateName(product.getName());
		validator.validateCategoryId(product.getCategory_id());
		validator.validateDescription(product.getDescription());
		validator.validatePrice(product.getPrice());

		ProductDAO productDAO = new ProductDAO();
		try {
			productDAO.updateProduct(id, product);
		} catch (PersistenceException e) {

			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Creates a new product.
	 *
	 * This method creates a new product based on the provided Product object. It
	 * first validates the attributes of the product using the
	 * ProductValidator.validateProduct method. If the validation is successful, the
	 * Product object is passed to the ProductDAO to perform the creation operation.
	 * If the creation operation is successful, the new product is added to the data
	 * source. If the creation operation fails, a ServiceException is thrown with an
	 * appropriate error message. If the validation operation throws a
	 * ValidationException, it is caught and re-thrown as a ServiceException.
	 *
	 * @param product The Product object containing the information of the new
	 *                product.
	 * @throws ServiceException    If there is an issue with validation or
	 *                             persistence during product creation.
	 * @throws ValidationException If any of the product's attributes fail
	 *                             validation.
	 */
	public void createProduct(Product product) throws ServiceException, ValidationException {

		ProductValidator validator = new ProductValidator();
		validator.validateProduct(product);

		ProductDAO productDAO = new ProductDAO();
		try {
			productDAO.create(product);
		} catch (PersistenceException e) {

			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Deletes a product by its ID.
	 *
	 * This method deletes a product based on the provided product ID. It first
	 * validates the product ID using the ProductValidator.validateProductId method.
	 * If the validation is successful, the product's ID is passed to the ProductDAO
	 * to perform the deletion operation. If the deletion operation is successful,
	 * the product is removed from the data source. If the deletion operation fails,
	 * a ServiceException is thrown with an appropriate error message. If the
	 * validation operation throws a ValidationException, it is caught and re-thrown
	 * as a ServiceException.
	 *
	 * @param productId The ID of the product to be deleted.
	 * @throws ServiceException    If there is an issue with validation or
	 *                             persistence during product deletion.
	 * @throws ValidationException If the provided product ID fails validation.
	 */
	public void deleteProduct(int productId) throws ServiceException, ValidationException {

		ProductValidator validator = new ProductValidator();
		validator.validateProductId(productId);

		ProductDAO productDAO = new ProductDAO();
		try {
			productDAO.deleteProduct(productId);
		} catch (PersistenceException e) {

			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	
	public Set<Product> findProductByUsertId(int user_id) throws ServiceException, ValidationException {

		ProductDAO productDAO = new ProductDAO();
		Set<Product> products;
		try {
			products = productDAO.listAllProductsByUserId(user_id);
			return products;
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

}
