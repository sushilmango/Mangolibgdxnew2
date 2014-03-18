package com.mangoreader.ui;



import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mangoreader.R;
import com.mangoreader.network.request.BookDownloadRequest;
import com.mangoreader.network.request.BookListRequest;
import com.mangoreader.network.request.LoginRequest;
import com.mangoreader.network.response.BookDownloadResponse;
import com.mangoreader.network.response.BookListResponse;
import com.mangoreader.network.response.BookListResponse.Book;
import com.mangoreader.network.response.LoginResponse;
import com.mangoreader.utils.Defines;
import com.mangoreader.utils.Utils;
import com.network.HttpResponseHandler;
import com.network.NetworkHandler;
import com.network.request.NetworkRequest;


public class LoginScreen extends Activity implements OnClickListener, HttpResponseHandler {
	ImageView submit,skipsign,signup;
	EditText emailidedittext,passwordEditText;
	public AlertDialog.Builder alertDialogBuilder;
	SharedPreferences.Editor editor;
	///ThreadDefaultBoook threadCustom;
	private ProgressDialog mDialog;
	Thread thread;
	SharedPreferences prefs;
	String username,password,Loginurl, base_url , unzipfile;
	private RelativeLayout layout;

	static private LoginScreen activity;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);


		layout = (RelativeLayout)findViewById(R.id.relative);
		final Animation animationFalling = AnimationUtils.loadAnimation(this, R.anim.falling);
		layout.startAnimation(animationFalling);

		emailidedittext = (EditText)findViewById(R.id.edittextusername);

		passwordEditText = (EditText)findViewById(R.id.edittextpassword);
		submit = (ImageView)findViewById(R.id.signin);
		submit.setOnClickListener(this);
		skipsign = (ImageView)findViewById(R.id.skip1);
		skipsign.setOnClickListener(this);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		signup = (ImageView)findViewById(R.id.signup);
		signup.setOnClickListener(this);


		editor = prefs.edit();

		String userName = prefs.getString("username", "");
	/*	if  (userName.length()!=0) {

			Intent k = new Intent();
			k.setClass(LoginScreen.this, HomeScreenActivity.class);
			k.putExtra(Defines.BOOK_ID, "");
			startActivity(k);
			finish();
		}*/
		
		if (prefs.contains("username")) {
			Intent k = new Intent();
			k.setClass(LoginScreen.this, HomeScreenActivity.class);
			k.putExtra(Defines.BOOK_ID, "");
			startActivity(k);
			finish();
		}
		base_url = "http://test.mangoreader.com/api/v2/";
		Loginurl = prefs.getString("base_url", "null");
	}

	@Override
	public void onClick(View v) {
		if (v.getId()==R.id.signin) {

			if (Utils.isOnline(this)==false) {

				Toast.makeText(this, "plz check internet connection", Toast.LENGTH_SHORT).show();
			}

			Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();

			username = emailidedittext.getText().toString();
			password = passwordEditText.getText().toString();



			String loginurl = base_url + "sign_in?email="
					+ username + "&password=" + password;
			Log.e("username", username);

			Log.e("passwd", password);

			NetworkHandler.getInstance().init();
			LoginRequest request = new LoginRequest(username, password);
			request.setNetworkResponseHandler(this);
			NetworkHandler.getInstance().addRequest(request);


			editor.putString("username",username);
			editor.putString("password", password);
			editor.putString("base_url",base_url);
			editor.commit();
		}if (v.getId()==R.id.skip1) {
			if (Utils.isOnline(this)==false) {

				Toast.makeText(this, "plz check internet connection", Toast.LENGTH_SHORT).show();
			}

			Toast.makeText(this, " internet connection", Toast.LENGTH_SHORT).show();

			/*NetworkHandler.getInstance().init();
			BookListRequest request = new BookListRequest();
			NetworkHandler.getInstance().addRequest(request);

			request.setNetworkResponseHandler(this);
			 */
			/*NetworkHandler.getInstance().init();
			BookDownloadRequest request = new BookDownloadRequest("52bc780669702d7522e43c00");
			request.setNetworkResponseHandler(this); 
			NetworkHandler.getInstance().addRequest(request);*/

			Intent k = new Intent();
			k.setClass(LoginScreen.this, HomeScreenActivity.class);
			startActivity(k);

		}if (v.getId()==R.id.signup) {

			Intent intent = new Intent(this,SignUpScreen.class);
			startActivity(intent);

			Toast.makeText(this, "Sign UP",Toast.LENGTH_SHORT).show();
		}


	}


	public void hideDialog() {
		runOnUiThread(new Runnable() {

			public void run() {
				if (mDialog != null) {
					if (mDialog.isShowing()) {
						mDialog.dismiss();
					}

				}
			}
		});


	}

	public void showDialog() {


		runOnUiThread(new Runnable() {

			public void run() {
				if (!LoginScreen.this.isFinishing()) {
					mDialog = ProgressDialog.show(LoginScreen.this,
							"Please wait...", "Login Into Account ...", true);
				}
			}
		});

	}

	@Override
	public void onNetworkResponse(NetworkRequest req, byte[] data) {

		if(req instanceof LoginRequest){

			try {
				LoginResponse response = new LoginResponse(data);
				System.out.println(">>>>>>>>>>>"+response.getAuth_token());
				if(!TextUtils.isEmpty(response.getAuth_token())){
					editor.putString("auth_token",response.getAuth_token());
					editor.commit();
					Intent k = new Intent();
					k.setClass(LoginScreen.this, HomeScreenActivity.class);
					k.putExtra(Defines.BOOK_ID, "");
					startActivity(k);
					finish();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (req instanceof BookListRequest){
			try {
				BookListResponse response = new BookListResponse(data);
				System.out.println(">>>>>>>>>>>" + response.getBooks());

				for(Book book : response.getBooks()){ 

					System.out.println(book.getTitle());
					Intent intent = new Intent(LoginScreen.this,StoreBooks.class);
					startActivity(intent);

					/*		for(String age : book.getInfo().getAgeGroups()){
						System.out.println("Age:"+age);
					}

					for (String categories: book.getInfo().getCategories()) {
						System.out.println("categories"+categories);

					}
					for (String tags:book.getInfo().getTags()) {
						System.out.println("tags"+tags);

					}


					for (String grades:book.getInfo().getGrades()) {
						System.out.println("grades"+grades);

					}

					System.out.println("Languages > "+book.getInfo().getLanguage());
					System.out.println("price > "+book.getInfo().getPrice());


					for (String learning_levels:book.getInfo().getLearning_levels()) {
						System.out.println("learning_levels"+learning_levels);

					}

					 */
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (req instanceof BookDownloadRequest){

			Log.e("book download req", "book download req");
			new BookDownloadResponse(getApplicationContext(),((BookDownloadRequest)req).getBookId()).execute(data); 

		}

		System.out.println(data);
		System.out.println(req.getUrl()+ " : : "+ new String(data));  
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

	//http://test.mangoreader.com/live_stories/52d0dd5669702d06e2040000/res/cover_65269b7e87.jpg

}
