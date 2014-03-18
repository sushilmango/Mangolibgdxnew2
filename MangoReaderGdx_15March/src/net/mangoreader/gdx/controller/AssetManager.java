package net.mangoreader.gdx.controller;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class AssetManager {

/*	public static Music music;*/
	//private  String basepath = "Android/data/com.mangoreader/files/MangoBook_52bc777369702d7522373c00/";/*52d00dd169702d01d4700a00/*/
	private  String basepath1 ="Android/data/com.mangoreader/files/MangoBook_";/*52d00dd169702d01d4700a00/*/
	private static AssetManager Instance;
	
	private String finalURL = basepath1;
	
	private AssetManager(){}
	
	public static AssetManager getInstance(){
		if(Instance == null){
			Instance = new AssetManager();
		}
		return Instance;
	}
	
	public void init(String book_id){
		//basepath = basepath +book_id+"/";
		System.out.println(book_id);
		System.out.println(basepath1);
		finalURL = basepath1 +book_id+"/";
		System.out.println("book path??"+basepath1+book_id+"/");
		
	}
	
	public Texture loadText(String url){
		System.out.println(url);
		return new Texture(Gdx.files.external(finalURL+url));
		
	}
	public Texture loadImage(String url){
		
		Log.e("LOad Images", ""+Gdx.files.external(finalURL+url));
		
		return new Texture(Gdx.files.external(finalURL+url));
		
	}
	
	public Music loadSound(String url){
		
	/*	Log.e("Sound", ""+Gdx.files.external(basepath+url));
		music = Gdx.audio.newMusic(Gdx.files.external(basepath+url));
	//	music.setLooping(true);
		music.setVolume(0.5f);
		music.play();*/
	
		return Gdx.audio.newMusic(Gdx.files.external(finalURL+url));/*Gdx.audio.newSound(Gdx.files.external(basepath+url));*/
	}
	
	public void playSound (Sound sound) {
		if (Settings.soundEnabled) sound.play(1);
	}
}
