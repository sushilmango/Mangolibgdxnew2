package com.mangoreader.network.response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.mangoreader.network.response.SearchListResponse.Book.Info;

public class SearchListResponse {

	private JSONObject jObject;

	private Book [] books;

	public Book[] getBooks(){
		return books;
	}

	public static class Book {
		private String user_name;
		private String title;
		private String synopis;
		private String id;

		private int page_count;
		private String cover;
		private String download_url;

		private Info info;
		private int price;
		private String currency;
		public String getId() {
			return id;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}

		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}

		public Info getInfo() {
			return info;
		}


		public static class Info{
			private String language;
			private int sub_categories;
			private boolean is_free;
			
			private String id;
			private String [] ageGroups;
			private String [] categories;
			private String [] tags;
			private String [] learning_levels;
			private String [] grades;

			
			public int getSub_categories() {
				return sub_categories;
			}



			public String getLanguage() {
				return language;
			}
			public String getId() {
				return id;
			}
			public String[] getAgeGroups() {
				if(ageGroups == null){
					ageGroups = new String[0];
				}

				return ageGroups;
			}
			public String[] getCategories() {
				if(categories == null){
					categories = new String[0];
				}
				return categories;
			}

			public String[] getGrades() {
				if(grades == null){
					grades = new String[0];
				}
				return grades;
			}
			public String[] getLearning_levels() {
				if(learning_levels == null){
					learning_levels = new String[0];
				}
				return learning_levels;
			}
			public String[] getTags() {
				if(tags == null){
					tags = new String[0];
				}
				return tags;
			}

		}


		public String getUser_name() {
			return user_name;
		}


		public String getTitle() {
			return title;
		}


		public String getSynopis() {
			return synopis;
		}


		public int getPage_count() {
			return page_count;
		}


		public String getCover() {
			return cover;
		}


		public String getDownload_url() {
			return download_url;
		}

	}

	public SearchListResponse(byte[] data) throws JSONException {


		JSONArray array = new JSONArray(new String(data));

		books = new Book[array.length()];

		for( int i = 0 ; i < books.length; i++){

			jObject = array.getJSONObject(i);

			Book book = new Book();
			book.user_name = jObject.getString("user_name");
			book.title = jObject.getString("title");
			book.synopis = jObject.getString("synopsis");
			book.page_count = jObject.getInt("page_count");
			book.cover = jObject.getString("cover");
			book.download_url = jObject.getString("download_url");
			book.price = jObject.getInt("price");
            book.currency = jObject.getString("currency");
			book.id = jObject.getString("id");
			Log.v("user id is:", ">" + book.id);


			jObject = jObject.getJSONObject("info");

			JSONArray ageGroups = jObject.getJSONArray("age_groups");

			Info info = new Info();
			info.ageGroups =  new String [ageGroups.length()];
			for (int index =0 ; index < ageGroups.length(); index++){

				info.ageGroups[index] = ageGroups.getString(index); 
				
			}
			
			
			

			try{
				JSONArray categories = jObject.getJSONArray("categories");

				info.categories = new String[categories.length()];
				for (int j = 0; j < categories.length(); j++) {
					info.categories[j] = categories.getString(j);
				}	} catch (JSONException e) {
				}



			try{
				JSONArray tags = jObject.getJSONArray("tags");

				//Info info2 = new Info();
				info.tags = new String[tags.length()];
				for (int j = 0; j < tags.length(); j++) {
					info.tags[j] = tags.getString(j);
				}
			} catch (JSONException e) {
			}



			try{

				JSONArray learning_levels = jObject.getJSONArray("learning_levels");

				//Info info2 = new Info();
				info.learning_levels = new String[learning_levels.length()];
				for (int j = 0; j < learning_levels.length(); j++) {
					info.learning_levels[j] = learning_levels.getString(j);
				}
			} catch (JSONException e) {
			}

			info.language = jObject.getString("language");

			//info.sub_categories = jObject.getInt("sub_categories");

			//info.price = jObject.getInt("price");

			//	info.is_free = jObject.getBoolean("is_free");
			try{

				JSONArray grade = jObject.getJSONArray("grades");
				info.grades = new String[grade.length()];
				for (int j = 0; j < grade.length(); j++) {
					info.grades[j] = grade.getString(j);
				}
			} catch (JSONException e) {
			}

			//book.info = info2;
			book.info = info;

			books[i] = book;


		}

		// jObject =new JSONObject(aJsonString);
		// String aJsonString1=jObject.getString("name");

		// Log.v("json strng value", aJsonString1);

	}

}
