package com.mangoreader.databases;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "mangoreader";

	// Contacts table name
	private static final String TABLE_BOOK = "BOOK";
	private static final String Table_CATEGORY ="CATEGORY";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "book_name";
	private static final String KEY_PATH = "bookpath";
	private static final String KEY_PRICE = "price";
	private static final String KEY_CURRENCY = "CUREENCY";

	private static final String KEY_CATEGORY = "CATEGORY";
	private static final String KEY_BITMAP= "imagebitmap";


	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	/* String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_BOOK + "("
             + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
             + KEY_PATH + " TEXT" + ")";*/
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		
		 String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_BOOK + "("
	                + KEY_ID + " TEXT PRIMARY KEY," + KEY_NAME + " TEXT,"
	                + KEY_PATH + " TEXT," + KEY_PRICE + " TEXT,"
	                + KEY_CURRENCY + " TEXT," + KEY_BITMAP + " BLOB" + ")";
		 db.execSQL(CREATE_CONTACTS_TABLE);
		 
		 String CREATE_CONTACTS_CATEGORY = "CREATE TABLE " + Table_CATEGORY + "("
	                + KEY_ID + " TEXT," + KEY_CATEGORY + " TEXT" + ")";
	               
		 db.execSQL(CREATE_CONTACTS_CATEGORY);

	
	}


	public void addBook(Bookdata bookdata) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		 values.put(KEY_ID, bookdata.getId());// book id
	        values.put(KEY_NAME, bookdata.getBookName());//book name
	        values.put(KEY_PATH, bookdata.getImagepath());// Contact Name
	        values.put(KEY_PRICE, bookdata.getPrice()); // price
	        values.put(KEY_CURRENCY, bookdata.getCurrency());
	        values.put(KEY_BITMAP, bookdata.getImage());// currency
		    
	        db.insert(TABLE_BOOK, null, values);
			db.close();

	}
	
	public void deletebook(String id) {
		 
		  SQLiteDatabase database = this.getWritableDatabase();  
		  String deleteQuery = "DELETE FROM  BOOK where id='"+ id +"'";
		  
		  database.execSQL(deleteQuery);
		}
	
	public void Remove_Book_From_Category(String id) {
		 
		  SQLiteDatabase database = this.getWritableDatabase();  
		  String deleteQuery = "DELETE FROM  CATEGORY where id='"+ id +"'";
		  
		  database.execSQL(deleteQuery);
		}


	  public void addcategory(Bookdata bookdata){

	    	 SQLiteDatabase db = this.getWritableDatabase();

		        ContentValues values = new ContentValues();
		        values.put(KEY_ID, bookdata.getId());// book id
		        values.put(KEY_CATEGORY, bookdata.getCategoryID());//book CATEGORY
		          // Inserting Row
		        db.insert(Table_CATEGORY, null, values);
		        db.close(); // Closing database connection
	    }


	public List<Bookdata> getAlldata() {
		List<Bookdata> bookList = new ArrayList<Bookdata>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_BOOK;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Bookdata book = new Bookdata();
				book.setId(cursor.getString(0));
				book.setBookName(cursor.getString(1));
				book.setPrice(cursor.getString(2));
				// Adding contact to list
				bookList.add(book);
			} while (cursor.moveToNext());
		}

		// return contact list
		return bookList;
	}
	//"SELECT * FROM " + tableName + " where Category = '" +categoryex + "'" , null
	
	
	public List<Bookdata> getbookdata( String catString) {
		List<Bookdata> bookList = new ArrayList<Bookdata>();
		//String selectQuery = "SELECT book_name  FROM " + TABLE_BOOK + " where id = '" +catString + "'";
		String selectQuery = "SELECT  * FROM " + TABLE_BOOK + " where id = '" +catString + "'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Bookdata book = new Bookdata();
				book.setId(cursor.getString(0));
				book.setBookName(cursor.getString(1));
				book.setPrice(cursor.getString(3));
				book.setCurrency(cursor.getString(4));
				book.setImage(cursor.getBlob(5));
				// Adding contact to list
				bookList.add(book);
			} while (cursor.moveToNext());
		}

		// return contact list
		
		
		return bookList;
	}
	
	public List<Bookdata> getallcategory() {
		List<Bookdata> bookList = new ArrayList<Bookdata>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + Table_CATEGORY;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Bookdata book = new Bookdata();
				book.setId(cursor.getString(0));
				book.setBookName(cursor.getString(1));
			
				// Adding contact to list
				bookList.add(book);
			} while (cursor.moveToNext());
		}

		// return contact list
		return bookList;
	}
	
	
	public List<Bookdata> getCaegory( String catString) {
		List<Bookdata> bookList = new ArrayList<Bookdata>();
		String selectQuery = "SELECT  * FROM " + Table_CATEGORY + " where CATEGORY = '" +catString + "'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Bookdata book = new Bookdata();
				//book.setId(cursor.getString(0));
				book.setCategoryID(cursor.getString(0));
				// Adding contact to list
				bookList.add(book);
			} while (cursor.moveToNext());
		}

		// return contact list
		
		
		return bookList;
	}
	public List<Bookdata> getAllCaegory() {
		List<Bookdata> bookList = new ArrayList<Bookdata>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + Table_CATEGORY;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Bookdata book = new Bookdata();
				book.setId(cursor.getString(0));
				book.setCategoryID(cursor.getString(1));
				// Adding contact to list
				bookList.add(book);
			} while (cursor.moveToNext());
		}

		// return contact list
		return bookList;
	}
	
	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);

		// Create tables again
		onCreate(db);
	}

}
