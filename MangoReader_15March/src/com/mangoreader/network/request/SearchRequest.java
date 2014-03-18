package com.mangoreader.network.request;

import android.util.Log;

import com.network.request.NetworkRequest;

public class SearchRequest extends NetworkRequest {
	String wordsearch;
	public SearchRequest(String word) {
		this.wordsearch = word;
		setType(NetworkRequest.GET);
         Log.e("search url",""+"http://api.mangoreader.com/api/v2/livestories/search?q="+wordsearch);
		System.out.println("http://api.mangoreader.com/api/v2/categories.json");
		setUrl("http://api.mangoreader.com/api/v2/livestories/search?q="+wordsearch);

	}

}
