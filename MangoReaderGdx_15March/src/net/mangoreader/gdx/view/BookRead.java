package  net.mangoreader.gdx.view;



import net.mangoreader.gdx.BookReader;
import net.mangoreader.gdx.LibGdxMainScreen;
import net.mangoreader.gdx.controller.AssetManager;
import net.mangoreader.gdx.controller.Assets;
import net.mangoreader.gdx.controller.Settings;
import net.mangoreader.gdx.screens.ScreenAdapter;
import net.mangoreader.gdx.screens.ScreenEnum;
import android.os.Bundle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class BookRead implements Screen, InputProcessor {

	ScreenAdapter mScreen;
	Game game;
	float cameraheight = 768f;
	float camerawidth = 1024f;
	OrthographicCamera guiCam;
	SpriteBatch batcher;
	Rectangle soundBounds;
	Rectangle playBounds;
	Rectangle highscoresBounds;
	Rectangle helpBounds;
	Vector3 touchPoint;
	Circle logo;
	Rectangle imageviewrect, read_to_me,read_by_myself,play_games,detailimage;
	Texture coverBitmap; 

	public BookRead(Game game) {
		this.game = game;


		guiCam = new OrthographicCamera(camerawidth, cameraheight);
		guiCam.position.set(camerawidth / 2, cameraheight / 2, 0);
		batcher = new SpriteBatch();
		soundBounds = new Rectangle(0, 0, 64, 64);
		playBounds = new Rectangle(50,200, 100, 36);
		//	playBounds = new Rectangle(x, y, width, height)
		highscoresBounds = new Rectangle(160 - 150, 200 - 18, 300, 36);
		helpBounds = new Rectangle(160 - 150, 200 - 18 - 36, 300, 36);
		touchPoint = new Vector3();
		logo = new Circle(new Vector2(15, 670),Assets.logo.getWidth()/2);

		imageviewrect = new Rectangle(camerawidth/17,cameraheight/5 , 592, 449);
		read_to_me = new Rectangle(camerawidth/1.47f, cameraheight/1.85f, 257, 73);
		read_by_myself = new Rectangle(camerawidth/1.47f, cameraheight/2.34f, 257,73);
		play_games = new Rectangle(camerawidth/1.47f, cameraheight/3.14f, 257,73);
		detailimage = new Rectangle(camerawidth/14.2f, cameraheight/4.62f, 568, 423);
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
		
		coverBitmap = AssetManager.getInstance().loadImage("res/cover.jpg");
	}

	public void update (float deltaTime) {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (OverlapTester.pointInRectangle(playBounds, touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);

				/*game.setScreen(new GameScreen(game));
				System.out.println(touchPoint.x);
				System.out.println(touchPoint.y);*/
				return;
			}

			if (OverlapTester.pointInHomeLogo(logo,new Vector2(touchPoint.x, touchPoint.y))) {
				
				//game.setScreen(new HomePage(game));
				
				((BookReader)game).finish();
				
				System.out.println("click");

				return ;
			}

			if (OverlapTester.readforme(read_to_me,touchPoint.x,touchPoint.y)) {
				
				((BookReader)game).readBook();

				System.out.println("raed for me");

				return ;
			}
			
			if (OverlapTester.playgames(play_games,touchPoint.x,touchPoint.y)) {
				
				((BookReader)game).playGames();

				System.out.println("play_games  ");

				return ;
			}

			if (OverlapTester.readbyme(read_by_myself,touchPoint.x,touchPoint.y)) {


				System.out.println("raed by self");

				return ;
			}

			if (OverlapTester.detailimage(detailimage,touchPoint.x,touchPoint.y)) {


				System.out.println("detail image");

				return ;
			}
		}
	}

	long last = System.nanoTime();
	public void draw (float deltaTime) {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);

		batcher.disableBlending();
		batcher.begin();
		batcher.draw(Assets.backgroundRegion, 0, 0, 1024, 768);

		batcher.enableBlending();

		batcher.draw(Assets.logo, logo.x, logo.y);

		batcher.draw(Assets.imageviewrect, imageviewrect.x, imageviewrect.y, imageviewrect.width, imageviewrect.height);
		batcher.draw(Assets.readtome, read_to_me.x, read_to_me.y, read_to_me.width, read_to_me.height);
		batcher.draw(Assets.readbuyself, read_by_myself.x, read_by_myself.y, read_by_myself.width, read_by_myself.height);
		batcher.draw(Assets.playgames, play_games.x, play_games.y, play_games.width, play_games.height);
		batcher.draw(coverBitmap, detailimage.x, detailimage.y, detailimage.getWidth(), detailimage.getHeight());
		batcher.end();
	}
 
	@Override
	public void render (float delta) {
		update(delta);
		draw(delta);
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void show () {
	}

	@Override
	public void hide () {
	}

	@Override
	public void pause () {
		Settings.save();
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.BACK){
			
			// Do your optional back button handling (show pause menu?)
		}
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
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
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
