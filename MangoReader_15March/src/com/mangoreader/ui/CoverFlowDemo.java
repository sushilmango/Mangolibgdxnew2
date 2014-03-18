package com.mangoreader.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class CoverFlowDemo extends Activity  {

	CoverFlow coverFlow;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Toast.makeText(this, "on create", Toast.LENGTH_SHORT).show();


		
		coverFlow = new CoverFlow(this);
		/*coverFlow.setOnItemClickListener(this);*/
		coverFlow.setSpacing(-25);
		//	coverFlow.setSelection(3, true);
		coverFlow.setAnimationDuration(1000);



	}



}
