package com.mangoreader.network.request;

import android.util.Log;

import com.network.request.NetworkRequest;

public class LanguageRequest extends NetworkRequest {

	public LanguageRequest() {
		setType(NetworkRequest.GET);
	
		Log.e("language req", "language req");
		//http://api.mangoreader.com/api/v2/available_languages.json
		System.out.println("http://api.mangoreader.com/api/v2/languages.json");
		setUrl("http://api.mangoreader.com/api/v2/available_languages.json");
	
	}

}
