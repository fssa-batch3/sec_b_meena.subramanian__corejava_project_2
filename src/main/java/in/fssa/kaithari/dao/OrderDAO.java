package in.fssa.kaithari.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import in.fssa.kaithari.exception.PersistenceException;
import in.fssa.kaithari.model.Order;
import in.fssa.kaithari.util.ConnectionUtil;

public class OrderDAO {
	
	
	public void createOrder(Order order) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionUtil.getConnection(); // Use your connection utility class to get a connection
            String query = "INSERT INTO orders (user_id, seller_id, product_id, order_status, cancel_order, created_at, name, address, village, district, buy_quantity, pincode,price,mobile_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            ps = conn.prepareStatement(query);

            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getSellerId());
            ps.setInt(3, order.getProductId());
            ps.setBoolean(4, order.getOrderStatus());
            ps.setBoolean(5, order.isCancelOrder());
            ps.setTimestamp(6, order.getCreatedAt());
            ps.setString(7, order.getName());
            ps.setString(8, order.getAddress());
            ps.setString(9, order.getVillage());
            ps.setString(10, order.getDistrict());
            ps.setInt(11, order.getBuyQuantity());
            ps.setInt(12, order.getPincode());
            ps.setInt(13, order.getPrice());
            ps.setLong(14, order.getMobileNumber());

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Order created successfully");
            } else {
                System.out.println("Order creation failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            ConnectionUtil.close(conn, ps);
        }
    }
	
	
	public Order findById(int id) throws PersistenceException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Order order = null;

	    try {
	        String query = "SELECT id, user_id, seller_id, product_id, order_status, cancel_order, created_at, name, address, village, district, buy_quantity, pincode, price,mobile_number FROM orders WHERE id = ?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setInt(1, id);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            order = new Order();
	            order.setId(rs.getInt("id"));
	            order.setUserId(rs.getInt("user_id"));
	            order.setSellerId(rs.getInt("seller_id"));
	            order.setProductId(rs.getInt("product_id"));
	            order.setOrderStatus(rs.getBoolean("order_status"));
	            order.setCancelOrder(rs.getBoolean("cancel_order"));
	            order.setCreatedAt(rs.getTimestamp("created_at"));
	            order.setName(rs.getString("name"));
	            order.setAddress(rs.getString("address"));
	            order.setVillage(rs.getString("village"));
	            order.setDistrict(rs.getString("district"));
	            order.setBuyQuantity(rs.getInt("buy_quantity"));
	            order.setPincode(rs.getInt("pincode"));
	            order.setPrice(rs.getInt("price"));
	            order.setMobileNumber(rs.getLong("mobile_number"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new PersistenceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }

	    return order;
	}
	
	
	public Set<Order> findOrderByUserId(int userId) throws PersistenceException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Set<Order> orders = new HashSet<>();

	    try {
	        String query = "SELECT id, user_id, seller_id, product_id, order_status, cancel_order, created_at, name, address, village, district, buy_quantity, pincode,price,mobile_number FROM orders WHERE user_id = ?";
	        con = ConnectionUtil.getConnection();
	        ps = con.prepareStatement(query);
	        ps.setInt(1, userId);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Order order = new Order();
	            order.setId(rs.getInt("id"));
	            order.setUserId(rs.getInt("user_id"));
	            order.setSellerId(rs.getInt("seller_id"));
	            order.setProductId(rs.getInt("product_id"));
	            order.setOrderStatus(rs.getBoolean("order_status"));
	            order.setCancelOrder(rs.getBoolean("cancel_order"));
	            order.setCreatedAt(rs.getTimestamp("created_at"));
	            order.setName(rs.getString("name"));
	            order.setAddress(rs.getString("address"));
	            order.setVillage(rs.getString("village"));
	            order.setDistrict(rs.getString("district"));
	            order.setBuyQuantity(rs.getInt("buy_quantity"));
	            order.setPincode(rs.getInt("pincode"));
	            order.setPrice(rs.getInt("price"));
	            order.setMobileNumber(rs.getLong("mobile_number"));
	            
	            orders.add(order);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new PersistenceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(con, ps, rs);
	    }

	    return orders;
	}


    public List<Order> findOrderBySellerId(int sellerId) throws PersistenceException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Order> orders = new ArrayList<>();

        try {
            String query = "SELECT id, user_id, seller_id, product_id, order_status, cancel_order, created_at, name, address, village, district, buy_quantity, pincode,price,mobile_number FROM orders WHERE seller_id = ?";
            con = ConnectionUtil.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, sellerId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setSellerId(rs.getInt("seller_id"));
                order.setProductId(rs.getInt("product_id"));
                order.setOrderStatus(rs.getBoolean("order_status"));
                order.setCancelOrder(rs.getBoolean("cancel_order"));
                order.setCreatedAt(rs.getTimestamp("created_at"));
                order.setName(rs.getString("name"));
                order.setAddress(rs.getString("address"));
                order.setVillage(rs.getString("village"));
                order.setDistrict(rs.getString("district"));
                order.setBuyQuantity(rs.getInt("buy_quantity"));
                order.setPincode(rs.getInt("pincode"));
                order.setPrice(rs.getInt("price"));
                order.setMobileNumber(rs.getLong("mobile_number"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(con, ps, rs);
        }

        return orders;
    }
    
    
    public void cancelOrder(int orderId) throws PersistenceException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionUtil.getConnection(); // Use your connection utility class to get a connection
            String query = "UPDATE orders SET cancel_order = 1 WHERE id = ?";
            ps = conn.prepareStatement(query);

            // Set the cancel_order status to true (1) for the specified orderId
            ps.setInt(1, orderId);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Order with ID " + orderId + " has been canceled successfully.");
            } else {
                throw new PersistenceException("Failed to cancel the order with ID " + orderId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceException(e.getMessage());
        } finally {
            ConnectionUtil.close(conn, ps);
        }
    }

}
