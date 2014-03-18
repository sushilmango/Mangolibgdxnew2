package com.mangoreader.ui;

import android.graphics.Bitmap;

/**
 * 
 * @author manish.s
 *
 */

public class Item {
	Bitmap image;
	String title;
	String bookid;
	
	
	public Item(Bitmap image, String title,String id) {
		super();
		this.bookid = id;
		this.image = image;
		this.title = title;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public Bitmap getImage() {
		return image;
	}
	public void setImage(Bitmap image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

}
