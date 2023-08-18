package in.fssa.kaithari;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.kaithari.model.Product;
import in.fssa.kaithari.service.ProductService;

public class TestUpdateProduct {
	@Test
	 void testUpdateProductPrice() {
		
		ProductService productService = new ProductService();
		Product updateProduct = new Product();
		updateProduct.setId(1);
		updateProduct.setPrice(140);
		assertDoesNotThrow(() ->{
			productService.updateProductPrice(updateProduct.getId(), updateProduct.getPrice());
		});
	}
	

}
