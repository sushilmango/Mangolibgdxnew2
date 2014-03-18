package com.mangoreader.network.request;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.mangoreader.utils.Defines;
import com.network.request.NetworkRequest;

public class SignupRequest extends NetworkRequest {
	
	private final String userName = "username";
	private final String Email = "email";
	
	private final String userPassword = "password";
	
	public SignupRequest(String username, String password) {
		setType(NetworkRequest.POST);
		setUrl(Defines.BASE_URL+Defines.SIGN_UP);
		List<NameValuePair> loginData = new ArrayList<NameValuePair>();
		loginData.add(new BasicNameValuePair(userName, username));
//		/loginData.add(new BasicNameValuePair(Email,email));
		loginData.add(new BasicNameValuePair(userPassword, password));
		
		
		setNameValuePair(loginData);
	}
	
	

}
