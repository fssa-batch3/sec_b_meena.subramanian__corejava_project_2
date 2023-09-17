package in.fssa.kaithari;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.kaithari.model.Seller;
import in.fssa.kaithari.service.SellerService;

public class TestUpdateSeller {
	@Test
	void testUpdateSeller() {
	    SellerService sellerService = new SellerService();

	    Seller updateSeller = new Seller();
	    updateSeller.setId(3);
	    updateSeller.setName("New Seller Name");
	    updateSeller.setEmail("newemail@example.com"); // Update other fields as needed

	    System.out.println(updateSeller.toString());

	    assertDoesNotThrow(() -> {
	        sellerService.update(updateSeller);
	    });
	}

}
