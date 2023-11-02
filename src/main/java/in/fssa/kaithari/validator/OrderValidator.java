package in.fssa.kaithari.validator;

import java.sql.Timestamp;

import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.Order;

public class OrderValidator {
    public static void validate(Order order) throws ValidationException {
        if (order == null) {
            throw new ValidationException("Invalid order input");
        }

        validateUserId(order.getUserId());
        validateSellerId(order.getSellerId());
        validateProductId(order.getProductId());
//        validateCreatedAt(order.getCreatedAt());
        validateName(order.getName());
        validateAddress(order.getAddress());
        validateVillage(order.getVillage());
        validateDistrict(order.getDistrict());
        validateBuyQuantity(order.getBuyQuantity());
        validatePincode(order.getPincode());
        validateMobileNumber(order.getMobileNumber());
       
    }
 
    private static void validateUserId(int userId) throws ValidationException {
        if (userId <= 0) {
            throw new ValidationException("User ID must be greater than zero");
        }
       
    }

    private static void validateSellerId(int sellerId) throws ValidationException {
        if (sellerId <= 0) {
            throw new ValidationException("Seller ID must be greater than zero");
        }
       
    }

    private static void validateProductId(int productId) throws ValidationException {
        if (productId <= 0) {
            throw new ValidationException("Product ID must be greater than zero");
        }
       
    }



//    private static void validateCreatedAt(Timestamp createdAt) throws ValidationException {
//        if (createdAt == null) {
//            throw new ValidationException("Creation date is required");
//        }
//       
//    }

    private static void validateName(String name) throws ValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Name is required");
        }

        if (name.length() > 255) {
            throw new ValidationException("Name cannot exceed 255 characters");
        }

    }

    private static void validateAddress(String address) throws ValidationException {
    	if (address == null || address.trim().isEmpty()) {
            throw new ValidationException("Address is required");
        }
    }

    private static void validateVillage(String village) throws ValidationException {
    	 if (village == null || village.trim().isEmpty()) {
    	        throw new ValidationException("Village is required");
    	    }
    	 }

    private static void validateDistrict(String district) throws ValidationException {
    	 if (district == null || district.trim().isEmpty()) {
    	        throw new ValidationException("District is required");
    	    }
    }

    private static void validateBuyQuantity(int buyQuantity) throws ValidationException {
        if (buyQuantity <= 0) {
            throw new ValidationException("Buy quantity must be greater than zero");
        }
        
    }

    private static void validatePincode(int pincode) throws ValidationException {
    	 if (pincode <= 0) {
    	        throw new ValidationException("Pincode must be greater than zero");
    	    }

    }
    
    private static void validateMobileNumber(long newMobileNumber) throws ValidationException{
		
		String phno = String.valueOf(newMobileNumber);
		
		if(phno.length()!=10) {
			throw new ValidationException("Invalid phone number");
		}
		
		if(newMobileNumber <= 6000000000l || newMobileNumber >= 9999999999l) {
			throw new ValidationException("Invalid phone number");
		}

	}

}