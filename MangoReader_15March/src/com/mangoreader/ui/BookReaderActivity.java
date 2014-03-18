package com.mangoreader.ui;

import net.mangoreader.gdx.BookReader;
import net.mangoreader.gdx.controller.AndroidScreenManager;
import net.mangoreader.gdx.controller.AssetManager;
import net.mangoreader.gdx.controller.StoryStateListener;
import net.mangoreader.gdx.data.model.Layer;
import net.mangoreader.gdx.data.model.Story;
import net.mangoreader.gdx.screens.AndroidScreenInterface;
import net.mangoreader.gdx.screens.ScreenAdapter;

import org.json.JSONException;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mangoreader.data.BookParser;
import com.mangoreader.ui.libgdx.ScreenImpl;
import com.mangoreader.utils.Defines;
import com.mangoreader.views.LabelView;

public class BookReaderActivity extends AndroidApplication implements StoryStateListener, AndroidScreenManager  {
	
	private LabelView storyTextView;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useWakelock = true; 
		//config.useGL20 = true;

		String id = getIntent().getStringExtra(Defines.BOOK_ID);
		Log.e("IDDDDDDDDDDDDDD",""+id);
		
		RelativeLayout storyPageLayout = new RelativeLayout(this);
		storyTextView = new LabelView(this);

		/*try {*/
			
			storyTextView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			
			storyTextView.setTextColor(Color.BLACK);
			
			/*//BookParser bp = new BookParser("52bc777369702d7522373c00", getApplicationContext());
			//AssetManager.getInstance().init("52bc777369702d7522373c00");
			BookParser bp = new BookParser(id, getApplicationContext());
			AssetManager.getInstance().init(id);
			Story story = bp.getStory();
			System.out.println("story..............."+story); 
			Log.e("story.......", ""+story);
			ScreenImpl scimpl = new ScreenImpl(story,this,this);*/
			
			View libgdxViewStoryPage = initializeForView(new BookReader(id,this,this), config);
			
			storyPageLayout.addView(libgdxViewStoryPage);
			
			storyPageLayout.addView(storyTextView);
		/*} catch (JSONException e) {
			e.printStackTrace();
		}*/
		
		setContentView(storyPageLayout);
	}

	@Override
	public void onPageTextChange(final String text,final Layer layer) {
		if(text!= null){
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				storyTextView.invalidateLayer(BookReaderActivity.this, layer);
				storyTextView.setText(text);
			}
		});
		
		}
	}

	@Override
	public ScreenAdapter onLoadStory(String id) {
		
		try {
		//BookParser bp = new BookParser("52bc777369702d7522373c00", getApplicationContext());
		//AssetManager.getInstance().init("52bc777369702d7522373c00");
		BookParser bp = new BookParser(id, getApplicationContext());
		
		Story story = bp.getStory();
		/*System.out.println("story..............."+story); 
		Log.e("story.......", ""+story);*/
		ScreenImpl scimpl = new ScreenImpl(story,this,this);
		
		return scimpl;
		
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void startActivity(String activityName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishActivity() {
		finish();
		
	}

	@Override
	public void startGames(String id) {
		
		Intent intent = new Intent();
		intent.setClass(this, GameDisplayActivity.class);
		intent.putExtra("BOOK_ID", id);
		startActivity(intent);
		
		
	}


	
}