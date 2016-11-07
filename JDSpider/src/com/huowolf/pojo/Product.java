package com.huowolf.pojo;

public class Product implements Comparable<Product>{
	private String description;
	private String image;
	private double price;
	private long comment; // ÆÀ¼ÛÁ¿

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getComment() {
		return comment;
	}

	public void setComment(long comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Product [description=" + description + ", image=" + image
				+ ", price=" + price + ", comment=" + comment + "]";
	}


	@Override
	public int compareTo(Product pro) {
		if(pro.comment - this.comment>0)
			return 1;
		else if(pro.comment == this.comment)
			return pro.description.compareTo(this.description);
		else 
			return -1;
	}
	

}
