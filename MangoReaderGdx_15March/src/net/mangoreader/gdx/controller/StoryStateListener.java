package net.mangoreader.gdx.controller;

import net.mangoreader.gdx.data.model.Layer;
import net.mangoreader.gdx.screens.ScreenAdapter;

public interface StoryStateListener {
	
	public ScreenAdapter onLoadStory(String id);

	//TODO pass page text JSON 
	public void onPageTextChange(String text, Layer layer);
	
	public void startGames(String id);
}
