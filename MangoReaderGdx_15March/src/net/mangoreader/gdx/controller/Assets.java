/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package net.mangoreader.gdx.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static Texture pausebt,menulogo;
	public  static Texture playbutton, pausebutton;
	public static Texture menucirlcle,menuclose,games,icon_text,edit_image,icon_reply;
	public static Texture background ,homebg;
	public static TextureRegion backgroundRegion,homebgregion;
	public static Texture pagenext, pageprevious;
	public static Texture /*items,*/readtome,readbuyself,playgames,free_story,my_story,store;
	public static Texture mainMenu/*,detailimage*/;
	public static TextureRegion pauseMenu;
	public static TextureRegion ready;
	public static Texture logo,storycreate;
	public static Sprite  createstory_treesprite,freestory_sprite,store_sprite,mystory_sprite;
	public static Texture imageviewrect;

	public static TextureRegion soundOn;
	public static TextureRegion soundOff;
	public static TextureRegion arrow;
	public static TextureRegion pause;
	public static BitmapFont font;

	public static Music music;
	public static Sound clickSound;

	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}

	public static void load () {
		// second screen
		background = loadTexture("data/reader_bookcover_bg.png");
		backgroundRegion = new TextureRegion(background, 0, 0, 1024,768);
		menulogo = loadTexture("data/icons_menu.png");
		edit_image = loadTexture("data/icons_editimage.png");
		icon_reply = loadTexture("data/icons_replay.png");
		icon_text = loadTexture("data/icons_text.png");
		games = loadTexture("data/icons_game.png");
	    menuclose = loadTexture("data/icons_close.png");
		pagenext  = loadTexture("data/icons_next.png");
		pageprevious =  loadTexture("data/icons_back.png");
        playbutton = loadTexture("data/icons_play.png");
        pausebutton = loadTexture("data/icons_pause.png");
        menucirlcle = loadTexture("data/menucircle.png");
		
		
		//items = loadTexture("data/items.png");
		logo =     loadTexture("data/icons_home.png");
		mainMenu = loadTexture("data/icons_home.png");
		imageviewrect = loadTexture("data/rectangle_books.png");//TODO make this dynamic

		readtome = loadTexture("data/icons_readtome.png");
		readbuyself = loadTexture("data/icons_readbymyself.png");
	/*	detailimage = loadTexture("data/detail.jpg");*/
				
		
		playgames = loadTexture("data/icons_playgames.png");
		
		homebg = loadTexture("data/homebg.png");
		homebgregion = new TextureRegion(homebg, 0, 0, 1024,768);
		storycreate = loadTexture("data/button_createstory.png");
		createstory_treesprite=new Sprite(storycreate);
		free_story = loadTexture("data/button_freestories.png");
		freestory_sprite = new Sprite(free_story);
		my_story = loadTexture("data/button_mystories.png");
		mystory_sprite = new Sprite(my_story);
		store = loadTexture("data/button_store.png");
		store_sprite = new Sprite(store);
		
		pagenext  = loadTexture("data/icons_next.png");
		pageprevious =  loadTexture("data/icons_back.png");
        

		clickSound = Gdx.audio.newSound(Gdx.files.internal("data/click.wav"));
	}

	public static void playSound (Sound sound) {
		//if (Settings.soundEnabled) sound.play(1);
	}
}
