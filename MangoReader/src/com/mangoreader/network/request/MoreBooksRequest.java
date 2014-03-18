package com.mangoreader.network.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.client.utils.URIUtils;

import android.util.Log;

import com.network.request.NetworkRequest;

public class MoreBooksRequest extends NetworkRequest{
	
	String agegroups,query;
	String group;
	public MoreBooksRequest(String age,String group) {
		this.agegroups = age;
		this.group = group;
		setType(NetworkRequest.GET);
		Log.e("group"+group, "agegroup"+agegroups);
		try {
			 query = URLEncoder.encode(agegroups, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 	query = query.replace("+", "%20");
		setUrl("http://api.mangoreader.com/api/v2/livestories/by/"+group+"/"+query+"?limit=100");
		Log.e("url>>>>>>>>>>>>","url>>>>>>>>>>>>"+"http://api.mangoreader.com/api/v2/livestories/by/"+group+"/"+query+"?limit=5");
	}
	//http://api.mangoreader.com/api/v2/livestories/search?q="+wordsearch
	
	public String getAgeGroup(){
		
		return agegroups;
	}

}
