package in.fssa.kaithari;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.kaithari.model.Category;
import in.fssa.kaithari.service.CategoryService;

public class TestUpdateCategory {
	@Test
	public void testUpdateCategoryName() {

		CategoryService categoryService = new CategoryService();

		Category updateCategory = new Category();
		updateCategory.setId(2);
		updateCategory.setName("Saree");
		assertDoesNotThrow(() -> {
			categoryService.updateCategoryName(updateCategory.getId(), updateCategory.getName());
		});
	}
}

