package com.mangoreader.network.request;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.mangoreader.utils.Defines;
import com.network.request.NetworkRequest;

public class LoginRequest extends NetworkRequest {

	private final String userName = "email";
	private final String userPassword = "password";
	
	public LoginRequest(String email, String password) {
		setType(NetworkRequest.POST);
		setUrl(Defines.BASE_URL+Defines.LOGIN_PATH);
		List<NameValuePair> loginData = new ArrayList<NameValuePair>();
		loginData.add(new BasicNameValuePair(userName, email));
		loginData.add(new BasicNameValuePair(userPassword, password));
		
		setNameValuePair(loginData);
	}
	
	
	
}
