package in.fssa.kaithari.model;

public class Seller {
	private String name;
	private String email;
	private String password;
	private String proofImage;
	private String idImage;
	private int id;
	private boolean isActive;
	private String district;
	private int pincode;
	private String village;
	private long mobileNumber;
	private String address;
	
	public boolean getisActive() {
		return isActive;
	}
	public void setisActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
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
	public String getProofImage() {
		return proofImage;
	}
	public void setProofImage(String proofImage) {
		this.proofImage = proofImage;
	}
	public String getIdImage() {
		return idImage;
	}
	public void setIdImage(String idImage) {
		this.idImage = idImage;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
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

	@Override
	public String toString() {
		return "Seller [name=" + name + ", email=" + email + ", password=" + password + ", proofImage=" + proofImage
				+ ", idImage=" + idImage + ", id=" + id + ", isActive=" + isActive + ", district=" + district
				+ ", pincode=" + pincode + ", village=" + village + ", mobileNumber=" + mobileNumber + ", address="
				+ address + "]";
	}
	

}
