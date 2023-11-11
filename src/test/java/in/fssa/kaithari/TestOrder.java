package in.fssa.kaithari;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.kaithari.model.Order;
import in.fssa.kaithari.model.Product;
import in.fssa.kaithari.service.OrderService;
import in.fssa.kaithari.service.ProductService;


public class TestOrder {
	
	@Test
	void testCreateProductWithValidInput() {

		OrderService orderService = new OrderService();

		Order order=new Order();
		order.setId(0);
		

		order.setUserId(25);
		order.setSellerId(4);
		order.setProductId(38);
		order.setOrderStatus(true);
		order.setCancelOrder(false);
		order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		order.setName("John Doe");
		order.setAddress("123 Main St");
		order.setVillage("Sample Village");
		order.setDistrict("Sample District");
		order.setBuyQuantity(5);
		order.setPincode(609202);
		order.setPrice(100);
		order.setMobileNumber(8925054123L);

		
		assertDoesNotThrow(() -> {
			orderService.createOrder(order);
		});
	}
   
	@Test
	void findById() {

		OrderService orderService = new OrderService();

		assertDoesNotThrow(() -> {
			Order order = orderService.findById(12);
		});
	}
	
	@Test
	void findOrderByUserId() {

		OrderService orderService = new OrderService();

		assertDoesNotThrow(() -> {
			Set<Order> order=orderService.findOrderByUserId(25);
		});
	}
	
	
	@Test
	void findOrderSellerId() {

		OrderService orderService = new OrderService();

		assertDoesNotThrow(() -> {
			List<Order> order=orderService.findOrderBySellerId(12);
		});
	}
	
	@Test
	void cancelOrder() {
		OrderService orderService = new OrderService();
		assertDoesNotThrow(() -> {
		orderService.cancelOrder(10);
		});
	}
	
}
