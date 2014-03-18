package com.mangoreader.network.request;

import android.util.Log;

import com.network.request.NetworkRequest;

public class AgeGroupRequest extends NetworkRequest {

	public AgeGroupRequest() {
		setType(NetworkRequest.GET);
	
		Log.e("Age req", "Age req");
		//http://api.mangoreader.com/api/v2/available_languages.json
		System.out.println("http://api.mangoreader.com/api/v2/languages.json");
		setUrl("http://api.mangoreader.com/api/v2/age_groups.json");
	
	}

}
