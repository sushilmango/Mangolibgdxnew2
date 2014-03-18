
package net.mangoreader.gdx.controller;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class ApplicationAssets {
	public  Texture menulogo ,menucirlcle,menuclose,games,icon_text,edit_image,icon_reply;
	public Texture backlogo, nextlogo;
	public Texture ActionAdventure, AnimalsandNature, BedtimeStories,
			ClassicStories, ComicsandGraphic, FamilyandFriends,
			GeneralandMiscellane, MoralsandValues, Humour,
			HolidaysandCelebrations, MysteryandSuspense, PictureBooks,
			PoemsandSongs, SchoolTime, ScienceandNature, TraditionalTales,
			FinancialLiteracy;
	public Texture pagenext, pageprevious;

	public Texture playbutton, pausebutton;
	public Texture items;

	private static ApplicationAssets Instance;
	private static boolean clearFlag;
	
	private ApplicationAssets(){
	}
	
	public static ApplicationAssets getInstance(){
		
		if(Instance == null){
			Instance = new ApplicationAssets();
		}
		
		if(!clearFlag){
			Instance.load();
			clearFlag = ! clearFlag;
		}
		return Instance;
	}
	
	
	public void clear(){
		clearFlag = !clearFlag;
	}
  
	public  Texture loadTexture (String file) {
		
		System.out.println("Gdx.files.getLocalStoragePath() "+Gdx.files.internal(file));
		
		File file1 = new File(Gdx.files.getExternalStoragePath()+"/sushil");
		
		if (file1.exists()) {
			
		}else {
			file1.mkdir();	
		}
		
		
		return new Texture(Gdx.files.internal(file));
		
		
	}
	
	private void load () {
	
		items = loadTexture("data/categoryscreen.png");
	
		ActionAdventure = loadTexture("data/icon_action_adventure.png");
		AnimalsandNature = loadTexture("data/icon_animals_nature.png");
		BedtimeStories = loadTexture("data/icon_bedtimestories.png");
		ClassicStories = loadTexture("data/icon_classics.png");
		ComicsandGraphic = loadTexture("data/icon_myexistingbooks.png");
		FamilyandFriends = loadTexture("data/icon_myexistingbooks.png");
		GeneralandMiscellane = loadTexture("data/icon_myexistingbooks.png");
		MoralsandValues =  loadTexture("data/icon_myexistingbooks.png");
		 Humour = loadTexture("data/icon_myexistingbooks.png");
    	HolidaysandCelebrations = loadTexture("data/icon_hoidays_celebrations.png");
		MysteryandSuspense = loadTexture("data/icon_myexistingbooks.png");
		PictureBooks = loadTexture("data/icon_myexistingbooks.png");
		PoemsandSongs = loadTexture("data/icon_poems_rhymes.png");
		SchoolTime = loadTexture("data/icon_myexistingbooks.png");
		ScienceandNature = loadTexture("data/icon_myexistingbooks.png");
		TraditionalTales = loadTexture("data/icon_traditional.png");
		FinancialLiteracy = loadTexture("data/icon_myexistingbooks.png");
		
		
		
		nextlogo = loadTexture("data/icons_nextpressed.png");
		backlogo = loadTexture("data/icons_backpressed.png");
		edit_image = loadTexture("data/icons_editimage.png");
		icon_reply = loadTexture("data/icons_replay.png");
		icon_text = loadTexture("data/icons_text.png");
		games = loadTexture("data/icons_game.png");
	    menuclose = loadTexture("data/icons_close.png");
		pagenext  = loadTexture("data/icons_next.png");
		pageprevious =  loadTexture("data/icons_back.png");
        playbutton = loadTexture("data/icons_play.png");
        pausebutton = loadTexture("data/icons_pause.png");
        menulogo = loadTexture("data/icons_menu.png");
        menucirlcle = loadTexture("data/menucircle.png");
        		

	}

	public  void playSound (Sound sound) {
		if (Settings.soundEnabled) sound.play(1);
	}
}
