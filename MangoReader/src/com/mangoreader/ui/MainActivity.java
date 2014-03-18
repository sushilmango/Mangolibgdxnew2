package com.mangoreader.ui;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import net.mangoreader.gdx.controller.ApplicationAssets;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;

import com.mangoreader.R;
import com.mangoreader.databases.Bookdata;
import com.mangoreader.databases.DatabaseHandler;
import com.mangoreader.network.request.AgeGroupRequest;
import com.mangoreader.network.request.BookDownloadRequest;
import com.mangoreader.network.request.BookListRequestByAge;
import com.mangoreader.network.request.CategoryRequest;
import com.mangoreader.network.request.FeaturedImagesRequest;
import com.mangoreader.network.request.GradeRequest;
import com.mangoreader.network.request.LanguageRequest;
import com.mangoreader.network.response.AgeGroupResponse;
import com.mangoreader.network.response.AgeGroupResponse.AgeGroup;
import com.mangoreader.network.response.BookDownloadResponse;
import com.mangoreader.network.response.BookListResponseByAge;
import com.mangoreader.network.response.BookListResponseByAge.Book;
import com.mangoreader.network.response.CategoryResponse;
import com.mangoreader.network.response.CategoryResponse.CategoryBook;
import com.mangoreader.network.response.FeaturedImageResponse;
import com.mangoreader.network.response.GradesResponse;
import com.mangoreader.network.response.GradesResponse.Grades;
import com.mangoreader.network.response.LanguageResponse;
import com.mangoreader.network.response.LanguageResponse.LanguagesBook;
import com.network.HttpResponseHandler;
import com.network.NetworkHandler;
import com.network.request.DownloadRequest;
import com.network.request.NetworkRequest;



