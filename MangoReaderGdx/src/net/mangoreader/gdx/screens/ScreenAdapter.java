package net.mangoreader.gdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public interface ScreenAdapter extends AndroidScreenInterface{

	public static String LEFT = "left";
	public static String RIGHT = "right";
	public static String TOP = "top";
	public static String BOTTOM = "bottom";
	public Boolean pagecount = false;
	
    
	
	public Screen getScreenAt(int postion);
	
	public int getCount();
	
	public void setGame(Game game);
	
	public void setNextScreen();
	public void setPrevScreen();
	
	public void setAutoNextScreen(); 
	
	public void onHomeClick();
	
	
}
