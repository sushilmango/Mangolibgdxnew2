package com.mangoreader.ui;

import java.util.List;

import com.mangoreader.R;
import com.mangoreader.databases.Bookdata;
import com.mangoreader.databases.DatabaseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class check extends Activity {
	DatabaseHandler databaseHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check);
		
		 databaseHandler = new DatabaseHandler(this);
		 Bookdata bookdata = new Bookdata();
		 bookdata.setBookName("sushil");
		 bookdata.setId("4");
		 bookdata.setCurrency("IND");
		 bookdata.setImagepath("data");
		 bookdata.setPrice("hundre");
		//databaseHandler.addBook("2", "s", "s", "s","us");
		databaseHandler.addBook(bookdata);
		
		
		Button button = (Button)findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 List<Bookdata> contacts = databaseHandler.getAlldata();  
			
				  for (Bookdata cn : contacts) {
			            String log = "Id: "+cn.getId()+" ,Name: " + cn.getBookName() + " ,Phone: " + cn.getPrice();
			                // Writing Contacts to log
			        Log.e("Name: ", log);
				  }
			}
		});
	}
}
