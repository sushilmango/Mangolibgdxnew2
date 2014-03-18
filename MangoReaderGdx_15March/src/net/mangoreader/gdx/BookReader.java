package net.mangoreader.gdx;

import net.mangoreader.gdx.controller.AndroidScreenManager;
import net.mangoreader.gdx.controller.AssetManager;
import net.mangoreader.gdx.controller.Assets;
import net.mangoreader.gdx.controller.CategoryAssets;
import net.mangoreader.gdx.controller.StoryStateListener;
import net.mangoreader.gdx.screens.ScreenAdapter;
import net.mangoreader.gdx.view.BookRead;

import com.badlogic.gdx.Game;

public class BookReader extends Game {

	//ScreenAdapter mScreen;
	AndroidScreenManager manager;
	StoryStateListener listener;
	String bookId;

	public BookReader(String id, AndroidScreenManager manager,StoryStateListener listener) {
		/*mScreen = screen;
		mScreen.setGame(this);*/
		this.manager = manager;
		this.listener = listener;
		bookId = id;
	}

	/* public StarAssault(AndroidScreenManager screen) {
	   this.manager = screen;
   }
	 */
	@Override
	public void create() {
		Assets.load();
		CategoryAssets.load();
		AssetManager.getInstance().init(bookId);
		setScreen(new BookRead(this));
		
		/*if(mScreen.getCount() > 0){
			setScreen(mScreen.getScreenAt(0));
	}*/

	}
	
	public void finish(){
		
		manager.finishActivity();
	}
	
	public void playGames(){
		
		listener.startGames(bookId);
	}

	public void readBook(){

		ScreenAdapter adapter = listener.onLoadStory(bookId);
		adapter.setGame(this);
		
		if(adapter.getCount() > 0){
			setScreen(adapter.getScreenAt(0));
	}
		/*Log.e("Start Assult", "Start Assult");
		mScreen.startActivity(bundle);*/
	}

}
