package net.mangoreader.gdx.data.model;

public class Style {
	private int height;
	private int width;
	private int left;
	private int right;
	private int top_ratio;
	private int left_ratio;
	private int font_family;
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
	public void setLeft_ratio(int left_ratio) {
		this.left_ratio = left_ratio;
	}
	public int getLeft_ratio() {
		return left_ratio;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}

	public int getTop_ratio() {
		return top_ratio;
	}public void setTop_ratio(int top_ratio) {
		this.top_ratio = top_ratio;
	}

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getFont_family() {
		return font_family;
	}
	public void setFont_family(int font_family) {
		this.font_family = font_family;
	}
	public int getZ_index() {
		return z_index;
	}
	public void setZ_index(int z_index) {
		this.z_index = z_index;
	}
}
