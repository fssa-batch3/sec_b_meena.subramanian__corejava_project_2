package in.fssa.kaithari.model;


public class User {
	private String name;
	private String email;
	private String password;
	private int id;
	private String district;
	private int pincode;
	private String village;
	private long mobileNumber;
	private String address;
	
	

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public String getName() {
		return name;
	}

	public void setName( String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", id=" + id + ", district="
				+ district + ", pincode=" + pincode + ", village=" + village + ", mobileNumber=" + mobileNumber
				+ ", address=" + address + "]";
	}
//	@Override
//	public String toString() {
//		return "UserEntity [name=" + name + ", email=" + email + ", password=" + password + ", id=" + id + "]";
//	}

}
