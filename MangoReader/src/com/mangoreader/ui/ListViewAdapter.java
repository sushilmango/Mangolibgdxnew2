package com.mangoreader.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mangoreader.R;

public class ListViewAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	ImageLoader imageLoader;

	public ListViewAdapter(Context context,
			ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
		data = arraylist;
		imageLoader = new ImageLoader(context);

	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		
		TextView bookname;
		TextView price;
		ImageView bookImage;
	

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.listview_item, parent, false);
		// Get the position from the results
		HashMap<String, String> resultp = new HashMap<String, String>();
		resultp = data.get(position);
		System.out.println(resultp);

	
		bookname = (TextView) itemView.findViewById(R.id.countryqq); 
		bookname.setSelected(true);
		
		price = (TextView)itemView.findViewById(R.id.pricetext);
		price.setText(resultp.get("price")+"$");
		
		bookImage = (ImageView) itemView.findViewById(R.id.flag); 

		System.out.println(">>>>>>>>>>>>>>>>>>>>>"+bookImage);
	
		bookname.setText(resultp.get("title"));
		
		imageLoader.DisplayImage(resultp.get("imagepath"), bookImage);
	
       
		
		return itemView;
	}
}
