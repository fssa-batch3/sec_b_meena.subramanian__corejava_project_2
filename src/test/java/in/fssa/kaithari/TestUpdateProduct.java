package in.fssa.kaithari;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.security.SecureRandom;

import org.junit.jupiter.api.Test;

import in.fssa.kaithari.model.Product;
import in.fssa.kaithari.service.ProductService;

public class TestUpdateProduct {
	
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
	
	
//	@Test
//	void testUpdateProductPrice() {
//
//		ProductService productService = new ProductService();
//		Product updateProduct = new Product();
//		updateProduct.setId(1);
//		updateProduct.setPrice(140);
//		assertDoesNotThrow(() -> {
//			productService.updateProductPrice(4, updateProduct.getPrice());
//		});
//	}
	
	@Test
	void testUpdateProduct() {

		ProductService productService = new ProductService();
		Product updateProduct = new Product();
		String randomString = generateRandomString(24);
		String randomStringForDescription = generateRandomString(15);
		
		updateProduct.setName(randomString);
		updateProduct.setCategory_id(2);
		updateProduct.setDescription(randomStringForDescription);
		updateProduct.setPrice(2990);
		
		assertDoesNotThrow(() -> {
			productService.updateProduct(14, updateProduct);
		});
	}


}