@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnMenuItemClickListener, HttpResponseHandler, OnClickListener,OnItemClickListener {

	
	private HashMap<String, String> image,getmap;
	private HashMap<String, String[]> categoryhash,getmapcategory;
	private ArrayList<HashMap<String, String[]>> arraylistcategory;
	
	
	protected static final String TAG = "MainActivity";

	SharedPreferences.Editor editor;
	private static ProgressBar progressBar;

	public static SharedPreferences prefs1;

	public static SharedPreferences prefs;
	 Bitmap imagebitmap;
	public static Context context;
	public   static int countprogressbar =0;
	CategoryBook categoryBook ;
	ImageView homelogoimage;
	public static Dialog dialog;

	private ArrayList<String> catdesc,catename,cateid,languageslist,agelist,gradelist,poplogoutlist;

	private PopupMenu popupMenu,popupMenu1,popupMenu2,popupMenu3,popupMenulogout;

	int cat =1;
	int age =2;
	int language = 3;
	int grade = 4;
	int menu = 5;

	//private ListView listView;
	//private Dataset dataSet;
	private SectionedGridViewAdapter adapter = null;
	//private LinkedHashMap<String, Cursor> cursorMap;
	//HashMap<String, ArrayList<Book>> map;
	ArrayList<Book> list;
	//ArrayList<HashMap<String, String>> arraylist;

	private String [] mAgeGroups = {"0-2","3-5","6-8","9-10","11-13","13+"};

	private View [] gridItems;

	String catname = "sus";
	private LayoutInflater inflater;

	private ImageLoader imageLoader;
	private IconLoader iconloader;
	public SearchView searchView;
	public static DatabaseHandler databaseHandler;

	private HashMap<String, ArrayList<Book>> map;

	private CoverFlow coverFlow;

	private ArrayList<HashMap<String, String>> coverFlowList;

	private ImageView imageView;

	private static ProgressBar downloadProgressBar;  
	private static Bookdata bookdata;
	private static	int arraylenth = 0;
	private static String Category[];
	
	private HashMap<String, String> downloadUrls = new HashMap<String, String>();
	private int DOWNLOAD_ERROR = 401;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		prefs1 = PreferenceManager.getDefaultSharedPreferences(this);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);


		editor = prefs1.edit();

		imageLoader = new ImageLoader(context);
		iconloader = new IconLoader(context);
		
      //  progressBar = (ProgressBar)MainActivity.this.findViewById(R.id.progressBar1);
		// create data set
		//	dataSet = new Dataset();

		setContentView(R.layout.grid_layout);

		databaseHandler = new DatabaseHandler(this);
		
		//databaseHandler.addBook("3", "s", "s", "s","us");

		homelogoimage = (ImageView)findViewById(R.id.button);
		homelogoimage.setOnClickListener(this);


		catdesc = new ArrayList<String>();
		cateid = new ArrayList<String>();
		catename = new ArrayList<String>();
		poplogoutlist = new ArrayList<String>();
		poplogoutlist.add("Logout");

		searchView= (SearchView)findViewById(R.id.search);
		int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
		TextView textView = (TextView) searchView.findViewById(id);
		textView.setTextColor(Color.BLACK);

		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(MainActivity.this,StoreBooks.class);
				intent.putExtra("SEARCH", query);
				intent.putExtra("value", "search");
				startActivity(intent);

				Toast.makeText(getApplicationContext(), ""+query, Toast.LENGTH_SHORT).show();
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				// TODO Auto-generated method stub
				return false;
			}
		});

		languageslist = new ArrayList<String>();
		agelist = new ArrayList<String>();
		gradelist = new ArrayList<String>();
		
		
		
	

		NetworkHandler.getInstance().init();
		CategoryRequest catrequest = new CategoryRequest();
		NetworkHandler.getInstance().addRequest(catrequest);

		catrequest.setNetworkResponseHandler(this);


		NetworkHandler.getInstance().init();
		LanguageRequest langreq = new LanguageRequest();
		NetworkHandler.getInstance().addRequest(langreq);

		langreq.setNetworkResponseHandler(this);



		NetworkHandler.getInstance().init();
		AgeGroupRequest agereq = new AgeGroupRequest();
		NetworkHandler.getInstance().addRequest(agereq);

		agereq.setNetworkResponseHandler(this);


		NetworkHandler.getInstance().init();
		GradeRequest gradereq = new GradeRequest();
		NetworkHandler.getInstance().addRequest(gradereq);

		gradereq.setNetworkResponseHandler(this);



		
	
		//Downloading books by age group
		NetworkHandler.getInstance().init();


		inflater = LayoutInflater.from(this); 



		if(gridItems == null){
			gridItems = new View [mAgeGroups.length];
		}

		for(int i = 0 ; i < mAgeGroups.length ; i++){


			View gridItem = inflater.inflate(R.layout.grid_item_layout, null);

			((TextView)gridItem.findViewById(R.id.section_item)).setText(mAgeGroups[i]);

			((TextView)gridItem.findViewById(R.id.moretext)).setOnClickListener(this);
			((TextView)gridItem.findViewById(R.id.moretext)).setTag(mAgeGroups[i]);

			((LinearLayout)findViewById(R.id.mainGridLayout)).addView(gridItem);

			gridItems[i] = gridItem;

			BookListRequestByAge request = new BookListRequestByAge(mAgeGroups[i]);
			NetworkHandler.getInstance().addRequest(request);
			request.setNetworkResponseHandler(this);



		}

		
		NetworkHandler.getInstance().init();
		FeaturedImagesRequest request = new FeaturedImagesRequest();
		NetworkHandler.getInstance().addRequest(request);

		request.setNetworkResponseHandler(this);
		coverFlow = (CoverFlow)findViewById(R.id.coverFlow);
		coverFlow.setOnItemClickListener(this);
		coverFlow.setSpacing(-25);
		//	coverFlow.setSelection(3, true);
		coverFlow.setAnimationDuration(1000);
		
		pop();



	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		
		
		if(id == DOWNLOAD_ERROR){
			 dialog = new Dialog(this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.error_dialog);
				
				((TextView)dialog.findViewById(R.id.err)).setText(args.getString("MSG")); 
				
				dialog.findViewById(R.id.dialogButtonOK).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						dismissDialog(DOWNLOAD_ERROR);
					}
				});
				
				return dialog;
		}
		
		return super.onCreateDialog(id, args);
	}

	private void pop() {
		popupMenu = new PopupMenu(this, findViewById(R.id.anchor));
		for (int i = 0; i < catename.size(); i++) {

			popupMenu.getMenu().add(cat, i, Menu.NONE, catename.get(i));
		}

		popupMenu.setOnMenuItemClickListener(this);
		findViewById(R.id.anchor).setOnClickListener(this);

		popupMenu1 = new PopupMenu(this, findViewById(R.id.anchor1));
		for (int i = 0; i < agelist.size(); i++) {

			popupMenu1.getMenu().add(age, i, Menu.NONE, agelist.get(i));
		}

		popupMenu1.setOnMenuItemClickListener(this);
		findViewById(R.id.anchor1).setOnClickListener(this);

		popupMenu2 = new PopupMenu(this, findViewById(R.id.anchor2));


		for (int i = 0; i < languageslist.size(); i++) {

			popupMenu2.getMenu().add(language, i, Menu.NONE, languageslist.get(i));
		}
		popupMenu2.setOnMenuItemClickListener(this);
		findViewById(R.id.anchor2).setOnClickListener(this);


		popupMenu3 = new PopupMenu(this, findViewById(R.id.anchor3));


		for (int i = 0; i < gradelist.size(); i++) {

			popupMenu3.getMenu().add(grade, i, Menu.NONE, gradelist.get(i));
		}
		popupMenu3.setOnMenuItemClickListener(this);
		findViewById(R.id.anchor3).setOnClickListener(this);

		popupMenulogout = new PopupMenu(this, findViewById(R.id.buttonmenu));
		popupMenulogout.getMenu().add(menu, 0, Menu.NONE, poplogoutlist.get(0));
		popupMenulogout.setOnMenuItemClickListener(this);
		findViewById(R.id.buttonmenu).setOnClickListener(this);


	}




	@Override
	public void onNetworkResponse(NetworkRequest req, byte[] data) {
	
		if (req instanceof FeaturedImagesRequest){
			try {
				FeaturedImageResponse response = new FeaturedImageResponse(data);

				HashMap<String, ArrayList<FeaturedImageResponse.Book>>	map = new HashMap<String, ArrayList<FeaturedImageResponse.Book>>();
				ArrayList<FeaturedImageResponse.Book> list = null;
				
				coverFlowList = new ArrayList<HashMap<String, String>>();
				arraylistcategory = new ArrayList<HashMap<String,String[]>>();
				
				for(FeaturedImageResponse.Book book : response.getBooks()){ 


					//Age_Group Filter
					for(String ag : book.getInfo().getAgeGroups()){

						list =  map.get(ag);
						Log.e("book name", ""+book.getInfo().getAgeGroups().toString());

						if(list==null){
							list = new ArrayList<FeaturedImageResponse.Book>();
							map.put(ag, list);
						}
						list.add(book);

						Log.e("size", ""+map.size());

					}

				    image = new HashMap<String, String>();
				    categoryhash = new HashMap<String, String[]>();
					
					image.put("imagepath", "http://next.mangoreader.com/"+book.getCover());
					image.put("title", book.getTitle());
					image.put("bookid", book.getId());
					
					String synopsis = book.getSynopis();
					String synopsisCharacters = synopsis.substring(0, Math.min(synopsis.length(), 230));
					
					
					image.put("synopsis", synopsisCharacters);
					image.put("currency", book.getCurrency());
					String  price = Integer.toString(book.getPrice());
					image.put("price",price);
					int i = book.getInfo().getCategories().length;
					String str [] = book.getInfo().getCategories();
					
					for (int j = 0; j < i; j++) {


						Log.e("cat", ""+str[j]);
					}
					StringBuilder builder = new StringBuilder();
					for(String s : str) {

						String ext = s+","+"\n";
						builder.append(ext);
					}
					String category = builder.toString();
					String categorystring = category.substring(0, category.lastIndexOf(','));

					categoryhash.put("catearraray", str);
					Log.e("category??",">"+category);
					image.put("categorystring",categorystring);
				//    image.put("category", str[]);
					String  pagecount = Integer.toString(book.getPage_count());
				    image.put("pagecount", pagecount);
				    
				    
				    image.put("language", book.getInfo().getLanguage());
				    editor.putString("id",book.getId());

					editor.commit();

					coverFlowList.add(image); 
					arraylistcategory.add(categoryhash);


				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			runOnUiThread(new Runnable() {

				@Override
				public void run() {

					coverFlow.setAdapter(new ImageAdapter(MainActivity.this, coverFlowList));

					ImageAdapter coverImageAdapter = new ImageAdapter(MainActivity.this,coverFlowList);

					coverFlow.setAdapter(coverImageAdapter);


				}
			});



		}

		else if (req instanceof BookListRequestByAge) {
			

			final 	String group_name = ((BookListRequestByAge)req).getAgeGroup();

			/*	ArrayList<Book>  bookList = map.get(group_name);*/

			ArrayList<HashMap<String, String>>	arraylist = new ArrayList<HashMap<String,String>>();

			BookListResponseByAge response;
			try {
				response = new BookListResponseByAge(data);

				final 	Book [] bookArray = response.getBooks();

			

				Log.e("LIST ?>>>>>","ADD SECTION .....?????????????????????????????????????????????***/ "+ arraylist.size());

				runOnUiThread(new Runnable() {

					@Override
					public void run() {

					
						int index = Arrays.asList(mAgeGroups).indexOf(group_name);

						if(index > -1 ){

							View gridRow = gridItems[index];

							LinearLayout gridRowLayout = (LinearLayout) gridRow.findViewById(R.id.row_item);

							int visibleCount = 0;
							for(Book book : bookArray){
								View child = inflater.inflate(R.layout.data_item, null);


								TextView tv = (TextView) child
										.findViewById(R.id.data_item_text);
								tv.setText(book.getTitle());
								tv.setSelected(true);
								
								TextView pricetext = (TextView)child.findViewById(R.id.data_item_price);
								pricetext.setText(Double.toString(book.getPrice())+"$");

								ImageButton button = (ImageButton) child
										.findViewById(R.id.data_item_image);
								button.setOnClickListener(MainActivity.this);

								button.setTag(book);

								iconloader.DisplayImage("http://next.mangoreader.com"+book.getCover(), button);
								gridRowLayout.addView(child);

								if(visibleCount == 5){
									((TextView) ((View)gridRowLayout.getParent().getParent()).findViewById(R.id.moretext)).setVisibility(View.VISIBLE);
									break;
								}

								visibleCount++;

							}

						}

					}
				});
				
				pop();

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}if (req instanceof LanguageRequest) {
			Log.e("response","lang");
			try {
				LanguageResponse response = new LanguageResponse(data);


				for(LanguagesBook book : response.getBooks()){ 


					Log.e("lang>>>", "lang"+book.getName());

					languageslist.add(book.getName());
					/*languageslist.add(book.getCode());
					languageslist.add(book.getId());*/


				}
				pop();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



		}
		if (req instanceof AgeGroupRequest) {
			Log.e("response","lang");
			try {
				AgeGroupResponse response = new AgeGroupResponse(data);


				for(AgeGroup age : response.getAges()){ 


					Log.e("age>>>", "age"+age.getName());

					agelist.add(age.getName());
					/*languageslist.add(book.getCode());
					languageslist.add(book.getId());*/


				}
				pop();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



		}


		if (req instanceof GradeRequest) {
			Log.e("response","grade");
			try {
				GradesResponse response = new GradesResponse(data);


				for(Grades grade : response.getGrade()){ 


					Log.e("grade>>>", "grade"+grade.getName());

					gradelist.add(grade.getName());
					/*languageslist.add(book.getCode());
					languageslist.add(book.getId());*/


				}
				pop();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



		}
		
		if(req instanceof BookDownloadRequest){
			Log.e("responseeeee", "aya");

			System.out.println("CCCCCCCCC "+ new String(data));
			
			new BookDownloadResponse(getApplicationContext(),((BookDownloadRequest)req).getBookId()).execute(data); 
		
			databaseHandler.addBook(bookdata);
			for (int i = 0; i < Category.length; i++) {
				bookdata.setCategoryID(Category[i]);
				databaseHandler.addcategory(bookdata);
			}
			
		}if (req instanceof CategoryRequest) {
			try {
				CategoryResponse response = new CategoryResponse(data);

				map = new HashMap<String, ArrayList<Book>>();

				for(CategoryBook book : response.getBooks()){ 


					Log.e("cat>>>", book.getName());

					catename.add(book.getName());
					catdesc.add(book.getDesc());
					cateid.add(book.getId());

					

				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(data);
			System.out.println(req.getUrl()+ " : : "+ new String(data));
		}

	}

	

	@Override
	public void onClick(View v) {

		if (v.getId()==R.id.button) {
			finish();
		}
		if (v.getId()==R.id.anchor) {

			//Log.e("name", catename.get);
			popupMenu.show();

		}if (v.getId()==R.id.anchor1) {
			popupMenu1.show();
		}

		if (v.getId()==R.id.anchor2) {
			popupMenu2.show();
			Toast.makeText(this, "lang", Toast.LENGTH_SHORT).show();
		}if (v.getId()==R.id.buttonmenu) {
			popupMenulogout.show();
		}


		if (v.getId()==R.id.anchor3) {
			popupMenu3.show();
			Toast.makeText(this, "grade", Toast.LENGTH_SHORT).show();
		}if (v.getTag()==mAgeGroups[0]) {
			String group = (String) v.getTag();
			Toast.makeText(this, "click"+ group, Toast.LENGTH_SHORT).show();

			Intent intent = new Intent(this,StoreBooksCategory.class);
			intent.putExtra("SECTION", group);
			intent.putExtra("GROUP", "agegroup");
			startActivity(intent);
			Toast.makeText(getApplicationContext(), ""+mAgeGroups[0],Toast.LENGTH_SHORT).show();
		}
		if (v.getTag()==mAgeGroups[1]) {

			String group = (String) v.getTag();
			Toast.makeText(this, "click"+ group, Toast.LENGTH_SHORT).show();

			Intent intent = new Intent(this,StoreBooksCategory.class);
			intent.putExtra("SECTION", group);
			intent.putExtra("GROUP", "agegroup");
			startActivity(intent);
			Toast.makeText(getApplicationContext(), ""+mAgeGroups[1],Toast.LENGTH_SHORT).show();
		}
		if (v.getTag()==mAgeGroups[2]) {
			String group = (String) v.getTag();
			Toast.makeText(this, "click"+ group, Toast.LENGTH_SHORT).show();

			Intent intent = new Intent(this,StoreBooksCategory.class);
			intent.putExtra("SECTION", group);
			intent.putExtra("GROUP", "agegroup");
			startActivity(intent);
			Toast.makeText(getApplicationContext(), ""+mAgeGroups[2],Toast.LENGTH_SHORT).show();
		}
		if (v.getTag()==mAgeGroups[3]) {
			String group = (String) v.getTag();
			Toast.makeText(this, "click"+ group, Toast.LENGTH_SHORT).show();

			Intent intent = new Intent(this,StoreBooksCategory.class);
			intent.putExtra("SECTION", group);
			intent.putExtra("GROUP", "agegroup");
			startActivity(intent);
			Toast.makeText(getApplicationContext(), ""+mAgeGroups[3],Toast.LENGTH_SHORT).show();
		}
		if (v.getTag()==mAgeGroups[4]) {
			String group = (String) v.getTag();
			Toast.makeText(this, "click"+ group, Toast.LENGTH_SHORT).show();

			Intent intent = new Intent(this,StoreBooksCategory.class);
			intent.putExtra("SECTION", group);
			intent.putExtra("GROUP", "agegroup");
			startActivity(intent);
			Toast.makeText(getApplicationContext(), ""+mAgeGroups[4],Toast.LENGTH_SHORT).show();
		}
		if (v.getTag()==mAgeGroups[5]) {
			String group = (String) v.getTag();
			Toast.makeText(this, "click"+ group, Toast.LENGTH_SHORT).show();

			Intent intent = new Intent(this,StoreBooksCategory.class);
			intent.putExtra("SECTION", group);
			intent.putExtra("GROUP", "agegroup");
			startActivity(intent);
			Toast.makeText(getApplicationContext(), ""+mAgeGroups[5],Toast.LENGTH_SHORT).show();
		}
		if(v.getTag() instanceof Book){

			Book book = (Book)v.getTag();
			int i = book.getInfo().getCategories().length;
			String imagepath = "http://next.mangoreader.com"+book.getCover();

			String synopsis = book.getSynopis();
			String name = book.getTitle();
			String bookid = book.getId();
			String currency = book.getCurrency();
			String str [] = book.getInfo().getCategories(); 
			Double price = book.getPrice();
			for (int j = 0; j < i; j++) {


				Log.e("cat", ""+str[j]);
				
			}

			StringBuilder builder = new StringBuilder();
			for(String s : str) {

				String ext = s+","+"\n";
				builder.append(ext);
			}
			String category = builder.toString();
			String s = category.substring(0, category.lastIndexOf(','));
			int pagecount = book.getPage_count();

			String pageno = "Page NO"+" "+pagecount;
			Log.e("category", s);
			String language =	book.getInfo().getLanguage();
			Log.e("sysnopsis", synopsis);
			Log.e("price",""+ price);
			Log.e("ID", ""+book.getId());
			Log.e("Age Group",""+book.getInfo().getAgeGroups());
			Log.e("Page Count", ""+book.getPage_count());
			editor.putString("id",book.getId());

			editor.commit();

			Toast.makeText(this, "click"+i, Toast.LENGTH_SHORT).show();
			//showDialog();
			detatilaleart( name,synopsis,price,imagepath,s,pageno,language,currency,str);
		}

	}

	public void detatilaleart( final String Title,String Synopsis, final Double Price,final String imagepath,final String category,String pageno,
			String languages,final String currency,final String cat[]){
		 dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		final String bookname = Title;
		final String image = imagepath;
		final String cur = currency;
		final Double price = Price;
		this.Category = cat;
		

		//tell the Dialog to use the dialog.xml as it's layout description
		dialog.setContentView(R.layout.detaildialog);
		downloadProgressBar  = (ProgressBar)dialog.findViewById(R.id.progress_bar); 
		
		if(downloadUrls.containsKey(prefs1.getString("id", ""))){
			downloadProgressBar.setTag(downloadUrls.get(prefs1.getString("id", "")));
		}else{
			downloadProgressBar.setTag("");
		}
		
		
		dialog.setCanceledOnTouchOutside(false);

		TextView categorytext = (TextView)dialog.findViewById(R.id.textcategory);
		categorytext.setText(category);

		String lang = "Language :-"+"\n"+languages;
		TextView langtext = (TextView)dialog.findViewById(R.id.textlanguages);
		langtext.setText(lang);

		TextView pages = (TextView)dialog.findViewById(R.id.textpagecount);
		pages.setText(pageno);

		TextView txt = (TextView) dialog.findViewById(R.id.text);

	//	String upToNCharacters = Synopsis.substring(0, Math.min(Synopsis.length(), 230));
		txt.setText(Synopsis);
		TextView name = (TextView) dialog.findViewById(R.id.bookname);
		name.setText(Title);
		TextView pricetext = (TextView)dialog.findViewById(R.id.pricetext);
		pricetext.setText("$"+Price);

		 imageView = (ImageView)dialog.findViewById(R.id.image);
		imageView.setImageResource(R.drawable.cover);
		

		iconloader.DisplayImage(imagepath, imageView);
		

		ImageView closeimage = (ImageView)dialog.findViewById(R.id.closebutton);
		closeimage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				downloadProgressBar = null;

				
				final String id = prefs1.getString("id", null);
				Log.e("IDDDD", ""+id);
				Log.e("book name", bookname);
				Log.e("currency", ""+currency);
				Log.e("path",""+imagepath);
				Log.e("price",Double.toString(price));
			}
		});

		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);

		dialogButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {


				final String userName = prefs.getString("username", "null");
				final String authToken = prefs.getString("auth_token", "null");
				final String id = prefs1.getString("id", null);
				
				
				

				if (prefs.contains("username")) {


					
					
					NetworkHandler.getInstance().init();
					BookDownloadRequest request = new BookDownloadRequest(MainActivity.this,id,userName,authToken);
					request.setNetworkResponseHandler((MainActivity.this)); 
					NetworkHandler.getInstance().addRequest(request);
					//dialog.dismiss();
					downloadProgressBar.setTag(request.getUrl());
					
					if(!downloadUrls.containsKey(id)){
						downloadUrls.put(id, request.getUrl());
					}
					
					BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
					Bitmap bitmap = drawable.getBitmap();
					
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
					byte[] byteArray = stream.toByteArray();
					
				    bookdata = new Bookdata();
					bookdata.setBookName(bookname);
					bookdata.setId(id);
					bookdata.setImagepath(image);
					bookdata.setPrice(Double.toString(price));
					bookdata.setCurrency(currency);
					bookdata.setImage(byteArray);
					bookdata.setCat(cat);
					
					
					arraylenth = cat.length;
				}else {
					dialog.dismiss();
					Intent intent = new Intent(MainActivity.this,LoginScreen.class);
					startActivity(intent);
					Toast.makeText(MainActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
				}
			}

		});

		dialog.show();
	}


	@Override
	public boolean onMenuItemClick(MenuItem item) {

		if (item.getGroupId()==1) {

			Toast.makeText(this, ""+item.getItemId(), Toast.LENGTH_SHORT).show();
			String catyname = catename.get(item.getItemId());
			Log.e("", catyname);
			Intent intent = new Intent(this,StoreBooksCategory.class);
			intent.putExtra("SECTION", catyname);
			intent.putExtra("GROUP", "category");

			startActivity(intent);

		}if (item.getGroupId()==2) {
			Toast.makeText(this, ""+item.getOrder(), Toast.LENGTH_SHORT).show();

			String age = agelist.get(item.getItemId());
			Log.e("", age);
			Intent intent = new Intent(this,StoreBooksCategory.class);
			intent.putExtra("SECTION", age);
			intent.putExtra("GROUP", "agegroup");

			startActivity(intent);
		}
		if (item.getGroupId()==3) {
			Toast.makeText(this, ""+item.getItemId(), Toast.LENGTH_SHORT).show();
			String langname = languageslist.get(item.getItemId());
			Log.e("", langname);
			Intent intent = new Intent(this,StoreBooksCategory.class);
			intent.putExtra("SECTION", langname);
			intent.putExtra("GROUP", "language");

			startActivity(intent);
		}if (item.getGroupId()==5) {
			//editor.clear();
			//editor.putString("", value)
			ApplicationAssets.getInstance().clear();
			
			Intent intent = new Intent(this,LoginScreen.class);

			//	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			//finish();
			editor.remove("username");
			editor.commit();
			startActivity(intent);
			Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
		}

		if (item.getGroupId()==4) {
			Toast.makeText(this, ""+item.getItemId(), Toast.LENGTH_SHORT).show();
			String grade = gradelist.get(item.getItemId());
			Log.e("", grade);
			Intent intent = new Intent(this,StoreBooksCategory.class);
			intent.putExtra("SECTION", grade);
			intent.putExtra("GROUP", "grade");

			startActivity(intent);
		}
		/*	switch (item.getGroupId()) {
		case ONE:
			tv.setText("ONE");
			break;
		case TWO:
			tv.setText("TWO");
			break;
		case THREE:
			tv.setText("THREE");
			break;
		}*/
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View v, int position, long arg3) {
		// TODO Auto-generated method stub
		
		
		getmap = new HashMap<String, String>();
		getmapcategory = new HashMap<String, String[]>(); 

		getmap = coverFlowList.get(position);
		getmapcategory = arraylistcategory.get(position);
		String name = getmap.get("title");
		String id = getmap.get("bookid");
		
	    editor.putString("id",id);

		editor.commit();

		String synopsis = getmap.get("synopsis");
		String pricestring = getmap.get("price");
		Double price = Double.parseDouble(pricestring);
		String imagepath = getmap.get("imagepath");
		Log.e("image", ""+imagepath);
		
		String pagecount = getmap.get("pagecount");
		String pageno = "Page NO :-"+" "+pagecount;
		String language = getmap.get("language");
		String currency = getmap.get("currency");
		String s = getmap.get("categorystring");
		String str [] = getmapcategory.get("catearraray");
		Log.e("cate", ""+str.length);
		Toast.makeText(this,""+imagepath, Toast.LENGTH_SHORT).show();
		
		/*for (int i = 0; i < str.length; i++) {
			Log.e("catess", str[i]);
			
		}
		*/
		
		//plitPreserveAllTokens(s, ",");
		//String str[] = s;
  
		
		detatilaleart( name,synopsis,price,imagepath,s,pageno,language,currency,str);

		
	}

	@Override
	public void onNetworkResponseProgress(NetworkRequest arg0, long arg1) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+arg0+"  Progres ?????  "+ arg1);
		
		if(arg0 instanceof BookDownloadRequest){
			if(downloadProgressBar != null && arg0.getUrl().equals((String)downloadProgressBar.getTag())){
			 Message msg = handler.obtainMessage();
		        Bundle b = new Bundle();                
		        b.putInt("state", 1);   
		        b.putInt("progress", (int)arg1);   
		        msg.setData(b);
			
		        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMM "+arg1); 
		        handler.sendMessage(msg);
			}
			
		}
	}
	
	static final Handler handler = new Handler() {
	    public void handleMessage(Message msg) {
	    	
	        int state = msg.getData().getInt("state");
	        if (state == 1){
	            
	        	int progress = msg.getData().getInt("progress");
	        	
	        	if(downloadProgressBar != null){
	        		downloadProgressBar.setVisibility(View.VISIBLE);
	        		downloadProgressBar.setProgress(progress);
	        	}
	        }else if(state == 2){
	        	
	        	if(downloadProgressBar != null){
	        		downloadProgressBar.setVisibility(View.GONE);
	        		dialog.dismiss();
	        	}
	        }else if (state==3) {
	        	
                  stop();
	        /*	progressBar.setVisibility(View.INVISIBLE);
	        	countprogressbar = 0;
			*/}
	    }

		
	};
	public static void stop() {
		
	/*	Log.e("stop", "stop");
		if (progressBar!=null) {
			
			progressBar.setVisibility(View.INVISIBLE);
		}else {
			
			Log.e("progress bar null", "null");
		//	progressBar.setVisibility(View.INVISIBLE);
		}*/
		
		
	}
	
	

	@Override
	public void onNetworkResponseStart(NetworkRequest arg0, long arg1) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+arg0+"  Start?? "+ arg1);
		Log.e("Responseee", "res start");
		
	}

	@Override
	public void onNetworkResponseStop(NetworkRequest arg0) {
		Log.e("Responseee", "res stop");		
		if (countprogressbar==9) {
			 Message msg = handler.obtainMessage();
			Bundle b = new Bundle();                
	        b.putInt("state", 3);   
	        msg.setData(b);
	        handler.sendMessage(msg);
			
		}else {
			countprogressbar++;
			Log.e("count", "res"+countprogressbar);
		}
		
		if(arg0 instanceof BookDownloadRequest){
			if(downloadProgressBar != null && arg0.getUrl().equals((String)downloadProgressBar.getTag())){
			 Message msg = handler.obtainMessage();
		        Bundle b = new Bundle();                
		        b.putInt("state", 2);   
		        msg.setData(b);
		        handler.sendMessage(msg);
			}
			
		}
		
	}






	@Override
	public void onNetworkError(NetworkRequest request, final String code) {
		// TODO Auto-generated method stub
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& n "+ request.getUrl() +" "+ code); 
		if(request instanceof DownloadRequest){
			
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						try {
						final JSONObject msg = new JSONObject(new String(code));
						Log.d("ERROR MESSAGE ", msg.getString("status"));
						Bundle bundle = new Bundle();
						bundle.putString("MSG", msg.getString("desc"));
						showDialog(DOWNLOAD_ERROR , bundle);
					
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				});
			
		}
	}

}



