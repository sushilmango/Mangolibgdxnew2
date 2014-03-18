package com.mangoreader.network.request;



import android.view.View;

import com.network.request.NetworkRequest;

public class ImageRequest extends NetworkRequest {

	
	
	public ImageRequest(String source) {
		setType(GET);
		setUrl(source);
	}
	
}
