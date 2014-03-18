package com.mangoreader.network.request;

import android.content.Context;
import android.util.Log;

import com.mangoreader.utils.Defines;
import com.network.request.DownloadRequest;
import com.network.request.NetworkRequest;

public class BookDownloadRequest extends DownloadRequest {

	private String id;
	private String email;
	private String auth_token;
	
	public BookDownloadRequest(Context context, String id,String email,String auth_token) {
		super(context);
		this.id=id;
		this.email = email;
		this.auth_token = auth_token;
		setType(NetworkRequest.GET);                                                        //  v_DmcdFyxR9-tznuuB5y
		setUrl(Defines.BOOKDOWNLOAD_URL+Defines.BooksDownload_PATH+"/"+id+"/"+"zipped?email="+email+"&auth_token="+auth_token);
		
		Log.e("Book Download URL", ""+Defines.BOOKDOWNLOAD_URL+Defines.BooksDownload_PATH+"/"+id+"/"+"zipped?email="+email+"&auth_token="+auth_token);
		//setUrl("http://api.mangoreader.com/api/v2/livestories/52bc2dd869702d7522570100/zipped?email=sushil@mangosense.com&auth_token=v_DmcdFyxR9-tznuuB5y");
	//	setUrl("http://testapi.mangoreader.com/api/v2/livestories/52bc777369702d7522373c00/zipped?email=sushil@mangosense.com&auth_token=9CqUY8J6YTjwUmS7HYRE");
		this.id = id;
		setFileName(id);
	}
	
	
	public String getBookId(){
		return id;
	}
	
}
