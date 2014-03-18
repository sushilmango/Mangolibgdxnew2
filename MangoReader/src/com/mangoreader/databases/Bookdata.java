package com.mangoreader.databases;

public class Bookdata {

	String Id;
	String BookName;
	String Imagepath;
	String Price;
	String Currency;
	String categoryID;
	String cat[];
	byte[] image ;
	public void setCat(String[] cat) {
		this.cat = cat;
	}
	public String[] getCat() {
		return cat;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryID() {
		return categoryID;
	}

	public void setId(String id) {
		Id = id;
	}
	public String getId() {
		return Id;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public String getBookName() {
		return BookName;
	}
	public void setImagepath(String imagepath) {
		Imagepath = imagepath;
	}
	public String getImagepath() {
		return Imagepath;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getPrice() {
		return Price;
	}
	/*public void setPrice(int price) {
		Price = price;
	}
	public int getPrice() {
		return Price;
	}*/
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public String getCurrency() {
		return Currency;
	}

}
