package in.fssa.kaithari.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import in.fssa.kaithari.dao.OrderDAO;
import in.fssa.kaithari.dao.UserDAO;
import in.fssa.kaithari.exception.PersistenceException;
import in.fssa.kaithari.exception.ServiceException;
import in.fssa.kaithari.exception.ValidationException;
import in.fssa.kaithari.model.Order;
import in.fssa.kaithari.validator.OrderValidator;

public class OrderService {
	 public void createOrder(Order newOrder) throws ServiceException, ValidationException {
	        OrderValidator.validate(newOrder);
	        OrderDAO orderDAO = new OrderDAO();

	        try {
	            orderDAO.createOrder(newOrder);
	        } catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	  
	 
	 public Order findById(int id) throws ServiceException {
		 OrderDAO orderDAO = new OrderDAO();
	        try {
	            return orderDAO.findById(id);
	        } catch (PersistenceException e) {
	            e.printStackTrace();
	            throw new ServiceException(e.getMessage());
	        }
	    }
	 
	 public  Set<Order> findOrderByUserId(int userId) throws ServiceException {
		 OrderDAO orderDAO = new OrderDAO();
	        try {
	            return orderDAO.findOrderByUserId(userId);
	        } catch (PersistenceException e) {
	            e.printStackTrace();
	            throw new ServiceException(e.getMessage());
	        }
	    }
	 
	 public  List<Order> findOrderBySellerId(int sellerId) throws ServiceException {
		 OrderDAO orderDAO = new OrderDAO();
	        try {
	            return orderDAO.findOrderBySellerId(sellerId);
	        } catch (PersistenceException e) {
	            e.printStackTrace();
	            throw new ServiceException(e.getMessage());
	        }
	    }
	 
	 public void cancelOrder(int id) throws ServiceException {
		 OrderDAO orderDAO = new OrderDAO();
	        try {
	            orderDAO.cancelOrder(id);
	        } catch (PersistenceException e) {
	            e.printStackTrace();
	            throw new ServiceException(e.getMessage());
	        }
	    }
}


