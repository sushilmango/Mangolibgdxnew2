package com.mangoreader.network.request;

import com.mangoreader.utils.Defines;
import com.network.request.NetworkRequest;

public class FeaturedImagesRequest extends NetworkRequest {

	public FeaturedImagesRequest() {
		setType(NetworkRequest.GET);
		setUrl("http://api.mangoreader.com/api/v2/livestories/featured.json?platform=ios");
		//setUrl("http://testapi.mangoreader.com/api/v2/livestories/by/agegroup/0-2");
	}
}
