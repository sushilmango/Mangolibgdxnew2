package com.mangoreader.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import android.database.Cursor;
import android.database.MatrixCursor;

public class Dataset {

	private LinkedHashMap<String, ArrayList<HashMap<String, String>>> sectionItems = new LinkedHashMap<String, ArrayList<HashMap<String, String>>>();

	public static final String DATA_COLUMN = "data";

	public static final int TYPE_DATA = 1;

	public static final String ITEM_PREFIX = "data-";

	public static final String[] COLUMNS = new String[] { DATA_COLUMN, "_id" , "title","image_path","book_id","price","currency" };
	
	private static volatile int INDEX = 1;
	ArrayList<HashMap<String, String>> arraylist;
	
	private LinkedHashMap<String, Cursor> sectionCursors = new LinkedHashMap<String, Cursor>();

	/*public void addSection(String sectionName, int numberOfItems) {
		sectionItems.put(sectionName, numberOfItems);
	}*/

	public synchronized Cursor getSectionCursor(String sectionName) {
		MatrixCursor cursor = (MatrixCursor) sectionCursors.get(sectionName);
		if( cursor == null) {
			cursor = new MatrixCursor(COLUMNS);
			arraylist = sectionItems.get(sectionName);

			// now add item rows
			for (int i = 0; i < arraylist.size(); i++) {
				cursor.addRow(new Object[] { sectionName + i , INDEX++ ,arraylist.get(i).get("title")
						,arraylist.get(i).get("imagepath"),arraylist.get(i).get("id"),arraylist.get(i).get("price"),arraylist.get(i).get("currency")});
			}
			
			sectionCursors.put(sectionName, cursor);

		}
		
		return cursor;
	}
	
	public synchronized LinkedHashMap<String, Cursor> getSectionCursorMap() {
		if(sectionCursors.isEmpty()) {
			 for(String sectionName : sectionItems.keySet()) {
				 getSectionCursor(sectionName);
			 }
		}
		
		
		return sectionCursors;
		
	}

	public synchronized void addSection(String sectionName, 
			ArrayList<HashMap<String, String>> arraylist) { 
		sectionItems.put(sectionName, arraylist);
		
		
		
	}
	
}
