package in.fssa.kaithari.service;

import java.util.Set;

import com.google.protobuf.ServiceException;

import in.fssa.kaithari.dao.CategoryDAO;
import in.fssa.kaithari.exception.PersistenceException;
import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.Category;
import in.fssa.kaithari.validator.CategoryValidator;

public class CategoryService {

	
	/**
	 * Creates a new category.
	 *
	 * This method creates a new category with the provided details. It first
	 * validates the provided category using the CategoryValidator.validateCategory
	 * method. If the validation is successful, the new category is passed to the
	 * CategoryDAO to perform the creation operation. If the category creation
	 * operation is successful, a new category is added to the data source. If the
	 * creation operation fails, a ServiceException is thrown with an appropriate
	 * error message. If the validation or persistence operation throws an
	 * exception, they are caught and re-thrown as ServiceException instances.
	 *
	 * @param newcategory The Category object containing the details of the new
	 *                    category to be created.
	 * @throws ServiceException    If there is an issue with validation or
	 *                             persistence during category creation.
	 * @throws ValidationException If the provided category fails validation.
	 */

	public void create(Category newcategory) throws ServiceException, ValidationException {

		CategoryValidator.validateCategory(newcategory);

		CategoryDAO categoryDAO = new CategoryDAO();

		try {
			categoryDAO.create(newcategory);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Deletes a category by its ID.
	 *
	 * This method deletes a category based on the provided category ID. It first
	 * validates the provided category ID using the CategoryValidator.validateId
	 * method. If the validation is successful, the category with the specified ID
	 * is passed to the CategoryDAO to perform the deletion operation. If the
	 * deletion operation is successful, the corresponding category is removed from
	 * the data source. If the deletion operation fails, a ServiceException is
	 * thrown with an appropriate error message. If the validation operation throws
	 * a ValidationException, it is caught and re-thrown as a ServiceException
	 * instance.
	 *
	 * @param categoryId The ID of the category to be deleted.
	 * @throws ServiceException    If there is an issue with validation or
	 *                             persistence during category deletion.
	 * @throws ValidationException If the provided category ID fails validation.
	 */
	public void delete(int categoryId) throws ServiceException, ValidationException {

		CategoryValidator.validateId(categoryId);

		CategoryDAO categoryDAO = new CategoryDAO();

		categoryDAO.delete(categoryId);
	}

	/**
	 * Updates the name of a category by its ID.
	 *
	 * This method updates the name of a category based on the provided category ID
	 * and the new category name. It first validates the provided category ID using
	 * the CategoryValidator.validateId method and the new category name using the
	 * CategoryValidator.validateName method. If both validations are successful,
	 * the category's ID and the new name are passed to the CategoryDAO to perform
	 * the update operation. If the update operation is successful, the category's
	 * name is modified in the data source. If the update operation fails, a
	 * ServiceException is thrown with an appropriate error message. If either
	 * validation operation throws a ValidationException, they are caught and
	 * re-thrown as ServiceException instances.
	 *
	 * @param id              The ID of the category to be updated.
	 * @param newCategoryName The new name to update for the category.
	 * @throws ServiceException    If there is an issue with validation or
	 *                             persistence during category name update.
	 * @throws ValidationException If the provided category ID or new name fails
	 *                             validation.
	 */
	public void updateCategoryName(int id, String newCategoryName) throws ServiceException, ValidationException {
		CategoryValidator.validateUpdateId(id);
		CategoryValidator.validateName(newCategoryName);
		CategoryDAO categoryDAO = new CategoryDAO();
		try {
			categoryDAO.updateName(id, newCategoryName);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	  
	public Set<Category> listAllCategroy()throws ServiceException {
		CategoryDAO categoryDAO=new CategoryDAO();
		
		try {
			return categoryDAO.listAllCategroy();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
}
