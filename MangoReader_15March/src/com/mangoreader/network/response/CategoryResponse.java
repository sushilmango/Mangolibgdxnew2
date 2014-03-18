package com.mangoreader.network.response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class CategoryResponse {

	private JSONObject jObject;

	private CategoryBook [] books;

	public CategoryBook[] getBooks(){
		return books;
	}

	public static class CategoryBook {
		private String name;
		private String desc;

		private String id;


		public String getName() {
			return name;
		}


		public String getDesc() {
			return desc;
		}
		public String getId() {
			return id;
		}



	}

	public CategoryResponse(byte[] data) throws JSONException {


		JSONArray array = new JSONArray(new String(data));

		books = new CategoryBook[array.length()];

		for( int i = 0 ; i < books.length; i++){

			jObject = array.getJSONObject(i);

			CategoryBook book = new CategoryBook();
			book.id = jObject.getString("id");
			book.name = jObject.getString("name");
			book.desc = jObject.getString("desc");
			Log.e("category id is:", ">" + book.id);
			Log.e("category name:", ">" + book.name);
			Log.v("category desc:", ">" + book.desc);



			books[i] = book;


		}
	}

}
