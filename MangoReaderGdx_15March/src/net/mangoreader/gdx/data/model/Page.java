package net.mangoreader.gdx.data.model;

import java.util.ArrayList;

public class Page {

	private String created_at;
	private String deleted_at;
	private boolean is_avail;
	private String name;
	private int pageNo;
	private String pageable_id;
	private String pageable_type;
	private String type;
	
	private String updated_at;
	private String id;
	
	private String child;
	
	private String game_cover;
	
	private String parent;
	
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getGame_cover() {
		return game_cover;
	}
	public void setGame_cover(String game_cover) {
		this.game_cover = game_cover;
	}
	public String getChild() {
		return child;
	}
	public void setChild(String child) {
		this.child = child;
	}
	private Layer[] layers;
	
	
	public Layer[] getLayers() {
		return layers;
	}
	public void setLayers(Layer[] layers) {
		this.layers = layers;
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
	public boolean isIs_avail() {
		return is_avail;
	}
	public void setIs_avail(boolean is_avail) {
		this.is_avail = is_avail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
/*	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}*/
	public String getPageable_id() {
		return pageable_id;
	}
	public void setPageable_id(String pageable_id) {
		this.pageable_id = pageable_id;
	}
	public String getPageable_type() {
		return pageable_type;
	}
	public void setPageable_type(String pageable_type) {
		this.pageable_type = pageable_type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
