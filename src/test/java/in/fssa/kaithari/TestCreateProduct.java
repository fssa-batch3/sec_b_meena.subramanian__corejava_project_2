package in.fssa.kaithari;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.SecureRandom;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.Product;
import in.fssa.kaithari.service.ProductService;

public class TestCreateProduct {
	
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
	void testCreateProductWithValidInput() {

		ProductService productService = new ProductService();

		Product product = new Product();
		String randomString = generateRandomString(5);
		product.setName(randomString);
		product.setCategory_id(8);
		product.setDescription("Given an array of n");
		product.setPrice(3000);
		assertDoesNotThrow(() -> {
			productService.createProduct(product);
		});
	}

	@Test
	void testCreateProductWithInvalidInput() {

		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(null);
		});
		String expectedMessage = "Invalid Product input";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
		System.out.println(actualMessage);
	}

	@Test
	void testCreateProductWithNameNull() {

		ProductService productService = new ProductService();

		Product product = new Product();

		product.setName(null);

		product.toString();

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void testCreateProductWithNameEmpty() {

		ProductService productService = new ProductService();

		Product product = new Product();

		product.setName("");

		product.toString();

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void testCreateProductWithInvalidName() {

		ProductService productService = new ProductService();

		Product product = new Product();

		product.setName("dhoti645");

		product.toString();

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
		});
		String expectedMessage = "Name does not match the pattern";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void testCreateProductWithDescriptionNull() {
		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("kaavi dhothi");
		product.setCategory_id(2);
		product.setDescription(null);
		product.setPrice(300);

		product.toString();

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
		});
		String expectedMessage = "description cannot be null or empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	void testCreateProductWithDescriptionEmpty() {

		ProductService productService = new ProductService();

		Product product = new Product();
		product.setName("kaavi dhoti");
		product.setCategory_id(2);
		product.setDescription("");
		product.setPrice(300);

		product.toString();

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.createProduct(product);
		});
		String expectedMessage = "description cannot be null or empty";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	void findListAllProduct() {

		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			Set<Product> list=productService.listAllProduct();
		});
	}
	
	@Test
	void findListAllProductBycategeryId() {

		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			Set<Product> list=productService.findProductByCategoryId(7);
		});
	}
	
	@Test
	void findProductById() {

		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			Product list=productService.findProductById(43);
		});
	}
	
	@Test
	void findUpdateProductPrice() {

		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
		    productService.updateProductPrice(43, 3000);
		});
	}
	
	@Test
	void findProductBySellerId() {

		ProductService productService = new ProductService();

		assertDoesNotThrow(() -> {
			Set<Product> list=productService.findProductBySellerId(12);
		});
	}
	
}
