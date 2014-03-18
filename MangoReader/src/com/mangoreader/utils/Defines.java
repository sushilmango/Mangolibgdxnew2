package com.mangoreader.utils;


public interface Defines {

	public final static String BASE_URL = "http://api.mangoreader.com/api/v2";
	public final static String BOOKDOWNLOAD_URL = "http://api.mangoreader.com/api/v2";

	public final static String LOGIN_PATH = "/sign_in";
	public final static String SIGN_UP ="/sign_up";

	public final static String Books_PATH = "/livestories.json";
	public final static String BooksDownload_PATH = "/livestories";


	public final static int  PURCHASED_BOOKS = 1;
	public final static int LIVE_BOOKS = 2;
	public final static int  FEATURED_BOOKS = 3;

	//pragma mark - Table Types

	public final static int  TABLE_TYPE_AUDIO_RECORDINGS = 1;
	public final static int  TABLE_TYPE_TEXT_TEMPLATES = 2;
	public final static int  TABLE_TYPE_CATEGORIES = 3;
	public final static int  TABLE_TYPE_AGE_GROUPS = 4; 
	
	
     ///API Method Names

	public final static String LIVE_STORIES ="livestories.json";
	public final static String  CATEGORIES = "categories.json";
	public final static String  LOGIN = "sign_in";
	public final static String  DOWNLOAD_STORY = "/livestories/%=/zipped?email=%=&auth_token=%=";
	public final static String  PURCHASED_STORIES = "users/purchased";
	public final static String  FEATURED_STORIES = "livestories/featured.json";
	public final static String  LIVE_STORIES_SEARCH = "/livestories/search";
	
	
	
	public final static String  EVENT_LOGIN_FACEBOOK ="loggedInWithFacebook";
	public final static String  EVENT_LOGIN_EMAIL ="loggedInWithEmail";
	public final static String  EVENT_SKIP_LOGIN ="skippedLogin";
	public final static String  EVENT_REDIRECT_TO_SIGNUP ="redirectedTosignup";
	public final static String  EVENT_VIDEO ="playedVideo";

	public final static String  EVENT_TIME_ON_VIEW ="timeSpentOnView";

	public final static String  EVENT_BOOK_TAPPED ="tappedOnBook";

	public final static String  EVENT_BOOK_PURCHASE_INITIATED ="initiatedBookPurchase";
	public final static String  EVENT_BOOK_PURCHASE_COMPLETED ="completedBookPurchase";
	public final static String  EVENT_BOOK_PURCHASE_CANCELLED ="cancelledBookPurchase";

	public final static String  EVENT_BOOK_SHARED ="sharedBook";
	public final static String  EVENT_BOOK_FORKED ="forkedBook";
	public final static String  EVENT_TRANSLATE_INITIATED ="translateBookInitiated";
	public final static String  EVENT_AUDIO_PAUSED ="audioPaused";
	public final static String  EVENT_AUDIO_PLAYED ="audioPlayed";
	public final static String  EVENT_GAME_CENTER_OPENED ="openedGameCenter";
	public final static String  EVENT_GAME_WORDSEARCH ="wordsearchPlayed";
	public final static String  EVENT_GAME_MEMORY ="memoryPlayed";
	public final static String  EVENT_GAME_JIGSAW ="jigsawPlayed";

	public final static String  PARAMETER_TIME_RANGE ="timeRange";
	public final static String  PARAMETER_VIEW_NAME ="viewName";
	public final static String  PARAMETER_BOOK_ID ="bookId";
	public final static String  PARAMETER_BOOK_TITLE ="bookTitle";

	public final static String  TIME_RANGE_10_SEC ="<10 Seconds";
	public final static String  TIME_RANGE_10TO20_SEC ="10To20 Seconds";
	public final static String  TIME_RANGE_20TO40_SEC ="20To40 Seconds";
	public final static String  TIME_RANGE_40TO60_SEC ="40To60 Seconds";
	public final static String  TIME_RANGE_1TO2_MIN ="1To2 Minutes";
	public final static String  TIME_RANGE_2TO5_MIN ="2To5 Minutes";
	public final static String  TIME_RANGE_5TO10_MIN ="5To10 Minutes";
	public final static String  TIME_RANGE_10TO20_MIN ="10To20 Minutes";
	public final static String  TIME_RANGE_20TO30_MIN ="20To30 Minutes";
	public final static String  TIME_RANGE_30TO60_MIN ="30To60 Minutes";
	public final static String  TIME_RANGE_1_HOUR =">1 hour";

//	#pragma mark - Views

	public final static String  VIEW_LOGIN ="LoginViewController";
	public final static String  VIEW_MY_BOOKS_FOR_ANALYTICS ="MyBooks";
	public final static String  VIEW_MY_BOOKS ="LibraryViewController";
	public final static String  VIEW_STORE_FOR_ANALYTICS ="Store";
	public final static String  VIEW_STORE ="LiveViewController";

	//#pragma mark - JSON Response KEYS

	public final static String  AUTH_TOKEN ="auth_token";
	public final static String  PAGE_NO ="pageNo";
	public final static String  LAYERS ="layers";
	public final static String  TYPE ="type";
	public final static String  TEXT ="text";
	public final static String  TEXT_POSITION_X ="left";
	public final static String  TEXT_POSITION_Y ="top";
	public final static String  TEXT_SIZE_WIDTH ="width";
	public final static String  TEXT_SIZE_HEIGHT ="height";
	public final static String  TEXT_FRAME ="style";
	public final static String  IMAGE ="image";
	public final static String  AUDIO ="audio";
	public final static String  CAPTURED_IMAGE ="capturedImage";
	public final static String  ASSET_URL ="url";
	public final static String  PAGES ="pages";
	public final static String  LEFT_RATIO ="left_ratio";
	public final static String  TOP_RATIO ="top_ratio";
	public final static String  PAGE_NAME ="name";
	public final static String  GAME ="game";
	public final static String  CUES ="wordTimes";
	public final static String  WORDMAP ="wordMap";

	public static final String ROOT_DIR = "mangoreader/res/";

	public static final String BOOK_ID = "BOOK_ID"; 


	
	
	
	

}
