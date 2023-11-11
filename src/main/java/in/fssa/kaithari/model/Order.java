package in.fssa.kaithari.model;

import java.sql.Timestamp;

public class Order {

	private int id;
	private int userId;
	private int sellerId;
	private int productId;
	private boolean orderStatus;
	private boolean cancelOrder;
	private Timestamp createdAt;
	private String name;
	private String address;
	private String village;
	private String district;
	private int buyQuantity;
	private int pincode;
	private int price;
	private long mobileNumber;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public boolean getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(boolean orderStatus) {
		this.orderStatus = orderStatus;
	}

	public boolean isCancelOrder() {
		return cancelOrder;
	}

	public void setCancelOrder(boolean cancelOrder) {
		this.cancelOrder = cancelOrder;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(int buyQuantity) {
		this.buyQuantity = buyQuantity;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", sellerId=" + sellerId + ", productId=" + productId
				+ ", orderStatus=" + orderStatus + ", cancelOrder=" + cancelOrder + ", createdAt=" + createdAt
				+ ", name=" + name + ", address=" + address + ", village=" + village + ", district=" + district
				+ ", buyQuantity=" + buyQuantity + ", pincode=" + pincode + ", price=" + price + ", mobileNumber="
				+ mobileNumber + "]";
	} 

}
