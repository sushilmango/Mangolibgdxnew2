package com.mangoreader.network.request;

import com.mangoreader.utils.Defines;
import com.network.request.NetworkRequest;

public class BookListRequest extends NetworkRequest {

	
	public BookListRequest() {
		setType(NetworkRequest.GET);
		//setUrl(Defines.BASE_URL+Defines.Books_PATH+"/?auth_token=9CqUY8J6YTjwUmS7HYRE");
		System.out.println(Defines.BASE_URL+Defines.Books_PATH+"/?limit=500");
		setUrl("http://api.mangoreader.com/api/v2/livestories.json/?limit=500");
		/*setUrl("http://testapi.mangoreader.com/api/v2/livestories/by/agegroup/0-2");*/
	}
	
}
