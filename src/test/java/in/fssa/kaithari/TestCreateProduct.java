package in.fssa.kaithari;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.Product;
import in.fssa.kaithari.service.ProductService;

public class TestCreateProduct {

	@Test
	 void testCreateProductWithValidInput() {
		
		ProductService productService = new ProductService();
		
		Product product = new Product();
		product.setName("Kaavi Dhoti");
		product.setDescription("Given an array of n");
		product.setCategory_id(2);
		product.setPrice(3000);
		assertDoesNotThrow(() ->{
			productService.createProduct(product);
		});
	}
	
	@Test    
	 void testCreateProductWithInvalidInput() {
		
		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () ->{
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

			Exception exception = assertThrows(ValidationException.class, () ->{
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

			Exception exception = assertThrows(ValidationException.class, () ->{
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

			Exception exception = assertThrows(ValidationException.class, () ->{
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

		Exception exception = assertThrows(ValidationException.class, () ->{
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

			Exception exception = assertThrows(ValidationException.class, () ->{
				productService.createProduct(product);
	});
	String expectedMessage = "description cannot be null or empty";
	String actualMessage = exception.getMessage();
	System.out.println(actualMessage);
	assertTrue(expectedMessage.equals(actualMessage));
	}
}
	
	
	

