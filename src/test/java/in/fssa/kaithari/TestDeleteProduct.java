package in.fssa.kaithari;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.kaithari.model.Product;
import in.fssa.kaithari.service.ProductService;

public class TestDeleteProduct {
	@Test
	 void testDeleteProduct() {
		
		ProductService productService = new ProductService();

		Product newProduct = new Product();
		newProduct.setId(1);
		assertDoesNotThrow(() ->{
			productService.deleteProduct(newProduct.getId());
		});
	}

}
