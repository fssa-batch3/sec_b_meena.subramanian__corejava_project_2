package in.fssa.kaithari;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.kaithari.model.Product;
import in.fssa.kaithari.service.ProductService;

public class TestGetAllProducts {

	@Test
	public void getAllProductsByCategoryId() {
		ProductService productService = new ProductService();
		Set<Product> products;
		try {
			products = productService.findProductByCategoryId(1);
			System.out.println(products);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getAll() {
		ProductService productService = new ProductService();
		Set<Product> AllProducts;
		try {
			AllProducts = productService.listAllProduct();
			System.out.print(AllProducts);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
