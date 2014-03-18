package com.mangoreader.ui;

import org.json.JSONException;

import com.mangoreader.R;
import com.mangoreader.network.request.LoginRequest;
import com.mangoreader.network.request.SignupRequest;
import com.mangoreader.network.response.LoginResponse;
import com.mangoreader.network.response.SignupResponse;
import com.mangoreader.ui.LoginScreen;
import com.mangoreader.utils.Defines;
import com.network.HttpResponseHandler;
import com.network.NetworkHandler;
import com.network.request.NetworkRequest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SignUpScreen extends Activity implements OnClickListener, HttpResponseHandler {
	ImageView signup;
	EditText emailidedittext,passwordEditText,usernameEdittext;
	public AlertDialog.Builder alertDialogBuilder;
	SharedPreferences.Editor editor;

	private ProgressDialog mDialog;
	Thread thread;
	SharedPreferences prefs;
	String username,password,email,Loginurl, base_url , unzipfile;
	private RelativeLayout layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		
		usernameEdittext = (EditText)findViewById(R.id.edittextusername);
	
		
		emailidedittext = (EditText)findViewById(R.id.edittextEmail);
		
		
		passwordEditText = (EditText)findViewById(R.id.edittextpassword);
		
		
		signup = (ImageView)findViewById(R.id.signup);
		signup.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		username = usernameEdittext.getText().toString();
		email = emailidedittext.getText().toString();
		password = passwordEditText.getText().toString();
		Toast.makeText(this,""+username+email+password,Toast.LENGTH_LONG).show();
	
		NetworkHandler.getInstance().init();
		SignupRequest request = new SignupRequest(username,password);
		request.setNetworkResponseHandler(this);
		NetworkHandler.getInstance().addRequest(request);
	}

	@Override
	public void onNetworkResponse(NetworkRequest req, byte[] data) {
		if(req instanceof SignupRequest){

			try {
				SignupResponse response = new SignupResponse(data);
				System.out.println(">>>>>>>>>>>"+response.getUser());
				if(!TextUtils.isEmpty(response.getUser())){
				//Toast.makeText(this, "sucess",Toast.LENGTH_SHORT).show();
					Log.e("Sucess","");
				}else {
					//Toast.makeText(this, " not sucess",Toast.LENGTH_SHORT).show();
					Log.e("NOt Sucess","");			
				}
				
			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void onNetworkResponseProgress(NetworkRequest arg0, long arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNetworkResponseStart(NetworkRequest arg0, long arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNetworkResponseStop(NetworkRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNetworkError(NetworkRequest paramNetworkRequest, String code) {
		// TODO Auto-generated method stub
		
	}

}
