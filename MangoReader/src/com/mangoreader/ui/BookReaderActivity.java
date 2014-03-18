package com.mangoreader.ui;

import net.mangoreader.gdx.BookReader;
import net.mangoreader.gdx.controller.AssetManager;
import net.mangoreader.gdx.data.model.Story;

import org.json.JSONException;

import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mangoreader.data.BookParser;
import com.mangoreader.ui.libgdx.ScreenImpl;
import com.mangoreader.utils.Defines;

public class BookReaderActivity extends AndroidApplication  {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useWakelock = true; 
		config.useGL20 = true;

		String id = getIntent().getStringExtra(Defines.BOOK_ID);
		Log.e("IDDDDDDDDDDDDDD",""+id);

		try {
			//BookParser bp = new BookParser("52bc777369702d7522373c00", getApplicationContext());
			//AssetManager.getInstance().init("52bc777369702d7522373c00");
			BookParser bp = new BookParser(id, getApplicationContext());
			AssetManager.getInstance().init(id);
			Story story = bp.getStory();
			/*System.out.println("story..............."+story); 
			Log.e("story.......", ""+story);*/
			ScreenImpl scimpl = new ScreenImpl(story,this);
			initialize(new BookReader(scimpl), config);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
}