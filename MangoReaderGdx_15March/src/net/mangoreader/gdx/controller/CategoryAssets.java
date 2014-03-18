package net.mangoreader.gdx.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class CategoryAssets {

	
	public static Texture items;



	public static Texture loadTexture (String file) {
		System.out.println("hello Assets");
		return new Texture(Gdx.files.internal(file));
	}

	public static void load () {
		
		items = loadTexture("data/categoryscreen.png");

	}

}
