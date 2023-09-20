package in.fssa.kaithari.service;

import in.fssa.kaithari.dao.SellerDAO;
import in.fssa.kaithari.exception.PersistenceException;
import in.fssa.kaithari.exception.ServiceException;
import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.Seller;
import in.fssa.kaithari.validator.SellerValidator;

public class SellerService {
	/**
	 * Create a new seller.
	 *
	 * This method is responsible for creating a new seller in the data source. It
	 * first validates the provided Seller object using SellerValidator. If the
	 * validation is successful, it utilizes a SellerDAO instance to interact with
	 * the data source and perform the creation operation.
	 *
	 * @param newSeller A Seller object representing the new seller to be created.
	 * @throws ServiceException    If an error occurs during the seller creation
	 *                             process or if the input validation fails.
	 * @throws ValidationException If the provided seller object is not valid
	 *                             according to predefined validation rules.
	 */

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

	/**
	 * Find a seller by their email address.
	 *
	 * This method retrieves a seller from the data source based on the provided email address.
	 * It utilizes a SellerDAO instance to interact with the data source and perform the retrieval.
	 *
	 * @param email The email address of the seller to be retrieved.
	 * @return A Seller object representing the seller with the specified email address, or null if no matching seller is found.
	 * @throws ServiceException If an error occurs during the retrieval of the seller.
	 * @throws PersistenceException If an error occurs in the data access layer.
	 */

	public Seller findByEmail(String email) throws ServiceException, PersistenceException {
		SellerDAO sellerDAO = new SellerDAO();

		try {
			return sellerDAO.findByEmail(email);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Find a seller by their ID.
	 *
	 * This method retrieves a seller from the data source based on the provided seller ID.
	 * It utilizes a SellerDAO instance to interact with the data source and perform the retrieval.
	 *
	 * @param sellerId The ID of the seller to be retrieved.
	 * @return A Seller object representing the seller with the specified ID, or null if no matching seller is found.
	 * @throws ServiceException If an error occurs during the retrieval of the seller.
	 * @throws PersistenceException If an error occurs in the data access layer.
	 */

	public Seller findById(int sellerId) throws ServiceException, PersistenceException {
		SellerDAO sellerDAO = new SellerDAO();

		try {
			return sellerDAO.findById(sellerId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Update seller information.
	 *
	 * This method is responsible for updating the information of an existing seller in the data source.
	 * It utilizes a SellerDAO instance to interact with the data source and perform the update operation.
	 *
	 * @param updatedSeller A Seller object representing the updated seller information.
	 * @throws ServiceException If an error occurs during the seller update process.
	 * @throws PersistenceException If an error occurs in the data access layer.
	 */

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
