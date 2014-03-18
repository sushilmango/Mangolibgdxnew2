package net.mangoreader.gdx.screens;

import java.util.ArrayList;
import java.util.Arrays;

import net.mangoreader.gdx.controller.AssetManager;
import net.mangoreader.gdx.controller.Assets;
import net.mangoreader.gdx.controller.StoryStateListener;
import net.mangoreader.gdx.data.model.Layer;
import net.mangoreader.gdx.data.model.Page;
import net.mangoreader.gdx.utils.Alignment;
import net.mangoreader.gdx.view.OverlapTester;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
/*import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;*/
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class StoryPageScreen implements Screen, InputProcessor {

	private Page pageModel;
	private Texture picture;
	private Vector2 imagePosition;
	private Vector2 imageDimen;
	//private Vector2 textDimen;
	
	private Vector2 textBoxDimen;

	private String storyText;
	private Music sound;
	private long sound_id;
	Circle logo,playbutton,pausebutton,menulogo,menuclose,game,icon_text,icon_replay,editimage;
	Rectangle menucircle;
	boolean menuclick= false;
	int soundid =0;
	private Layer mTextLayer;

	private void buildLayers(Layer layer) {
		/*if ("image".equals(layer.getType())) {
			buildImage(layer);
		} else*/ if ("text".equals(layer.getType())) {
			buildText(layer);
		}else if ("audio".equals(layer.getType())) {
			buidAudio(layer);
		}

	}
	
	/*private void buildGameView(){
		webCore = new WebCore(".");
		webView = webCore.createWebView(512, 512);
		webView.focus();
		webView.loadURL("http://www.google.at", "", "", "");
	}*/


	private void buidAudio(Layer layer){
		if (layer.getUrl()!=null) {

			/*sound =AssetManager.getInstance().loadSound(layer.getUrl());*/

			Log.e("Audio", "not null");
			sound =  AssetManager.getInstance().loadSound(layer.getUrl());
			sound.play();
			Log.e("Sound", "not null"+sound);
			System.out.println("SOUND NOT NULL"+sound);



		}else {
			Log.e("Audio", "null");

		}
	}

	private void buildText(Layer layer) {
	
	//	if (layer.getText()!=null) {

			storyText = layer.getText();
			
			if(alignment != null && alignment.getTextAlignment() != null){
				
				layer.setAlignment(alignment.getTextAlignment());
			/*	textDimen = alignment.getTextAlignment();
				textBoxDimen = new Vector2(camerawidth/3,cameraheight/3);*/
				
			}else {
				
				layer.setAlignment(null);
			}
				/*else{
			}
			
			textDimen = new Vector2(100,cameraheight - 100);
			textBoxDimen = new Vector2(camerawidth/2,cameraheight/2);
			
			if(layer.getStyle() != null){
			
			textBoxDimen.x = layer.getStyle().getWidth();
			textBoxDimen.y = layer.getStyle().getHeight();

			if ("left".equals(layer.getAlignment())) {

				
				textDimen.x = camerawidth/layer.getStyle().getLeft_ratio();
				textDimen.y = cameraheight/layer.getStyle().getTop_ratio();
				//textDimen.x = camerawidth * .65f;
				//textDimen.y = cameraheight;

			} else if ("right".equals(layer.getAlignment())) {
				
				textDimen.x = camerawidth/layer.getStyle().getLeft_ratio();
				textDimen.y = cameraheight/layer.getStyle().getTop_ratio();

				textDimen.x = camerawidth * .65f;
				textDimen.y = cameraheight;
			} else if ("top".equals(layer.getAlignment())) {
				textDimen.x = camerawidth/layer.getStyle().getLeft_ratio();
				textDimen.y = cameraheight/layer.getStyle().getTop_ratio();

				textDimen.x = camerawidth ;
				textDimen.y = cameraheight * .65f;
			} else if ("bottom".equals(layer.getAlignment())) {
				
				textDimen.x = camerawidth/layer.getStyle().getLeft_ratio();
				textDimen.y = cameraheight/layer.getStyle().getTop_ratio();

				textDimen.x = camerawidth;
				textDimen.y = cameraheight * .65f;
			} else {

				
				textDimen.x = camerawidth;
				textDimen.y = cameraheight;
			}
			
			}
			}*/

		//}
			mTextLayer = layer;
		//Pass json
		textListener.onPageTextChange(storyText,layer);

	}

	private void buildImage(Layer layer) {
		if (layer.getUrl() != null) {
			picture = AssetManager.getInstance().loadImage(layer.getUrl());
			Log.e("Picture",""+AssetManager.getInstance().loadImage(layer.getUrl()));
		}else {
			Log.e("picture is null", "");

		}

		if ("left".equals(layer.getAlignment())) {
			alignment = new Alignment(camerawidth, cameraheight, Alignment.LEFT);
		} else if ("right".equals(layer.getAlignment())) {
			alignment = new Alignment(camerawidth, cameraheight, Alignment.RIGHT);
		} else if ("top".equals(layer.getAlignment())) {
			alignment = new Alignment(camerawidth, cameraheight, Alignment.TOP);
		} else if ("bottom".equals(layer.getAlignment())) {
			alignment = new Alignment(camerawidth, cameraheight, Alignment.BOTTOM);
		} else {
			alignment = new Alignment(camerawidth, cameraheight, Alignment.CENTER);
		}

		imagePosition = alignment.getImageAlignment();
		imageDimen = alignment.getImageDimVector();

	}

	// Game game;
	Rectangle bookcover;
	Rectangle pagenext, pageprevious;
	float cameraheight = 768f;
	float camerawidth = 1024f;
	OrthographicCamera guiCam;
	SpriteBatch batcher;
	Vector3 touchPoint;
	boolean isTreeTouched;
	boolean isFreeStoryTouched;
	boolean isMyStoryTouched;
	boolean isStoreTouched;
	public static BitmapFont font;

	private ScreenAdapter screenAdapter;
	private float coloredFontW, coloredFontH;
	private float coloredFontPosition;
	private ShapeRenderer shapes;
	private Alignment alignment;
	
	private StoryStateListener textListener;

	public StoryPageScreen(ScreenAdapter screenAdapter, Page model, StoryStateListener textListener) {
		this.screenAdapter = screenAdapter;
		this.pageModel = model;
		this.textListener = textListener;
		
		Layer[] layers = model.getLayers();

		
		//Build Image layer first
		
		for (Layer layer : layers) {
			if ("image".equals(layer.getType())) {
				buildImage(layer);
				break;
			} 
		}
		
		for (Layer layer : layers) {
			if ("image".equals(layer.getType())) {
				continue;
			} 
			buildLayers(layer);
		}

		guiCam = new OrthographicCamera(camerawidth, cameraheight);
		guiCam.position.set(camerawidth / 2, cameraheight / 2, 0);
		batcher = new SpriteBatch();

		menucircle = new Rectangle(cameraheight,10 , Assets.menucirlcle.getWidth(), Assets.menucirlcle.getHeight());
		logo = new Circle(new Vector2(15, 670),Assets.logo.getWidth()/2);

		menuclose = new Circle(new Vector2(890,650),Assets.menuclose.getWidth()/2);
		playbutton = new Circle(new Vector2(840,550),Assets.playbutton.getWidth()/2);
		pausebutton = new Circle(new Vector2(830,530),Assets.pausebutton.getWidth()/2);
		game = new Circle(new Vector2(805,420),Assets.games.getWidth()/2);
		icon_text = new Circle(new Vector2(805,300),Assets.icon_text.getWidth()/2);
		editimage = new Circle(new Vector2(825,180),Assets.edit_image.getWidth()/2);
		icon_replay = new Circle(new Vector2(880,80),Assets.icon_reply.getWidth()/2);
		
		shapes = new ShapeRenderer(); 

		menulogo = new Circle(new Vector2(890, 650),Assets.menulogo.getWidth()/2);
		pagenext = new Rectangle(928, 1, 97, 100);
		pageprevious = new Rectangle(0, 0, 97, 100);
		touchPoint = new Vector3();
		
		
		
		/*FileHandle fontFile = Gdx.files.internal("data/Nagarik Italic.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);

        font = generator.generateFont(50);*/
		font = new BitmapFont(Gdx.files.internal("fonts/h.fnt"),
                Gdx.files.internal("fonts/h.png"), false);
		
        font.getRegion().getTexture().setFilter(TextureFilter.Linear,TextureFilter.Linear);
     ///   generator.dispose();
		/*font = new BitmapFont();*/
		
		Gdx.input.setInputProcessor(this);
		//Gdx.input.setCatchBackKey(true);


	}

	public void reload(){
		if (sound!=null) {
			sound.play();		
		}
		textListener.onPageTextChange(storyText,mTextLayer);
	}


	private void draw(float delta) {
		//System.out.println("draw");

		Log.e("ondraw", "");
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);
		// batcher.disableBlending();
		if((picture !=null)){

			//Log.e("x", ""+imagePosition.x);
			//Log.e("y", ""+imagePosition.y);
			//Log.e("im dim x", ""+imageDimen.x);
			//Log.e("im dim y", ""+imageDimen.y);

			batcher.draw(picture, imagePosition.x,imagePosition.y,
					imageDimen.x, imageDimen.y);


		}

		if (menuclick == true) {

			batcher.draw(Assets.edit_image,editimage.x, editimage.y);
			batcher.draw(Assets.menucirlcle,menucircle.x, menucircle.y);
			batcher.draw(Assets.menuclose, menuclose.x,menuclose.y );
			batcher.draw(Assets.games, game.x,game.y );
			batcher.draw(Assets.icon_text,icon_text.x,icon_text.y);
			batcher.draw(Assets.icon_reply,icon_replay.x, icon_replay.y);	
			batcher.draw(Assets.edit_image,editimage.x, editimage.y);

			if (sound!=null && sound.isPlaying()) {
				batcher.draw(Assets.pausebutton, playbutton.x, playbutton.y );
				sound_id =0;

			}else {
				batcher.draw(Assets.playbutton, playbutton.x, playbutton.y );
				sound_id = 1;

			}
		}else {
			batcher.draw(Assets.menulogo,menulogo.x,menulogo.y);		
		}
		batcher.draw(Assets.logo, logo.x, logo.y);



		batcher.draw(Assets.pagenext, 928, 1, pagenext.getWidth(),
				pagenext.getHeight());

		//drawStoryText();

		 /*shapes.begin(ShapeType.Filled);
		 shapes.setColor(Color.BLUE);
		    shapes.rect(coloredFontW, 100, coloredFontW, coloredFontH);
		    shapes.end();*/


		/*if (storyText!=null) {
			//font.draw(batcher, storyText, Utils.textpositionleft().x,
			//	Utils.textpositionleft().y);
			//	batcher.setShader(Highlight.SHADER);
			font.setColor(Color.BLACK);
			font.setScale((float) (2.0));		
			font.drawMultiLine(batcher, insertNewlineChars(storyText,textBoxDimen.x,font), textDimen.x,textDimen.yUtils.textpositionleft().x, Utils.textpositionleft().y);
			//batcher.setShader(null);
		}
*/
		
		batcher.draw(Assets.pageprevious, 0, 1,
				pageprevious.getWidth(), pageprevious.getHeight());
	}

	String insertNewlineChars(String textToDisplay, Float maxLabelWidth, BitmapFont font)
	{

		float textWidth=0;
		ArrayList<String> words = new ArrayList<String>(Arrays.asList(textToDisplay.split(" ")));
		String addWordsToSentence;

		//add first word
		String nextWord = words.get(0) ;
		addWordsToSentence = nextWord + " ";
		textWidth = font.getBounds(addWordsToSentence).width;

		//add 2nd to last word
		for(int i=1;i<words.size();i++)
		{
			nextWord = words.get(i);
			textWidth += font.getBounds(nextWord).width;

			//add word to a new line
			if(textWidth >  maxLabelWidth)
			{
				//push to next line
				textWidth = font.getBounds(nextWord).width;
				addWordsToSentence = addWordsToSentence.concat("\n" + nextWord + " ");
			}

			//add word to the same line
			else
			{
				addWordsToSentence = addWordsToSentence.concat(nextWord + " ");
			}
			
			coloredFontW = font.getBounds(nextWord).width;
			coloredFontH = font.getBounds(nextWord).height;
			coloredFontPosition = textWidth;

		}
		return addWordsToSentence;
	}

	/*private void drawStoryText() {
		if(storyText != null){

			font.setScale((float) (2.0));		

			font.setColor(Color.BLUE);
			font.draw(batcher, storyText, textDimen.x,
					textDimen.y);

		}
	}*/

	private void update(float delta) {
		Log.e("update", "");
		//System.out.println("update");
/*
		if (sound != null && !sound.isPlaying()) {
			sound.stop();
			screenAdapter.setAutoNextScreen();

		}
*/
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(),
					0));

			if (OverlapTester.nextpage(pagenext, touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);

                    

				if (sound != null && sound.isPlaying()) {

					sound.pause();
				}
				screenAdapter.setNextScreen();
				

				return;
			}


			if (OverlapTester.pointInHomeLogo(logo,new Vector2(touchPoint.x, touchPoint.y))) {

				screenAdapter.onHomeClick();
				
				/* Game game = new Game() {
					
					@Override
					public void create() {
						// TODO Auto-generated method stub
						System.out.println("game");
					}
				};*/
				
               
				System.out.println("click");

				return ;
			}


			if (OverlapTester.pointtoplay(playbutton,new Vector2(touchPoint.x, touchPoint.y))) {

                 
				if (sound != null /*&& sound.isPlaying()*/) {

					if (sound.isPlaying()&&sound_id==0) {
						sound.pause();	
						
					}else {
						sound.play();
					}

				}else {
					
					System.out.println("sound null");
				}
				/*  Game game = null;
				game.setScreen(new HomePage(game));*/

				System.out.println("click");

				return ;
			}

			if (OverlapTester.pointtomenu(menulogo,new Vector2(touchPoint.x, touchPoint.y))) {

				if (menuclick == false) {


					menuclick = true;
				}else {
					menuclick = false;
				}
				System.out.println("menu click");

				return ;
			}



			if (OverlapTester.previouspage(pageprevious, touchPoint.x,
					touchPoint.y)) {
				Assets.playSound(Assets.clickSound);

				if (sound != null && sound.isPlaying()) {

					sound.stop();
				}

				screenAdapter.setPrevScreen();


				/*
				 * System.out.println(touchPoint.x);
				 * System.out.println(touchPoint.y);
				 */
				return;
			}
		}

	}

	@Override
	public void render(float delta) {
		//	System.out.println("render");
		Log.e("render", "");
		batcher.begin();
		draw(delta);
		batcher.end();
		update(delta);
	}

	@Override
	public void resize(int width, int height) {
		Log.e("resize", "");
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Log.e(" show()()", "");
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		Log.e(" hide()()", "");
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		Log.e(" pause()()", "");
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

		textListener.onPageTextChange(storyText,mTextLayer);
		Log.e(" resume()", "");
	}

	@Override
	public void dispose() {
		Log.e(" dispose()()", "");
		// TODO Auto-generated method stub

	}

	@Override
	public boolean keyDown(int keycode) {
		/*if(keycode == Keys.BACK){
			// Do your optional back button handling (show pause menu?)
		}*/
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
