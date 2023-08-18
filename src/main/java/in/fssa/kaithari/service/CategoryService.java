package in.fssa.kaithari.service;

import in.fssa.kaithari.dao.CategoryDAO;
import in.fssa.kaithari.model.Category;
import in.fssa.kaithari.validator.CategoryValidator;

public class CategoryService {
	/**
	 * 
	 * @param newcategory
	 * @throws Exception
	 */

	public void create(Category newcategory) throws Exception {

		CategoryValidator.validateCategory(newcategory);

		CategoryDAO categoryDao = new CategoryDAO();

		categoryDao.create(newcategory);
	}

	/**
	 * 
	 * @param categoryId
	 * @throws Exception
	 */
	public void delete(int categoryId) throws Exception {

		CategoryValidator.validateId(categoryId);

		CategoryDAO categoryDao = new CategoryDAO();

		categoryDao.delete(categoryId);
	}

	/**
	 * 
	 * @param id
	 * @param newCategoryName
	 * @throws Exception
	 */
	public void updateCategoryName(int id, String newCategoryName) throws Exception {

		
		CategoryValidator.validateId(id);
		CategoryValidator.validateName(newCategoryName);
		CategoryDAO categoryDao = new CategoryDAO();
		categoryDao.updateName(id, newCategoryName);

	}

}
