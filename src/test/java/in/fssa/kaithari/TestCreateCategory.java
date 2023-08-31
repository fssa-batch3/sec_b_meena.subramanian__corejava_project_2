package in.fssa.kaithari;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.SecureRandom;

import org.junit.jupiter.api.Test;

import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.Category;
import in.fssa.kaithari.service.CategoryService;

public class TestCreateCategory {
	
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public static String generateRandomString(int length) {
		SecureRandom random = new SecureRandom();
		StringBuilder stringBuilder = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			char randomChar = CHARACTERS.charAt(randomIndex);
			stringBuilder.append(randomChar);
		}

		return stringBuilder.toString();
	}
	
	@Test
	void testCreateCategoryWithValidInput() {

		CategoryService categoryService = new CategoryService();
		String randomString = generateRandomString(5);
		Category newCategory = new Category();
		newCategory.setName(randomString);
		assertDoesNotThrow(() -> {
			categoryService.create(newCategory);
		});
	}

	@Test
	public void testCreateCategoryWithInvalidInput() {
		CategoryService categoryService = new CategoryService();
		Exception exception = assertThrows(ValidationException.class, () -> {
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

		Exception exception = assertThrows(ValidationException.class, () -> {
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

		Exception exception = assertThrows(ValidationException.class, () -> {
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

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.create(newCategory);
		});
		String expectedMessage = "Name doesn't match the pattern";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
}
