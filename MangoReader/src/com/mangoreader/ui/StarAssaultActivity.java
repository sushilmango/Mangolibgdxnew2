package com.mangoreader.ui;

import net.mangoreader.gdx.LibGdxMainScreen;
import net.mangoreader.gdx.controller.AndroidScreenManager;
import net.mangoreader.gdx.data.model.Story;
import net.mangoreader.gdx.screens.ScreenAdapter;

import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mangoreader.data.BookParser;
import com.mangoreader.data.CategoryParser;
import com.mangoreader.ui.libgdx.ScreenImpl;
import com.mangoreader.utils.Defines;

public class StarAssaultActivity extends AndroidApplication  {
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

		try {
			BookParser bp = new BookParser("52d0f82d69702d32d61e5a00", getApplicationContext());
			Story story = bp.getStory();
			
		
			ScreenImpl scimpl = new ScreenImpl(story,this);
			initialize(new LibGdxMainScreen(scimpl), config);



		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
}