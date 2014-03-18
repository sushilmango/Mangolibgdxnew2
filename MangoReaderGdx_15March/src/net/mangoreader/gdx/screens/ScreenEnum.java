package net.mangoreader.gdx.screens;

public enum ScreenEnum {
	
	SCREEN_1("STORE"), SCREEN_2("BookReader"),SCREEN_3("category"),SCREEN_4("mystore");
	
	
	private String value;
	private ScreenEnum(String value){
		this.value = value;
	}
	
	public String getValue(){
		
		return value;
	}

}
