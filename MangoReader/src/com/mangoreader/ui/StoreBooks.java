package com.mangoreader.ui;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mangoreader.R;
import com.mangoreader.databases.Bookdata;
import com.mangoreader.databases.DatabaseHandler;
import com.mangoreader.network.request.BookDownloadRequest;
import com.mangoreader.network.request.BookListRequestByAge;
import com.mangoreader.network.request.MoreBooksRequest;
import com.mangoreader.network.request.SearchRequest;

import com.mangoreader.network.response.BookListResponseByAge;
import com.mangoreader.network.response.BookListResponseByAge.Book;
import com.mangoreader.network.response.BookListResponseBycategory;

import com.network.HttpResponseHandler;
import com.network.NetworkHandler;
import com.network.request.NetworkRequest;


public class StoreBooks extends Activity implements HttpResponseHandler, OnItemClickListener {
	String newString;
	private HashMap<String, String> image,getmap;
	private static ProgressBar downloadProgressBar;  
	private GridView gridView;
	private ArrayList<HashMap<String, String>> arraylist;
	private HashMap<String, String[]> categoryhash,getmapcategory;
	private ArrayList<HashMap<String, String[]>> arraylistcategory;
	private com.mangoreader.ui.ListViewAdapter adapter;
	static String FLAG = "flag";
	static String COUNTRY = "country";
	ArrayList<Book> list;
	ArrayList<com.mangoreader.network.response.BookListResponseBycategory.Book> list1;
	HashMap<String, ArrayList<Book>> map;
	HashMap<String, ArrayList<com.mangoreader.network.response.BookListResponseBycategory.Book>> map1;
	String key[];
	String age,group,one,searchtext;
	private Bundle extras;
	public static ImageView imageView;
	private ImageLoader imageLoader;
	private IconLoader iconloader;
	private SharedPreferences prefs1,prefs;
	SharedPreferences.Editor editor;
	private static DatabaseHandler databaseHandler;
	public static Dialog dialog;
	private static String Category[];
	private static Bookdata bookdata;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.storebook);

		gridView = (GridView)findViewById(R.id.gridView1);
		gridView.setOnItemClickListener(this);
		
		imageLoader = new ImageLoader(this);
		iconloader = new IconLoader(this);
		
		prefs1 = PreferenceManager.getDefaultSharedPreferences(this);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		editor = prefs1.edit();
		
		databaseHandler = new DatabaseHandler(this);
		

		extras = getIntent().getExtras();
		if(extras == null) {
			newString= null;
		} else {
			age= extras.getString("SECTION");
			group = extras.getString("GROUP");
			one = extras.getString("value");
			searchtext= extras.getString("SEARCH");
		}
		TextView textView = (TextView)findViewById(R.id.categoryname);
		textView.setText(age);

		Log.e("one","one"+one);

	
		if (one.equals("search")) {

			NetworkHandler.getInstance().init();
			SearchRequest request = new SearchRequest(searchtext);
			NetworkHandler.getInstance().addRequest(request);
			request.setNetworkResponseHandler(this);
			//Toast.makeText(StoreBooks.this, "searching"+searchtext, Toast.LENGTH_SHORT).show();	
		}


	}


	@Override
	public void onNetworkResponse(NetworkRequest req, byte[] data) {

		if (req instanceof SearchRequest){
			try {
				BookListResponseByAge response = new BookListResponseByAge(data);
				//System.out.println(">>>>>>>>>>>" + response.getBooks());

				arraylist = new ArrayList<HashMap<String, String>>();
				arraylistcategory = new ArrayList<HashMap<String,String[]>>();


				

				map = new HashMap<String, ArrayList<Book>>();

				for(Book book : response.getBooks()){ 

					
					//Age_Group Filter
					for(String ag : book.getInfo().getAgeGroups()){

						list =  map.get(ag);
						Log.e("book name", ""+book.getInfo().getAgeGroups().toString());

						if(list==null){
							list = new ArrayList<BookListResponseByAge.Book>();
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
					String  price = Double.toString(book.getPrice());
					image.put("price",price);
					int i = book.getInfo().getCategories().length;
					String str [] = book.getInfo().getCategories();
					categoryhash.put("catearraray", str);
					
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

					Log.e("category??",">"+category);
					image.put("categorystring",categorystring);
				//    image.put("category", str[]);
					String  pagecount = Integer.toString(book.getPage_count());
				    image.put("pagecount", pagecount);
				    
				    
				    
				    image.put("language", book.getInfo().getLanguage());
				    editor.putString("id",book.getId());

					editor.commit();
				    
					//	image.put("id",bookList.get(i).getId() );
					//image.put("price",""+bookList.get(i).getPrice());
					//image.put("currency", ""+bookList.get(i).getCurrency());






					arraylist.add(image);
					arraylistcategory.add(categoryhash);


				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					adapter = new ListViewAdapter(StoreBooks.this, arraylist);
					//	System.out.println("adapter "+adapter);
					// Binds the Adapter to the ListView
					gridView.setAdapter(adapter);


				}
			});



		}


		for ( String key : map.keySet() ) {
			/*  System.out.println( key );
		    System.out.println("value"+map.get(key));
		    System.out.println("LIST"+list.get(1).getCover());
		    Log.e("LIST value",""+list.get(1).getCover());*/

		}
	}


	@Override
	public void onItemClick(AdapterView<?> adapterView, View v, int position, long arg3) {
		// TODO Auto-generated method stub
		
		
		getmap = new HashMap<String, String>();
		getmapcategory = new HashMap<String, String[]>(); 

		getmap = arraylist.get(position);
		getmapcategory = arraylistcategory.get(position);
		String name = getmap.get("title");
		String id = getmap.get("bookid");
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
		Log.e("cate", s);
		
		
		//plitPreserveAllTokens(s, ",");
		//String str[] = s;
  
		
		detatilaleart( name,synopsis,price,imagepath,s,pageno,language,currency,str);

		
		
		
		
		
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
		dialog.setCanceledOnTouchOutside(false);
		downloadProgressBar  = (ProgressBar)dialog.findViewById(R.id.progress_bar); 

		TextView categorytext = (TextView)dialog.findViewById(R.id.textcategory);
		categorytext.setText(category);

		String lang = "Language :-"+"\n"+languages;
		TextView langtext = (TextView)dialog.findViewById(R.id.textlanguages);
		langtext.setText(lang);

		TextView pages = (TextView)dialog.findViewById(R.id.textpagecount);
		pages.setText(pageno);

		TextView txt = (TextView) dialog.findViewById(R.id.text);

		String upToNCharacters = Synopsis.substring(0, Math.min(Synopsis.length(), 230));
		txt.setText(upToNCharacters);
		TextView name = (TextView) dialog.findViewById(R.id.bookname);
		name.setText(Title);
		TextView pricetext = (TextView)dialog.findViewById(R.id.pricetext);
		pricetext.setText("$"+Price);

		imageView = (ImageView)dialog.findViewById(R.id.image);
		imageView.setImageResource(R.drawable.cover);
		
		Log.e("image path", "http://next.mangoreader.com"+imagepath);
  
		iconloader.DisplayImage(imagepath, imageView);
		

		ImageView closeimage = (ImageView)dialog.findViewById(R.id.closebutton);
		closeimage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				

				
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
					BookDownloadRequest request = new BookDownloadRequest(StoreBooks.this,id,userName,authToken);
					request.setNetworkResponseHandler((StoreBooks.this)); 
					NetworkHandler.getInstance().addRequest(request);
				//	dialog.dismiss();
					

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
					
					
					
					
					Log.e("cat array length",""+cat.length);
					
			
				}else {
					dialog.dismiss();
					Intent intent = new Intent(StoreBooks.this,LoginScreen.class);
					startActivity(intent);
					Toast.makeText(StoreBooks.this, "Please Login", Toast.LENGTH_SHORT).show();
				}
			}

		});

		dialog.show();
	}


	@Override
	public void onNetworkResponseProgress(NetworkRequest arg0, long arg1) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+arg0+"  Progres ?????  "+ arg1);
		
		if(arg0 instanceof BookDownloadRequest){
			 Message msg = handler.obtainMessage();
		        Bundle b = new Bundle();                
		        b.putInt("state", 1);   
		        b.putInt("progress", (int)arg1);   
		        msg.setData(b);
			
		        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMM "+arg1); 
		        handler.sendMessage(msg);
			
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
	        		databaseHandler.addBook(bookdata);
					for (int i = 0; i < Category.length; i++) {
						bookdata.setCategoryID(Category[i]);
						databaseHandler.addcategory(bookdata);
					}
	        		downloadProgressBar.setVisibility(View.GONE);
	        		dialog.dismiss();
	        	}
	        }
	    }
	};

	@Override
	public void onNetworkResponseStart(NetworkRequest arg0, long arg1) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+arg0+"  Start?? "+ arg1);
		
	}

	@Override
	public void onNetworkResponseStop(NetworkRequest arg0) {
		if(arg0 instanceof BookDownloadRequest){
			 Message msg = handler.obtainMessage();
		        Bundle b = new Bundle();                
		        b.putInt("state", 2);   
		        msg.setData(b);
		        handler.sendMessage(msg);
			
		}
		
	}


	@Override
	public void onNetworkError(NetworkRequest paramNetworkRequest, String code) {
		// TODO Auto-generated method stub
		
	}



}
