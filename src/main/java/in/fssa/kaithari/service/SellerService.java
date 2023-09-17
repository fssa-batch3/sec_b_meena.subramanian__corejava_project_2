package in.fssa.kaithari.service;

import in.fssa.kaithari.dao.SellerDAO;
import in.fssa.kaithari.exception.PersistenceException;
import in.fssa.kaithari.exception.ServiceException;
import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.Seller;
import in.fssa.kaithari.validator.SellerValidator;

public class SellerService {
	public void create(Seller newSeller) throws ServiceException, ValidationException {
	    SellerValidator.validate(newSeller); 

	    SellerDAO sellerDAO = new SellerDAO();

	    try {
	        sellerDAO.create(newSeller);
	    } catch (PersistenceException e) {
	        e.printStackTrace();
	        throw new ServiceException(e.getMessage());
	    }
	}
	
	public Seller findByEmail(String email) throws ServiceException, PersistenceException {
	    SellerDAO sellerDAO = new SellerDAO();

	    try {
	        return sellerDAO.findByEmail(email);
	    } catch (PersistenceException e) {
	        e.printStackTrace();
	        throw new ServiceException(e.getMessage());
	    }
	}
	
	public Seller findById(int sellerId) throws ServiceException, PersistenceException {
	    SellerDAO sellerDAO = new SellerDAO();

	    try {
	        return sellerDAO.findById(sellerId);
	    } catch (PersistenceException e) {
	        e.printStackTrace();
	        throw new ServiceException(e.getMessage());
	    }
	}
	
	public void update(Seller updatedSeller) throws ServiceException, PersistenceException {
	    SellerDAO sellerDAO = new SellerDAO();

	    try {
	        sellerDAO.update(updatedSeller);
	    } catch (PersistenceException e) {
	        e.printStackTrace();
	        throw new ServiceException(e.getMessage());
	    }
	}

}

