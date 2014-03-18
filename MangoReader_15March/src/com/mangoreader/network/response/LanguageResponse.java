package com.mangoreader.network.response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mangoreader.network.response.AgeGroupResponse.AgeGroup;

import android.util.Log;

public class LanguageResponse {

	private JSONObject jObject;

	private LanguagesBook [] books;

	public LanguagesBook[] getBooks() {
		return books;
	}

	public static class LanguagesBook {
		private String name;
		private String code;

		private String id;


		public String getName() {
			return name;
		}


		public String getCode() {
			return code;
		}
		public String getId() {
			return id;
		}



	}

	
	
/*	public AgeGroupResponse(byte[] data) throws JSONException {


		JSONArray array = new JSONArray(new String(data));

		ages = new AgeGroup[array.length()];

		for( int i = 0 ; i < ages.length; i++){

			jObject = array.getJSONObject(i);

			AgeGroup age = new AgeGroup();
			age.id = jObject.getString("id");
			age.name = jObject.getString("name");
			age.desc = jObject.getString("desc");
			Log.e("category id is:", ">" + age.id);
			Log.e("category name:", ">" + age.name);
			Log.v("category desc:", ">" + age.desc);



			ages[i] = age;


		}
	}*/
	public LanguageResponse(byte[] data) throws JSONException {


		JSONArray array = new JSONArray(new String(data));
		
		books = new LanguagesBook[array.length()];
		Log.e("data", "data"+array.get(1));
		
		
		for( int i = 0 ; i < books.length; i++){

			jObject = array.getJSONObject(i);

			LanguagesBook book = new LanguagesBook();
			book.name = jObject.getString("_id");
			Log.e("category id is:", ">" + book.name);
			/*Log.e("category name:", ">" + age.name);
			Log.v("category desc:", ">" + age.desc);*/



			books[i] = book;


		}
		/*for (int i = 0; i < array.length(); i++) {
			
			LanguagesBook book = new LanguagesBook();
			book.name = array.get(i).toString();
			books[i] = book;
		}*/
		
     /*
		for (int i = 0; i < array.length(); i++) {
			Log.e("value",""+array.getInt(i));
		}*/
	/*	
		books = new LanguagesBook[array.length()];

		for( int i = 0 ; i < books.length; i++){

			Log.e("value", ""+array.getString(i));
			
			
			jObject = array.getJSONObject(i);

			LanguagesBook book = new LanguagesBook();
			book.id = jObject.getString("id");
			book.name = jObject.getString("name");
			book.code = jObject.getString("code");
			Log.e("lang id is:", ">" + book.id);
			Log.e("lang name:", ">" + book.name);
			Log.v("lang desc:", ">" + book.code);



			books[i] = book;


		}
*/	}

}
