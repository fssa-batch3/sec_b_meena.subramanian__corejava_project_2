package in.fssa.kaithari;

import in.fssa.kaithari.exception.ServiceException;
import in.fssa.kaithari.model.Product;
import in.fssa.kaithari.service.ProductService;

public class AppTest {

	public static void main(String[] args) {
		ProductService productservice = new ProductService();
        Product product = new Product();
        
        try {
			product = productservice.findProductById(7);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
        System.out.println(product.toString());
	}

}



