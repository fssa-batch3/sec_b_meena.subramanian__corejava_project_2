package in.fssa.kaithari;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.kaithari.model.Order;
import in.fssa.kaithari.service.OrderService;

public class TestOrder {
	@Test

	void testCreateOrdersWithValidInput() {
		
		OrderService orderService = new OrderService();
		
		Order newOrder = new Order();
		newOrder.setAddress(null);
		newOrder.setBuyQuantity(1);
		newOrder.setUserId(3);
		newOrder.getDistrict();
		newOrder.getPrice();;
		newOrder.getPincode();
		
        LocalDate orderDate = LocalDate.now(); 
        int daysToAdd = 3; 
        LocalDate deliveryDate = orderDate.plusDays(daysToAdd);
        
		newOrder.setDeliveryDate(deliveryDate);
		
		assertDoesNotThrow(()->{
			orderService.create(newOrder);
		});
	
	}
}
