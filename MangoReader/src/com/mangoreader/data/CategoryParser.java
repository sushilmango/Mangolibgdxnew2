package com.mangoreader.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;

import net.mangoreader.gdx.data.model.Layer;
import net.mangoreader.gdx.data.model.Page;
import net.mangoreader.gdx.data.model.Story;
import net.mangoreader.gdx.data.model.WordMap;
import net.mangoreader.gdx.data.model.Layer.Style;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mangoreader.network.request.CategoryRequest;
import com.mangoreader.network.response.CategoryResponse;
import com.mangoreader.network.response.CategoryResponse.CategoryBook;
import com.network.HttpResponseHandler;
import com.network.NetworkHandler;
import com.network.request.NetworkRequest;

import android.content.Context;
import android.util.Log;

public class CategoryParser implements HttpResponseHandler {


	private Context mContext;
	private File dir; 
	HashMap<String, String> map;
	public HashMap<String, String> getMap() {
		return map;
	}


	public CategoryParser(Context context) {
		this.mContext = context;
		Log.e("caregory","?????>>caregory");
		
		NetworkHandler.getInstance().init();
		CategoryRequest request = new CategoryRequest();
		NetworkHandler.getInstance().addRequest(request);

		request.setNetworkResponseHandler(this);
		map = new HashMap<String, String>();

	}





	private String getString(JSONObject json, String key){

		try{
			return json.getString(key);
		}catch(JSONException e){}
		catch(Exception e){}

		return "";
	}

	private float getFloat(String value) {
		try {
			return Float.parseFloat(value);

		} catch (NumberFormatException e) {
			return 0f;
		} 
	}



	private int getInt( String value){
		try {
			return Integer.parseInt(value);

		} catch (NumberFormatException e) {
			return 0;
		}
	}

	@Override
	public void onNetworkResponse(NetworkRequest req, byte[] data) {

		if (req instanceof CategoryRequest) {
			try {
				CategoryResponse response = new CategoryResponse(data);

				Log.e("response data", ""+response);


				for (CategoryBook book : response.getBooks()) {


					String name = book.getName();
					String id = book.getId();
					String desc = book.getDesc();


					map.put("name", name);
					map.put("id",id);
					map.put("desc", desc);
					Log.e("name is", ""+map.get("name"));
					Log.e("id is", ""+map.get("id"));
					Log.e("desc is", ""+map.get("desc"));

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}}


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
		
	}}