package com.mangoreader.network.request;

import android.util.Log;

import com.network.request.NetworkRequest;

public class GradeRequest extends NetworkRequest {

	public GradeRequest() {
		setType(NetworkRequest.GET);
	
		Log.e("grade req", "grade req");
		//http://api.mangoreader.com/api/v2/available_languages.json
		System.out.println("http://api.mangoreader.com/api/v2/languages.json");
		setUrl("http://api.mangoreader.com/api/v2/grades.json");
	
	}

}
