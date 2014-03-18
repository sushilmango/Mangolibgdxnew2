package com.mangoreader.ui;

import java.util.ArrayList;
import java.util.HashMap;

import com.mangoreader.R;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	CoverFlow coverFlow;
	coverimageloader coverimageloader;
	int mGalleryItemBackground;
	private Context mContext;
	ArrayList<HashMap<String, String>> data;



	private ImageView[] mImages;

	public ImageAdapter(Context c,ArrayList<HashMap<String, String>> arraylist) {
		mContext = c;
		this.data = arraylist;
		coverimageloader = new coverimageloader(c);
		mImages = new ImageView[arraylist.size()];
	}




	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("deprecation")
	public View getView(int position, View convertView, ViewGroup parent) {

		Log.e("position", ""+position);
		// Use this code if you want to load from resources
		HashMap<String, String> resultp = new HashMap<String, String>();
		resultp = data.get(position);
		System.out.println("result>>"+resultp);
		ImageView i = new ImageView(mContext);
		i.setPadding(8, 8, 8, 8);
		i.setBackgroundResource(R.drawable.grid_item_selector);
		
		coverimageloader.DisplayImage(resultp.get("imagepath"), i);
		//i.setImageResource(mImageIds[position]);
		i.setLayoutParams(new CoverFlow.LayoutParams(mContext.getResources().getDimensionPixelSize(R.dimen.coverflow_item_w),
				mContext.getResources().getDimensionPixelSize(R.dimen.coverflow_item_h)));
	
		i.setScaleType(ImageView.ScaleType.FIT_XY);


		// Make sure we set anti-aliasing otherwise we get jaggies
		BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
		drawable.setAntiAlias(true);
		return i;

		// return mImages[position];
	}

	/**
	 * Returns the size (0.0f to 1.0f) of the views depending on the
	 * 'offset' to the center.
	 */
	public float getScale(boolean focused, int offset) {
		/* Formula: 1 / (2 ^ offset) */
		return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
	}

}

