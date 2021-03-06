package net.mangoreader.gdx;

import net.mangoreader.gdx.controller.Assets;
import net.mangoreader.gdx.controller.CategoryAssets;
import net.mangoreader.gdx.screens.AndroidScreenInterface;
import net.mangoreader.gdx.view.HomePage;
import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.Game;



public class LibGdxMainScreen extends Game {

	AndroidScreenInterface mScreen;

	public LibGdxMainScreen(AndroidScreenInterface screen) {
		
		mScreen  = screen;
	}
	
	@Override
	public void create() {
		Assets.load();
		CategoryAssets.load();
		setScreen(new HomePage(this));

	}

	public void startScreen(Bundle bundle){

		Log.e("Start Assult", "Start Assult");
		mScreen.startActivity(bundle); 
	}

}
