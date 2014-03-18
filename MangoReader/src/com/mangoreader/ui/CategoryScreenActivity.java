package com.mangoreader.ui;

import net.mangoreader.gdx.Category;
import net.mangoreader.gdx.data.model.Story;

import org.json.JSONException;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mangoreader.data.BookParser;
import com.mangoreader.ui.libgdx.ScreenImpl;
import com.mangoreader.utils.Defines;

public class CategoryScreenActivity extends AndroidApplication {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useWakelock = true; 
		config.useGL20 = true;

		String id = getIntent().getStringExtra(Defines.BOOK_ID);

		try {
			BookParser bp = new BookParser("52bc777369702d7522373c00", getApplicationContext());
			Story story = bp.getStory();
			
		/*	CategoryParser categoryParser = new CategoryParser(this);
			//categoryParser.getMap().containsKey(key);
			Log.e("namee", ""+categoryParser.getMap().containsKey("name"));
			Log.e("idddd", ""+categoryParser.getMap().containsKey("id"));
			Log.e("desc", ""+categoryParser.getMap().containsKey("desc"));*/
			
			ScreenImpl scimpl = new ScreenImpl(story,this);
			initialize(new Category(scimpl), config);



		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	}
