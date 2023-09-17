package in.fssa.kaithari.model;

public class Seller {

	
	private String name;
	private String email;
	private String password;
	private String proofImage;
	private String idImage;
	private int id;
	private boolean isActive;
	
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

	
	@Override
	public String toString() {
		return "Seller [name=" + name + ", email=" + email + ", password=" + password + ", proofImage=" + proofImage
				+ ", idImage=" + idImage + ", id=" + id + ", isActive=" + isActive + "]";
	}

}
