package net.mangoreader.gdx.screens;

import net.mangoreader.gdx.LibGdxMainScreen;
import net.mangoreader.gdx.controller.ApplicationAssets;
import net.mangoreader.gdx.controller.Assets;
import net.mangoreader.gdx.controller.CategoryAssets;
import net.mangoreader.gdx.view.HomePage;
import net.mangoreader.gdx.view.OverlapTester;
import android.os.Bundle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
/*import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;*/
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Categoryscreen implements Screen ,InputProcessor {


	Game game;
	Rectangle  ActionAdventure,AnimalsandNature,
	BedtimeStories,ClassicStories,ComicsandGraphic,FamilyandFriends,GeneralandMiscellane,
	MoralsandValues,Humour,HolidaysandCelebrations,MysteryandSuspense,PictureBooks,PoemsandSongs,
	SchoolTime,ScienceandNature,TraditionalTales,FinancialLiteracy;
	Rectangle bookcover;
	Rectangle pagenext,pageprevious;
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

	static int  pageno =0;
	Circle logo;

	public Categoryscreen( Game game) {
		this.game = game;

		guiCam = new OrthographicCamera(camerawidth, cameraheight);
		guiCam.position.set(camerawidth / 2, cameraheight / 2, 0);
		batcher = new SpriteBatch();
		font = new BitmapFont();
		
		
		logo = new Circle(new Vector2(15, 670),Assets.logo.getWidth()/2);

		ActionAdventure = new Rectangle(120, 440, ApplicationAssets.getInstance().ActionAdventure.getWidth(), ApplicationAssets.getInstance().ActionAdventure.getHeight());
		BedtimeStories = new Rectangle(412, 440, ApplicationAssets.getInstance().BedtimeStories.getWidth(), ApplicationAssets.getInstance().BedtimeStories.getHeight());
		AnimalsandNature = new Rectangle(680, 440, ApplicationAssets.getInstance().AnimalsandNature.getWidth(), ApplicationAssets.getInstance().AnimalsandNature.getHeight());
		ClassicStories = new Rectangle(120, 130, ApplicationAssets.getInstance().ClassicStories.getWidth(), ApplicationAssets.getInstance().ClassicStories.getHeight());
		HolidaysandCelebrations =new Rectangle(412, 130, ApplicationAssets.getInstance().HolidaysandCelebrations.getWidth(), ApplicationAssets.getInstance().HolidaysandCelebrations.getHeight());
		PoemsandSongs = new  Rectangle(680, 130, ApplicationAssets.getInstance().PoemsandSongs.getWidth(), ApplicationAssets.getInstance().PoemsandSongs.getHeight());

		TraditionalTales = new Rectangle(120, 440, ApplicationAssets.getInstance().TraditionalTales.getWidth(), ApplicationAssets.getInstance().TraditionalTales.getHeight());
		ComicsandGraphic = new Rectangle(412, 440, ApplicationAssets.getInstance().ComicsandGraphic.getWidth(), ApplicationAssets.getInstance().ComicsandGraphic.getHeight());
		FamilyandFriends = new Rectangle(680, 440, ApplicationAssets.getInstance().FamilyandFriends.getWidth(), ApplicationAssets.getInstance().FamilyandFriends.getHeight());
		MoralsandValues =  new Rectangle(120, 130, ApplicationAssets.getInstance().MoralsandValues.getWidth(), ApplicationAssets.getInstance().MoralsandValues.getHeight());
		Humour =  new Rectangle(412, 130, ApplicationAssets.getInstance().Humour.getWidth(), ApplicationAssets.getInstance().Humour.getHeight());
		MysteryandSuspense = new Rectangle(680, 130, ApplicationAssets.getInstance().MysteryandSuspense.getWidth(), ApplicationAssets.getInstance().MysteryandSuspense.getHeight());
	
		PictureBooks = new Rectangle(120, 440, ApplicationAssets.getInstance().PictureBooks.getWidth(), ApplicationAssets.getInstance().PictureBooks.getHeight());
		
		SchoolTime = new Rectangle(412, 440, ApplicationAssets.getInstance().SchoolTime.getWidth(), ApplicationAssets.getInstance().SchoolTime.getHeight());
		ScienceandNature = new Rectangle(680, 440, ApplicationAssets.getInstance().ScienceandNature.getWidth(), ApplicationAssets.getInstance().ScienceandNature.getHeight());

		FinancialLiteracy = new Rectangle(120, 130, ApplicationAssets.getInstance().FinancialLiteracy.getWidth(), ApplicationAssets.getInstance().FinancialLiteracy.getHeight());
		GeneralandMiscellane = new Rectangle(412, 130, ApplicationAssets.getInstance().GeneralandMiscellane.getWidth(), ApplicationAssets.getInstance().GeneralandMiscellane.getHeight());
		pagenext = new Rectangle(928, cameraheight/2, 80, 140);
		//pagenext = new Rectangle(0, 0, 80, 140);
		pageprevious = new Rectangle( 0, cameraheight/2-30, 80, 140);
		
		touchPoint = new Vector3();
		
		Gdx.input.setCatchBackKey(true);

	}


	private void draw(float delta) {

		GL20 gl = Gdx.gl;
		gl.glClearColor(0, 1, 0, 0);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);
		batcher.draw(CategoryAssets.items, 0, 0, camerawidth, cameraheight);
		batcher.draw(Assets.logo, logo.x, logo.y);
		/*	batcher.draw(ApplicationAssets.getInstance().nextlogo, 940, cameraheight/2-30,pagenext.getWidth(),
				pagenext.getHeight());*/

		batcher.draw(ApplicationAssets.getInstance().nextlogo, 940,  cameraheight/2-30, pagenext.getWidth(),
				pagenext.getHeight());

		batcher.draw(ApplicationAssets.getInstance().backlogo, 0, cameraheight/2-30,
				pageprevious.getWidth(),pageprevious.getHeight());
		
		font.setScale((float) (2.0));	


		font.setColor(Color.WHITE );
        System.out.println("changeeeeeeeeeee");

		font.draw(batcher, "My Story", 420,
				730);

		if (pageno==0) {

			batcher.draw(ApplicationAssets.getInstance().ActionAdventure,120,440,ApplicationAssets.getInstance().ActionAdventure.getWidth(),ApplicationAssets.getInstance().ActionAdventure.getHeight());
			font.draw(batcher, "Action Adventure", 120,420);
			batcher.draw(ApplicationAssets.getInstance().BedtimeStories,412,440,ApplicationAssets.getInstance().BedtimeStories.getWidth(),ApplicationAssets.getInstance().BedtimeStories.getHeight());
			font.draw(batcher, "Bedtime Stories", 410,420);
			batcher.draw(ApplicationAssets.getInstance().AnimalsandNature,680,440,ApplicationAssets.getInstance().AnimalsandNature.getWidth(),ApplicationAssets.getInstance().AnimalsandNature.getHeight());
			font.draw(batcher, "Animals & Nature", 680,420);
			batcher.draw(ApplicationAssets.getInstance().ClassicStories,120,130,ApplicationAssets.getInstance().ClassicStories.getWidth(),ApplicationAssets.getInstance().ClassicStories.getHeight());
			font.draw(batcher, "Classic Stories", 120,110);
			batcher.draw(ApplicationAssets.getInstance().HolidaysandCelebrations,412,130,ApplicationAssets.getInstance().HolidaysandCelebrations.getWidth(),ApplicationAssets.getInstance().HolidaysandCelebrations.getHeight());
			font.draw(batcher, "Holidays", 440,110);
			batcher.draw(ApplicationAssets.getInstance().PoemsandSongs,680,130,ApplicationAssets.getInstance().PoemsandSongs.getWidth(),ApplicationAssets.getInstance().PoemsandSongs.getHeight());
			font.draw(batcher, "Poems", 720,110);
		   
		}if (pageno==1) {

			batcher.draw(ApplicationAssets.getInstance().TraditionalTales,120,440,ApplicationAssets.getInstance().TraditionalTales.getWidth(),ApplicationAssets.getInstance().TraditionalTales.getHeight());
			font.draw(batcher, "TraditionalTales", 120,420);
			batcher.draw(ApplicationAssets.getInstance().ComicsandGraphic,412,440,ApplicationAssets.getInstance().ClassicStories.getWidth(),ApplicationAssets.getInstance().ComicsandGraphic.getHeight());
			font.draw(batcher, "Comics..", 440,420);
			batcher.draw(ApplicationAssets.getInstance().FamilyandFriends,680,440,ApplicationAssets.getInstance().FamilyandFriends.getWidth(),ApplicationAssets.getInstance().FamilyandFriends.getHeight());
			font.draw(batcher, "Family &Friends", 680,420);
			batcher.draw(ApplicationAssets.getInstance().MoralsandValues,120,130,ApplicationAssets.getInstance().MoralsandValues.getWidth(),ApplicationAssets.getInstance().MoralsandValues.getHeight());
			font.draw(batcher, "Morals & Values", 120,110);
			batcher.draw(ApplicationAssets.getInstance().Humour,412,130,ApplicationAssets.getInstance().Humour.getWidth(),ApplicationAssets.getInstance().Humour.getHeight());
			font.draw(batcher, "Humour", 440,110);
			batcher.draw(ApplicationAssets.getInstance().MysteryandSuspense,680,130,ApplicationAssets.getInstance().MysteryandSuspense.getWidth(),ApplicationAssets.getInstance().MysteryandSuspense.getHeight());
			font.draw(batcher, "Mystery...", 720,110);
		}if (pageno ==2) {
			batcher.draw(ApplicationAssets.getInstance().PictureBooks,120,440,ApplicationAssets.getInstance().PictureBooks.getWidth(),ApplicationAssets.getInstance().PictureBooks.getHeight());
			font.draw(batcher, "Picture Books", 120,420);
			batcher.draw(ApplicationAssets.getInstance().SchoolTime,412,440,ApplicationAssets.getInstance().SchoolTime.getWidth(),ApplicationAssets.getInstance().SchoolTime.getHeight());
			font.draw(batcher, "School Time", 430,420);
			batcher.draw(ApplicationAssets.getInstance().ScienceandNature,680,440,ApplicationAssets.getInstance().ScienceandNature.getWidth(),ApplicationAssets.getInstance().ScienceandNature.getHeight());
			font.draw(batcher, "Science &Nature", 680,420);
			batcher.draw(ApplicationAssets.getInstance().FinancialLiteracy,120,130,ApplicationAssets.getInstance().FinancialLiteracy.getWidth(),ApplicationAssets.getInstance().FinancialLiteracy.getHeight());
			font.draw(batcher, "Financial Literacy",  120,110);
			batcher.draw(ApplicationAssets.getInstance().GeneralandMiscellane,412,130,ApplicationAssets.getInstance().GeneralandMiscellane.getWidth(),ApplicationAssets.getInstance().GeneralandMiscellane.getHeight());
			font.draw(batcher, "General...",  440,110);
		}


		font.setScale((float) (2.0));	


		font.setColor(Color.WHITE );


		font.draw(batcher, "My Story", 420,
				730);
		Gdx.input.setInputProcessor(this);
	}

	private void update(float delta) {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			System.out.println(touchPoint.x);
			System.out.println(touchPoint.y);
			

			if (OverlapTester.pointInHomeLogo(logo,new Vector2(touchPoint.x, touchPoint.y))) {
				game.setScreen(new HomePage(game));

				System.out.println("click");

				return ;
			}

			if (pageno==0) {


				if (OverlapTester.bedtime(ActionAdventure, touchPoint.x,touchPoint.y)) {

					Bundle bundle = new Bundle();
					bundle.putString("Activity", ScreenEnum.SCREEN_4.getValue());
					bundle.putString("Category", "Action and Adventure");
					((LibGdxMainScreen)game).startScreen( bundle );
					
					//((StarAssault)game).startScreen(ScreenEnum.SCREEN_2.getValue());
					// game.setScreen(new BookRead(game));

					System.out.println("ActionAdventure");
					return;
				}
				if (OverlapTester.bedtime(BedtimeStories, touchPoint.x,touchPoint.y)) {
					
					Bundle bundle = new Bundle();
					bundle.putString("Activity", ScreenEnum.SCREEN_4.getValue());
					bundle.putString("Category", "Bedtime Stories");
					((LibGdxMainScreen)game).startScreen( bundle );

					System.out.println("BedtimeStories");
					return;
				}
				if (OverlapTester.bedtime(AnimalsandNature, touchPoint.x,touchPoint.y)) {

					Bundle bundle = new Bundle();
					bundle.putString("Activity", ScreenEnum.SCREEN_4.getValue());
					bundle.putString("Category", "Animals and Nature");
					((LibGdxMainScreen)game).startScreen( bundle );
					System.out.println("AnimalsandNature");
					return;
				}
				if (OverlapTester.bedtime(ClassicStories, touchPoint.x,touchPoint.y)) {
					
					Bundle bundle = new Bundle();
					bundle.putString("Activity", ScreenEnum.SCREEN_4.getValue());
					bundle.putString("Category", "Classic Stories");
					((LibGdxMainScreen)game).startScreen( bundle );

					System.out.println("ClassicStories");
					return;
				}
				if (OverlapTester.bedtime(HolidaysandCelebrations, touchPoint.x,touchPoint.y)) {

					Bundle bundle = new Bundle();
					bundle.putString("Activity", ScreenEnum.SCREEN_4.getValue());
					bundle.putString("Category", "Holidays and Celebration");
					((LibGdxMainScreen)game).startScreen( bundle );
					System.out.println("HolidaysandCelebrations");
					return;
				}
				if (OverlapTester.bedtime(PoemsandSongs, touchPoint.x,touchPoint.y)) {

					Bundle bundle = new Bundle();
					bundle.putString("Activity", ScreenEnum.SCREEN_4.getValue());
					bundle.putString("Category", "Poems and Songs");
					((LibGdxMainScreen)game).startScreen( bundle );
					System.out.println("PoemsandSongs");
					return;
				}
			}if (pageno==1) {

				if (OverlapTester.bedtime(TraditionalTales, touchPoint.x,touchPoint.y)) {

					Bundle bundle = new Bundle();
					bundle.putString("Activity", ScreenEnum.SCREEN_4.getValue());
					bundle.putString("Category", "Traditional Tales");
					((LibGdxMainScreen)game).startScreen( bundle );
					System.out.println("TraditionalTales");
					return;
				}
				if (OverlapTester.bedtime(ComicsandGraphic, touchPoint.x,touchPoint.y)) {

					Bundle bundle = new Bundle();
					bundle.putString("Activity", ScreenEnum.SCREEN_4.getValue());
					bundle.putString("Category", "Comics and Graphic Novels");
					((LibGdxMainScreen)game).startScreen( bundle );
					System.out.println("ComicsandGraphic");
					return;
				}
				if (OverlapTester.bedtime(FamilyandFriends, touchPoint.x,touchPoint.y)) {

					Bundle bundle = new Bundle();
					bundle.putString("Activity", ScreenEnum.SCREEN_4.getValue());
					bundle.putString("Category", "Family and Friends");
					((LibGdxMainScreen)game).startScreen( bundle );
					System.out.println("FamilyandFriends");
					return;
				}
				if (OverlapTester.bedtime(MoralsandValues, touchPoint.x,touchPoint.y)) {

					Bundle bundle = new Bundle();
					bundle.putString("Activity", ScreenEnum.SCREEN_4.getValue());
					bundle.putString("Category", "Morals and Values");
					((LibGdxMainScreen)game).startScreen( bundle );
					System.out.println("MoralsandValues");
					return;
				}
				if (OverlapTester.bedtime(Humour, touchPoint.x,touchPoint.y)) {

					Bundle bundle = new Bundle();
					bundle.putString("Activity", ScreenEnum.SCREEN_4.getValue());
					bundle.putString("Category", "Humour");
					((LibGdxMainScreen)game).startScreen( bundle );
					System.out.println("Humour");
					return;
				}
				if (OverlapTester.bedtime(MysteryandSuspense, touchPoint.x,touchPoint.y)) {
					Bundle bundle = new Bundle();
					bundle.putString("Activity", ScreenEnum.SCREEN_4.getValue());
					bundle.putString("Category", "Mystery and Suspense");
					((LibGdxMainScreen)game).startScreen( bundle );
					System.out.println("MysteryandSuspense");
					return;
				}
			}if (pageno==2) {

				if (OverlapTester.bedtime(PictureBooks, touchPoint.x,touchPoint.y)) {

					Bundle bundle = new Bundle();
					bundle.putString("Activity", ScreenEnum.SCREEN_4.getValue());
					bundle.putString("Category", "Picture Books");
					((LibGdxMainScreen)game).startScreen( bundle );
					System.out.println("PictureBooks");
					return;
				}
				if (OverlapTester.bedtime(SchoolTime, touchPoint.x,touchPoint.y)) {
					Bundle bundle = new Bundle();
					bundle.putString("Activity", ScreenEnum.SCREEN_4.getValue());
					bundle.putString("Category", "School Time");
					((LibGdxMainScreen)game).startScreen( bundle );
					System.out.println("SchoolTime");
					return;
				}
				if (OverlapTester.bedtime(ScienceandNature, touchPoint.x,touchPoint.y)) {
					Bundle bundle = new Bundle();
					bundle.putString("Activity", ScreenEnum.SCREEN_4.getValue());
					bundle.putString("Category", "Science and Nature");
					((LibGdxMainScreen)game).startScreen( bundle );
					System.out.println("ScienceandNature");
					return;
				}
				if (OverlapTester.bedtime(FinancialLiteracy, touchPoint.x,touchPoint.y)) {

					Bundle bundle = new Bundle();
					bundle.putString("Activity", ScreenEnum.SCREEN_4.getValue());
					bundle.putString("Category", "Financial Literacy");
					((LibGdxMainScreen)game).startScreen( bundle );
					System.out.println("FinancialLiteracy");
					return;
				}
				
				if (OverlapTester.bedtime(GeneralandMiscellane, touchPoint.x,touchPoint.y)) {

					Bundle bundle = new Bundle();
					bundle.putString("Activity", ScreenEnum.SCREEN_4.getValue());
					bundle.putString("Category", "General and Miscellaneous");
					((LibGdxMainScreen)game).startScreen( bundle );
					System.out.println("FinancialLiteracy");
					return;
				}
				
			}

			if (Gdx.input.justTouched()) {
				guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(),
						0));

				if (OverlapTester.nextpage(pagenext, touchPoint.x, touchPoint.y)) {
					Assets.playSound(Assets.clickSound);

					System.out.println("new"+pageno);
					System.out.println("next");
					if (pageno>=0&&pageno<2) {
						pageno ++;	
						System.out.println(""+pageno);
					}


					return;
				}


				if (OverlapTester.previouspageno(pageprevious, touchPoint.x, touchPoint.y)) {
					Assets.playSound(Assets.clickSound);

					System.out.println("page previous");
					System.out.println(touchPoint.x);
					System.out.println(touchPoint.y);
					if (pageno>0&&pageno<=2) {
						pageno --;	
					}


					return;

				}
			}}




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
