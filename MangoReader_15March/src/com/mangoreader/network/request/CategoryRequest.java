package com.mangoreader.network.request;

import com.network.request.NetworkRequest;

public class CategoryRequest extends NetworkRequest {

	public CategoryRequest() {
		setType(NetworkRequest.GET);
	
		System.out.println("http://api.mangoreader.com/api/v2/categories.json");
		setUrl("http://api.mangoreader.com/api/v2/categories.json");
	
	}

}
