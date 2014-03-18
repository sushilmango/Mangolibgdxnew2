package net.mangoreader.gdx;

import net.mangoreader.gdx.controller.AndroidScreenManager;
import net.mangoreader.gdx.controller.ApplicationAssets;
import net.mangoreader.gdx.controller.Assets;
import net.mangoreader.gdx.screens.ScreenAdapter;
import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.Game;

public class BookReader extends Game {

	ScreenAdapter mScreen;
	AndroidScreenManager manager;

	public BookReader(ScreenAdapter screen) {
		mScreen = screen;
		mScreen.setGame(this);
	}

	/* public StarAssault(AndroidScreenManager screen) {
	   this.manager = screen;
   }
	 */
	@Override
	public void create() {
		Assets.load();
		
		
		if(mScreen.getCount() > 0){
			setScreen(mScreen.getScreenAt(0));
	}

	}

	public void startScreen(Bundle bundle){

		Log.e("Start Assult", "Start Assult");
		mScreen.startActivity(bundle);
	}

}
