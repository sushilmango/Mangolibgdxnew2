package com.mangoreader.network.response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class GradesResponse {

	private JSONObject jObject;

	private Grades [] grades;
	
	public Grades[] getGrade() {
		return grades;
	}

	

	public static class Grades {
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

	public GradesResponse(byte[] data) throws JSONException {


		JSONArray array = new JSONArray(new String(data));

		grades = new Grades[array.length()];

		for( int i = 0 ; i < grades.length; i++){

			jObject = array.getJSONObject(i);

			Grades grade = new Grades();
			grade.id = jObject.getString("id");
			grade.name = jObject.getString("name");
			grade.desc = jObject.getString("desc");
			Log.e("category id is:", ">" + grade.id);
			Log.e("category name:", ">" + grade.name);
			Log.v("category desc:", ">" + grade.desc);



			grades[i] = grade;


		}
	}

}
