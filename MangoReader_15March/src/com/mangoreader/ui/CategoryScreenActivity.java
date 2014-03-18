package com.mangoreader.ui;

import net.mangoreader.gdx.Category;
import net.mangoreader.gdx.controller.ApplicationAssets;
import net.mangoreader.gdx.screens.AndroidScreenInterface;
import net.mangoreader.gdx.screens.ScreenEnum;
import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class CategoryScreenActivity extends AndroidApplication implements AndroidScreenInterface{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useWakelock = true; 
		//config.useGL20 = true;

	/*	String id = getIntent().getStringExtra(Defines.BOOK_ID);

		try {
			BookParser bp = new BookParser("52bc777369702d7522373c00", getApplicationContext());
			Story story = bp.getStory();*/
			
		/*	CategoryParser categoryParser = new CategoryParser(this);
			//categoryParser.getMap().containsKey(key);
			Log.e("namee", ""+categoryParser.getMap().containsKey("name"));
			Log.e("idddd", ""+categoryParser.getMap().containsKey("id"));
			Log.e("desc", ""+categoryParser.getMap().containsKey("desc"));*/
			
			///ScreenImpl scimpl = new ScreenImpl(story,this);
			initialize(new Category(this/*scimpl*/), config);

		/*} catch (JSONException e) {
			e.printStackTrace();
		}*/
		
	}
	
    @Override
    protected void onDestroy() {
    	ApplicationAssets.getInstance().clear();
    	super.onDestroy();
    }

	@Override
	public void startActivity(Bundle bundle) {
		Intent intent = new Intent();
		
		if(ScreenEnum.SCREEN_1.getValue().equals(bundle.get("Activity"))){
			intent.setClass(this, MainActivity.class);
		}else if(ScreenEnum.SCREEN_2.getValue().equals(bundle.get("Activity"))){
			intent.setClass(this, BookReaderActivity.class);
		}else if (ScreenEnum.SCREEN_3.getValue().equals(bundle.get("Activity"))) {
			intent.setClass(this, CategoryScreenActivity.class);
		}else if (ScreenEnum.SCREEN_4.getValue().equals(bundle.get("Activity"))) {
			String categoryname = bundle.getString("Category");
			intent.putExtra("Category", categoryname);
			intent.setClass(this, MyBooksActivity.class);
		}
		startActivity(intent);
		
	}

	@Override
	public void onHomeClick() {
		finish();
	}
	
	}
