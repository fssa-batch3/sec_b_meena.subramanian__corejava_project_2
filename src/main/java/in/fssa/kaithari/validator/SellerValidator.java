package in.fssa.kaithari.validator;

import in.fssa.kaithari.dao.SellerDAO;
import in.fssa.kaithari.exception.PersistenceException;
import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.Seller;
import in.fssa.kaithari.util.StringUtil;

public class SellerValidator {
	public static void validate(Seller seller) throws ValidationException {
	    if (seller == null) {
	        throw new ValidationException("Invalid seller input");
	    }

	    StringUtil.rejectIfInvalidString(seller.getName(), "Name");
	    StringUtil.rejectIfInvalidString(seller.getEmail(), "Email");
	    StringUtil.rejectIfInvalidString(seller.getProofImage(), "Proof Image");
	    StringUtil.rejectIfInvalidString(seller.getIdImage(), "ID Image");
	    StringUtil.rejectIfInvalidString(seller.getPassword(), "Password");

	    validateName(seller.getName());
	    validateEmail(seller.getEmail());
	    validatePassword(seller.getPassword());
	   
	}
	
	private static void validateName(String name) throws ValidationException {
	    if (name == null || name.trim().isEmpty()) {
	        throw new ValidationException("Name cannot be empty");
	    }
	    if (!name.matches("^[A-Za-z][A-Za-z\\\\s]*$")) {
	        throw new ValidationException("Invalid Name format");
	    }
	   
	}

	private static void validateEmail(String email) throws ValidationException {
	    if (email == null || email.trim().isEmpty()) {
	        throw new ValidationException("Email cannot be empty");
	    }
	    if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
	        throw new ValidationException("Invalid email format");
	    }
	}

	private static void validatePassword(String password) throws ValidationException {
	    if (password == null || password.trim().isEmpty()) {
	        throw new ValidationException("Password cannot be empty");
	    }
	    if (!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$")) {
	        throw new ValidationException("Invalid Password format");
	    }
	}

	
	public static void validateSeller(int id) throws ValidationException {
	    if (id < 1) {
	        throw new ValidationException("Invalid seller id");
	    }
	}

	public static void checkSellerIdExist(int sellerId) throws ValidationException, PersistenceException {
	    SellerDAO sellerDAO = new SellerDAO();
	    sellerDAO.checkSellerIdExist(sellerId);
	}
}
