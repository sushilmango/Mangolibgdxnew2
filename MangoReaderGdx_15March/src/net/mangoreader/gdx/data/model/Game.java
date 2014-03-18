package net.mangoreader.gdx.data.model;

import org.json.JSONArray;
import org.json.JSONObject;

public class Game {

	private String name;
	private String id;
	private String game_cover;
	
	public String getGame_cover() {
		return game_cover;
	}
	public void setGame_cover(String game_cover) {
		this.game_cover = game_cover;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	private Layer[] layers;
	
	
	public Layer[] getLayers() {
		return layers;
	}
	public void setLayers(Layer[] layers) {
		this.layers = layers;
	}
	
	private JSONArray layerJSON;

	public JSONArray getLayerJSON() {
		return layerJSON;
	}

	public void setLayerJSON(JSONArray layerJSON) {
		this.layerJSON = layerJSON;
	}
	
	
}
