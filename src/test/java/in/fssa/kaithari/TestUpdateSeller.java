package in.fssa.kaithari;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.kaithari.service.SellerService;

public class TestUpdateSeller {
    @Test
    void testUpdateSeller() {
        SellerService sellerService = new SellerService();

        int id = 3;
        String newName = "NewSellerName";
        String newAddress = "NewAddress";
        String newDistrict = "NewDistrict";
        long newMobileNumber = 1234567890L;
        int newPincode = 609202; 
        String newVillage = "New Village";

        assertDoesNotThrow(() -> {
            sellerService.updateAddress(id, newName, newAddress, newDistrict, newMobileNumber, newPincode, newVillage);
        });
    }
}

