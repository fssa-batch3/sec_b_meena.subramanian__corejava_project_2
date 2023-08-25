package in.fssa.kaithari;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.Category;
import in.fssa.kaithari.service.CategoryService;

public class TestCreateCategory {
	@Test
	void testCreateCategoryWithValidInput() {
		
		CategoryService categoryService = new CategoryService();

		Category newCategory = new Category();
		newCategory.setName("Chuditharsse");
		assertDoesNotThrow(() ->{
			categoryService.create(newCategory);
		});
	}
	
	@Test    
	public void testCreateCategoryWithInvalidInput() {
		CategoryService categoryService = new CategoryService();
		Exception exception = assertThrows(ValidationException.class, () ->{
			categoryService.create(null);
		});
		String expectedMessage = "Invalid category input";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
		System.out.println(actualMessage);
	}
	
	@Test
	public void testCreateCategoryWithNameNull() {
		
		CategoryService categoryService = new CategoryService();
		
		Category newCategory = new Category();
		
		newCategory.setName(null);
		
		newCategory.toString();

			Exception exception = assertThrows(ValidationException.class, () ->{
				categoryService.create(newCategory);
	});
	String expectedMessage = "Name cannot be null or empty";
	String actualMessage = exception.getMessage();
	assertTrue(expectedMessage.equals(actualMessage));
}
	
	@Test
	public void testCreateCategoryWithNameEmpty() {
		
		CategoryService categoryService = new CategoryService();
		
		Category newCategory = new Category();
		
		newCategory.setName(" ");
		
		newCategory.toString();
			
			Exception exception = assertThrows(ValidationException.class, () ->{
				categoryService.create(newCategory);
	});
	String expectedMessage = "Name cannot be null or empty";
	String actualMessage = exception.getMessage();
	System.out.println(actualMessage);
	assertTrue(expectedMessage.equals(actualMessage));
}

	@Test
	public void testCreateCategoryWithInvalidName() {
		
		CategoryService categoryService = new CategoryService();
		
		Category newCategory = new Category();
		
		newCategory.setName("1234");
		
		newCategory.toString();
			
			Exception exception = assertThrows(ValidationException.class, () ->{
				categoryService.create(newCategory);
	});
	String expectedMessage = "Name doesn't match the pattern";
	String actualMessage = exception.getMessage();
	System.out.println(actualMessage);
	assertTrue(expectedMessage.equals(actualMessage));
}
}
