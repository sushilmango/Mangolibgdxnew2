package com.mangoreader.ui;

import java.io.File;
import java.util.ArrayList;

import net.mangoreader.gdx.data.model.Game;
import net.mangoreader.gdx.data.model.Story;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.mangoreader.R;
import com.mangoreader.data.BookParser;
import com.mangoreader.utils.Defines;

public class GameDisplayActivity extends Activity implements OnItemClickListener/*,OnItemLongClickListener*/ {

	GridView gridView;
	ArrayList<Item> gridArray = new ArrayList<Item>();
	ArrayList<String> bookid = new ArrayList<String>();
	
	ArrayList<Game> mGameArray;
	
	CustomGridViewAdapter customGridAdapter;
	private String bookID;
	
	//Bitmap bookimage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.mybooks);

		Intent intent = getIntent();
		
		bookID = intent.getStringExtra("BOOK_ID"); 

		//"Android/data/com.mangoreader/files/MangoBook_"+bookID+"games";
		
		TextView textViewcatename = (TextView)findViewById(R.id.categoryname);
		
		Story story = null;
		
		try{
		BookParser bp = new BookParser(bookID, getApplicationContext());
		
		story = bp.getStory();
		
		}catch (Exception e) {
			
		}
		
		mGameArray = story.getGames();
		
		/*textViewcatename.setText(categoryname);*/


		/*List<Bookdata> contacts = databaseHandler.getAlldata();  

		for (Bookdata cn : contacts) {
			String log = "Id: "+cn.getId()+" ,Name: " + cn.getBookName() + " ,Phone: " + cn.getPrice();
			// Writing Contacts to log
			Log.e("Name: ", log);
		}*/

		/*List<Bookdata> getallcat = databaseHandler.getallcategory();  */

		/*for (Bookdata cn : getallcat) {
			String log = "Id: "+cn.getId()+" ,Name: " + cn.getBookName();
			// Writing Contacts to log
			Log.e("All CAt: ", log);
		}*/

		/*Log.e("caty name", "caty"+categoryname);
		List<Bookdata> category = databaseHandler.getCaegory(categoryname);  

		for (Bookdata cn : category) {
			String log = "Name: " + cn.getCategoryID();
			// Writing Contacts to log
			bookid.add(cn.getCategoryID());
			Log.e("categoryyyyyyyy: ", log);
		}*/

		for (int i = 0; i < mGameArray.size(); i++) {
			
			Game game = mGameArray.get(i);

		/*	List<Bookdata> bookname = databaseHandler.getbookdata(bookid.get(i));  

			for (Bookdata cn : bookname) {
				String log = "Name: " + cn.getBookName();
				// Writing Contacts to log
				Log.e("book Name: ", log);
				Log.e("Books NAme from table: ", ""+cn.getBookName());
				Log.e("Book Id", ""+cn.getId());
				Log.e("Books price from table: ", ""+cn.getPrice());
				Log.e("Books currency from table: ", ""+cn.getCurrency());*/
				//bookimage = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_action_adventure);
			
			/*final File file = new File(Environment.getExternalStorageDirectory()
                    .getAbsolutePath(), filename);*/
				//bookimage = BitmapFactory.decodeByteArray(cn.getImage(), 0, cn.getImage().length);
			Bitmap bitmap =  BitmapFactory.decodeFile("Android/data/com.mangoreader/files/MangoBook_"+bookID+"games"+"/"+game.getName()+"/game_start.png");

				gridArray.add(new Item(bitmap/*getCroppedBitmap(bookimage)*/,game.getName(),game.getId()));


			/*}*/
		}

		gridView = (GridView) findViewById(R.id.gridView1);
		customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid, gridArray);
		gridView.setAdapter(customGridAdapter);
		gridView.setOnItemClickListener(this);
		//gridView.setOnItemLongClickListener(this);
		
	}
	
	public Bitmap getCroppedBitmap(Bitmap bitmap) {
	    Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
	            bitmap.getHeight(), Config.ARGB_8888);
	    Canvas canvas = new Canvas(output);

	    final int color = 0xff424242;
	    final Paint paint = new Paint();
	    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

	    paint.setAntiAlias(true);
	    canvas.drawARGB(0, 0, 0, 0);
	    paint.setColor(color);
	    // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
	    canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
	            bitmap.getWidth() / 2, paint);
	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	    canvas.drawBitmap(bitmap, rect, rect, paint);
	    //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
	    //return _bmp;
	    return output;
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {

		
		String value =	"Android/data/com.mangoreader/files/MangoBook_"+bookID+"/games"+"/"+mGameArray.get(pos).getName()+"/"+"index.html";
		Intent intent = new Intent(this,GameHomeActivity.class);
		intent.putExtra("GAME_URL", value); 
		startActivity(intent);
		Log.e("value", value);
		Toast.makeText(this, "Pos"+value, Toast.LENGTH_SHORT).show();
	}
	/*@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos,
			long arg3) {
		
		gridView.setOnItemClickListener(null);
		
		String value=	gridArray.get(pos).getBookid();
		Toast.makeText(this, "Book ID"+value, Toast.LENGTH_SHORT).show();
		gridView.setOnItemClickListener(this);
		return false;
	}*/

}
