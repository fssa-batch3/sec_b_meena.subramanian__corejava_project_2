package in.fssa.kaithari;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.kaithari.model.Category;
import in.fssa.kaithari.model.Product;
import in.fssa.kaithari.service.CategoryService;
import in.fssa.kaithari.service.ProductService;

public class TestUpdateCategory {
	@Test
	public void testUpdateCategoryName() {

		CategoryService categoryService = new CategoryService();

		Category updateCategory = new Category();
		updateCategory.setId(15);
		updateCategory.setName("Saree");
		assertDoesNotThrow(() -> {
			categoryService.updateCategoryName(updateCategory.getId(), updateCategory.getName());
		});
	}
	
	@Test
	void findListAllCategory() {

		CategoryService categoryService = new CategoryService();

		assertDoesNotThrow(() -> {
			Set<Category> list = categoryService.listAllCategroy();
		});
	}
	
	@Test
	void DeleteCategory() {

		CategoryService categoryService = new CategoryService();

		assertDoesNotThrow(() -> {
			categoryService.delete(15);
		});
	}
}
 
