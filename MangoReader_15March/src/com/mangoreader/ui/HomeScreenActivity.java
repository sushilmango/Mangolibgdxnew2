package com.mangoreader.ui;

import net.mangoreader.gdx.LibGdxMainScreen;
import net.mangoreader.gdx.screens.AndroidScreenInterface;
import net.mangoreader.gdx.screens.ScreenEnum;
import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class HomeScreenActivity extends AndroidApplication implements AndroidScreenInterface  {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useWakelock = true; 
		//config.useGL20 = true;
		

		/*String id = getIntent().getStringExtra(Defines.BOOK_ID);

		try {
			BookParser bp = new BookParser("52d0f82d69702d32d61e5a00", getApplicationContext());
			Story story = bp.getStory();
			
			ScreenImpl scimpl = new ScreenImpl(story,this);*/
			
		initialize(new LibGdxMainScreen(this), config);
			
		/*} catch (JSONException e) {
			e.printStackTrace();
		}*/
		
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