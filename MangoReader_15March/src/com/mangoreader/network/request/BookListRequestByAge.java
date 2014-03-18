package com.mangoreader.network.request;

import android.util.Log;

import com.network.request.NetworkRequest;

public class BookListRequestByAge  extends NetworkRequest{
	
	String agegroups;
	public BookListRequestByAge(String age) {
		this.agegroups = age;
		setType(NetworkRequest.GET);
		
		setUrl("http://api.mangoreader.com/api/v2/livestories/by/agegroup/"+agegroups);
		Log.e("url", "http://api.mangoreader.com/api/v2/livestories/by/agegroup/"+agegroups);
	}
	
	public String getAgeGroup(){
		
		return agegroups;
	}

}
