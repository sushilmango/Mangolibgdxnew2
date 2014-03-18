package net.mangoreader.gdx.data.model;

import java.util.ArrayList;

public class Story {
	
	private int comments_count;
	private String created_at;
	private String deleted_at;
	private int flag_count;
	private int like_count;
	private int page_count;
	private String story_id;
	private String synopsis;
	private String title;
	private String updated_at;
	private String user_id;
	private int view_count;
	private int widget_count;
	private String _id;
	
	private String _type;
	
	private String price;
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String get_type() {
		return _type;
	}
	public void set_type(String _type) {
		this._type = _type;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	private Info info;

	private ArrayList<Image> images;
	private ArrayList<Audio> audios;
	
	//Test 
	private ArrayList<Page> pages = new ArrayList<Page>();
	
	private ArrayList<Game> games = new ArrayList<Game>();
	
	
	public ArrayList<Game> getGames() {
		return games;
	}
	
	public void addGames(Game game) {
		this.games.add(game);
	}
	
	public ArrayList<Page> getPages() {
		return pages;
	}
	
	public void addPage(Page page) {
		this.pages.add(page);
	}
	public int getComments_count() {
		return comments_count;
	}
	public void setComments_count(int comments_count) {
		this.comments_count = comments_count;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getDeleted_at() {
		return deleted_at;
	}
	public void setDeleted_at(String deleted_at) {
		this.deleted_at = deleted_at;
	}
	public int getFlag_count() {
		return flag_count;
	}
	public void setFlag_count(int flag_count) {
		this.flag_count = flag_count;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	public int getPage_count() {
		return page_count;
	}
	public void setPage_count(int page_count) {
		this.page_count = page_count;
	}
	public String getStory_id() {
		return story_id;
	}
	public void setStory_id(String story_id) {
		this.story_id = story_id;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public int getWidget_count() {
		return widget_count;
	}
	public void setWidget_count(int widget_count) {
		this.widget_count = widget_count;
	}
	
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	
	public ArrayList<Image> getImages() {
		return images;
	}
	public void setImages(ArrayList<Image> image_array) {
		this.images = image_array;
	}
	public ArrayList<Audio> getAudios() {
		return audios;
	}
	public void setAudios(ArrayList<Audio> audio_array) {
		this.audios = audio_array;
	}

}
