package com.mangoreader.network.response;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class SignupResponse {

	private JSONObject jObject;

	private String user;
	private String auth_token;
	private String email;
	private String id;

	public String getUser() {
		return user;
	}

	public String getAuth_token() {
		return auth_token;
	}

	public String getEmail() {
		return email;
	}

	public String getId() {
		return id;
	}


	public SignupResponse(byte[] data) throws JSONException {

		jObject = new JSONObject(new String(data));
		
		Log.e("DATA",""+data);
		if (!jObject.isNull("auth_token")) {
			user = jObject.getString("name");
			auth_token = jObject.getString("auth_token");
			
			Log.v("from json auth token", auth_token);
			Log.v("if json not null", "in if loop in login");
			Log.v("json strng value", user);

			
			email = jObject.getString("email");

			id = jObject.getString("id");
			Log.v("user id is:", ">" + id);
			
		}
	}
}
