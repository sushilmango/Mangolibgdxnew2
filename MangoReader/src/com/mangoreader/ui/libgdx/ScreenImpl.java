package com.mangoreader.ui.libgdx;

import net.mangoreader.gdx.controller.AssetManager;
import net.mangoreader.gdx.data.model.Page;
import net.mangoreader.gdx.data.model.Story;
import net.mangoreader.gdx.screens.ScreenAdapter;
import net.mangoreader.gdx.screens.ScreenEnum;
import net.mangoreader.gdx.screens.StoryPageScreen;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mangoreader.ui.BookReaderActivity;
import com.mangoreader.ui.CategoryScreenActivity;
import com.mangoreader.ui.MainActivity;
import com.mangoreader.ui.MyBooksActivity;

public class ScreenImpl/* extends AndroidApplication*/ implements ScreenAdapter {
	
	private Game game;
	private int cPosition;
	private Story mStory;
	private SparseArray<StoryPageScreen> screenStack;
	private Context mContext;
	int pageno;
	public Boolean boolean1 = false;
	public Boolean getBoolean1() {
		return boolean1;
	}
	public void setBoolean1(Boolean boolean1) {
		this.boolean1 = boolean1;
	}
	
	public ScreenImpl(Story story,Context c) {
		mStory = story;
		screenStack = new SparseArray<StoryPageScreen>();
		mContext = c;
	
	}
	
	@Override
	public Screen getScreenAt(int postion) {
		
		Page[] pages = mStory.getPages();
		Page page = pages[postion];
		
		if(screenStack.get(postion) == null){
			screenStack.put(postion,new StoryPageScreen(this,page));
		}else{
			screenStack.get(postion).reload();
		}
		
		Log.e("POSITION", ""+postion);
		pageno = postion;
		return screenStack.get(postion); 
	}

	@Override
	public int getCount() {

		return mStory.getPages().length;
	}

	@Override
	public void setNextScreen() {
		Log.e("","??????"+cPosition);
		
		
		
		
		if(cPosition < getCount() - 1){
			game.setScreen(getScreenAt(cPosition + 1));
			Log.e("Cposition setNextScreen",""+getCount());
			cPosition++;
		}if (pageno==getCount()-1) {
			
			 
			Log.e("page finish",""+cPosition);
		}
			
		
	
	}

	@Override
	public void setPrevScreen() {
		Log.e("","??????++"+cPosition); 
		
		if(cPosition > 0){
			
			screenStack.remove(cPosition);
			
			game.setScreen(getScreenAt(cPosition - 1));
			Log.e("Cposition setPrevScreen",""+cPosition);
			
			cPosition--;
		}
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public void startActivity(Bundle bundle) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		
		if(ScreenEnum.SCREEN_1.getValue().equals(bundle.get("Activity"))){
			intent.setClass(mContext, MainActivity.class);
		}else if(ScreenEnum.SCREEN_2.getValue().equals(bundle.get("Activity"))){
			intent.setClass(mContext, BookReaderActivity.class);
		}else if (ScreenEnum.SCREEN_3.getValue().equals(bundle.get("Activity"))) {
			intent.setClass(mContext, CategoryScreenActivity.class);
		}else if (ScreenEnum.SCREEN_4.getValue().equals(bundle.get("Activity"))) {
			String categoryname = bundle.getString("Category");
			intent.putExtra("Category", categoryname);
			intent.setClass(mContext, MyBooksActivity.class);
		}
		mContext.startActivity(intent);
		Log.e("start act", "start act");		
	}

	@Override
	public void setAutoNextScreen() {
		Log.e("",">>>>>>>"+cPosition);
		if(cPosition != 0 ){
			setNextScreen();
		}
	}
	@Override
	public void onHomeClick() {
		((Activity)mContext).finish();
	}

}
