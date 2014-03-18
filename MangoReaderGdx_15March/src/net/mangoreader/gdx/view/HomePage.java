package net.mangoreader.gdx.view;

import net.mangoreader.gdx.LibGdxMainScreen;
import net.mangoreader.gdx.controller.Assets;

import net.mangoreader.gdx.screens.Categoryscreen;
import net.mangoreader.gdx.screens.ScreenEnum;

import android.os.Bundle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
/*import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;*/
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class HomePage implements Screen ,InputProcessor{

	Game game;
	Rectangle createstory,free_story,My_Story,Store;
	float cameraheight = 768f;
	float camerawidth = 1024f;
	OrthographicCamera guiCam;
	SpriteBatch batcher;
	Vector3 touchPoint;
	boolean isTreeTouched;
	boolean isFreeStoryTouched;
	boolean isMyStoryTouched;
	boolean isStoreTouched;

	public HomePage( Game game) {
		this.game = game;

		guiCam = new OrthographicCamera(camerawidth, cameraheight);
		guiCam.position.set(camerawidth / 2, cameraheight / 2, 0);
		batcher = new SpriteBatch();
		createstory = new Rectangle(camerawidth/150f, cameraheight/3.3f, 370, 450);
		free_story = new Rectangle(camerawidth/2.57f, cameraheight/1.53f, 335, 210);
		Store = new Rectangle(camerawidth/1.46f, cameraheight/3.08f, 380, 350);
		My_Story = new Rectangle(camerawidth/2.68f, cameraheight/184f, 633, 252);
		touchPoint = new Vector3();
		Gdx.input.setInputProcessor(this);
	}



	private void draw(float delta) {

		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);
		//batcher.disableBlending();
		batcher.draw(Assets.homebg, 0, 0, camerawidth, cameraheight);
		Assets.createstory_treesprite.setPosition(createstory.x, createstory.y);
		Assets.mystory_sprite.setPosition(My_Story.x, My_Story.y);
		Assets.freestory_sprite.setPosition(free_story.x, free_story.y);
		Assets.store_sprite.setPosition(Store.x, Store.y);
		if(isTreeTouched)
		{
			Assets.createstory_treesprite.draw(batcher,0.5f);	
		}
		else
		{
			Assets.createstory_treesprite.draw(batcher);	

		}

		if(isFreeStoryTouched)
		{
			Assets.freestory_sprite.draw(batcher,0.5f);	
		}
		else
		{
			Assets.freestory_sprite.draw(batcher);	

		}

		if(isMyStoryTouched)
		{
			Assets.mystory_sprite.draw(batcher,0.5f);	
		}
		else
		{
			Assets.mystory_sprite.draw(batcher);	

		}
		if(isStoreTouched)
		{
			Assets.store_sprite.draw(batcher,0.5f);	
		}
		else
		{
			Assets.store_sprite.draw(batcher);	

		}


		batcher.enableBlending();

	}

	private void update(float delta) {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));


		}

	}

	@Override
	public void render (float delta) {
		batcher.begin();
		draw(delta);
		batcher.end();
		update(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}



	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
		//		System.out.println("down");
		//		return false;

		if (OverlapTester.createstory(createstory, touchPoint.x, touchPoint.y)) {
			Assets.playSound(Assets.clickSound);
			isTreeTouched=true;

			System.out.println("create story touchdown");


			return true;
		}

		if (OverlapTester.freestory(free_story, touchPoint.x, touchPoint.y)) {
			Assets.playSound(Assets.clickSound);
			isFreeStoryTouched=true;
			return true;
		}

		if (OverlapTester.store(Store, touchPoint.x, touchPoint.y)) {
			Assets.playSound(Assets.clickSound);
			System.out.println("store click");
	// change
			
		
		
			/*((StarAssault)game).startScreen(ScreenEnum.SCREEN_1.getValue());*/
			isStoreTouched = true;
			return true;
		}
		if (OverlapTester.my_story(My_Story, touchPoint.x, touchPoint.y)) {
			Assets.playSound(Assets.clickSound);
			isMyStoryTouched = true;
	       // game.setScreen(new Categoryscreen(game));
			/*((StarAssault)game).startScreen(ScreenEnum.SCREEN_3.getValue());*/
	//		game.setScreen(new BookRead(game));

			return true;
		}
		return true;

	}



	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

		

		if (OverlapTester.createstory(createstory, touchPoint.x, touchPoint.y)) {
			Assets.playSound(Assets.clickSound);

			isTreeTouched=false;
			return true;
		}

		if (OverlapTester.freestory(free_story, touchPoint.x, touchPoint.y)) {
			Assets.playSound(Assets.clickSound);

           isFreeStoryTouched = false;
			return true;
		}

		if (OverlapTester.store(Store, touchPoint.x, touchPoint.y)) {
			Assets.playSound(Assets.clickSound);
			
			//((StarAssault)game).startScreen("StoreBooks");
			isStoreTouched = false;
			Bundle bundle = new Bundle();
			bundle.putString("Activity", ScreenEnum.SCREEN_1.getValue());
			((LibGdxMainScreen)game).startScreen(bundle);

			return true;
		}
		if (OverlapTester.my_story(My_Story, touchPoint.x, touchPoint.y)) {
			Assets.playSound(Assets.clickSound);
			
			 isMyStoryTouched = false;
		//	((StarAssault)game).startScreen(ScreenEnum.SCREEN_3.getValue());
			//((StarAssault)game).startScreen(ScreenEnum.SCREEN_2.getValue());
			// game.setScreen(new BookRead(game));
			 game.setScreen(new Categoryscreen(game));

			return true;
		}
		return true;
	}



	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
