package in.fssa.kaithari;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.kaithari.service.SellerService;

public class TestUpdateSeller {
    @Test
    void testUpdateSeller() {
        SellerService sellerService = new SellerService();

        int id = 3;
        String newName = "New Seller Name";
        String newAddress = "New Address";
        String newDistrict = "New District";
        long newMobileNumber = 1234567890L;
        int newPincode = 12345; 
        String newVillage = "New Village";

        assertDoesNotThrow(() -> {
            sellerService.updateAddress(id, newName, newAddress, newDistrict, newMobileNumber, newPincode, newVillage);
        });
    }
}

