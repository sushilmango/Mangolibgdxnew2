package com.mangoreader.network.response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class AgeGroupResponse {

	private JSONObject jObject;

	private AgeGroup [] ages;

	public AgeGroup[] getAges() {
		return ages;
	}
	

	public static class AgeGroup {
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

	public AgeGroupResponse(byte[] data) throws JSONException {


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
	}

}
