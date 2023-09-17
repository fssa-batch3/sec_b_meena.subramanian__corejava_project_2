package in.fssa.kaithari.model;

public class Product {
	private int id;
	private String name;
	private String description;
	private int price;
	private int category_id;
	private int sellerId;
	private String jarigai;
	private String designName;
	private String image;
	private int length;
	private int quantity;
	private int offers;

	public String getJarigai() {
		return jarigai;
	}

	public void setJarigai(String jarigai) {
		this.jarigai = jarigai;
	}

	public String getDesignName() {
		return designName;
	}

	public void setDesignName(String designName) {
		this.designName = designName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getOffers() {
		return offers;
	}

	public void setOffers(int offers) {
		this.offers = offers;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", category_id=" + category_id + ", sellerId=" + sellerId + ", jarigai=" + jarigai + ", designName="
				+ designName + ", image=" + image + ", length=" + length + ", quantity=" + quantity + ", offers="
				+ offers + "]";
	}

//	@Override
//	public String toString() {
//		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
//				+ ", category_id=" + category_id + ", sellerId=" + sellerId + "]";
//	}

//	@Override
//	public String toString() {
//		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
//				+ ", category_id=" + category_id + "]";
//	}

}
