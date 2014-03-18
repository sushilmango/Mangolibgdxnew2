package net.mangoreader.gdx.data.model;



public class Layer {

	private String alignment;
	private String audio_id;
	private String created_at;
	private String html;
	private String name;
	private String order;
	private String text =""; 
	private String type;
	private String updated_at;
	private String url;
	private String id;
	private Style style;
	private String game_cover;
	private String image_alignment;
	private String story_path;
	private String audio;
	private String text_id;
	private WordMap[] wordMap;
	
	private String[] wordTimes;
	
	private String [] images;
	
	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public String[] getWordTimes() {
		return wordTimes;
	}

	public void setWordTimes(String[] wordTimes) {
		this.wordTimes = wordTimes;
	}

	public WordMap[] getWordMap() {
		return wordMap;
	}

	public void setWordMap(WordMap[] wordMap) {
		this.wordMap = wordMap;
	}

	public String getText_id() {
		return text_id;
	}

	public void setText_id(String text_id) {
		this.text_id = text_id;
	}

	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	public String getStory_path() {
		return story_path;
	}

	public void setStory_path(String story_path) {
		this.story_path = story_path;
	}

	private String page_name;
	
	
	public String getPage_name() {
		return page_name;
	}

	public void setPage_name(String page_name) {
		this.page_name = page_name;
	}

	public String getImage_alignment() {
		return image_alignment;
	}

	public void setImage_alignment(String image_alignment) {
		this.image_alignment = image_alignment;
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

	private String child;
	
	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public String getAudio_id() {
		return audio_id;
	}

	public void setAudio_id(String audio_id) {
		this.audio_id = audio_id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public static class Style{
		private int height;
		private int width;
		private int left;
		private int top;
		private Float top_ratio;
		private Float left_ratio;
		private String font_family;
		private String color;
		private int z_index;

		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
		public int getWidth() {
			return width;
		}
		public void setWidth(int width) {
			this.width = width;
		}

		public int getLeft() {
			return left;
		}
		public void setLeft_ratio(Float left_ratio) {
			this.left_ratio = left_ratio;
		}
		public Float getLeft_ratio() {
			return left_ratio;
		}
		public void setLeft(int left) {
			this.left = left;
		}
		public int gettop() {
			return top;
		}
		public void settop(int top) {
			this.top = top;
		}

		
		public Float getTop_ratio() {
			return top_ratio;
		}public void setTop_ratio(Float top_ratio) {
			this.top_ratio = top_ratio;
		}

		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public String getFont_family() {
			return font_family;
		}
		public void setFont_family(String font_family) {
			this.font_family = font_family;
		}
		public int getZ_index() {
			return z_index;
		}
		public void setZ_index(int z_index) {
			this.z_index = z_index;
		}
	}
}
