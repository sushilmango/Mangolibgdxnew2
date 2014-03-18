package com.mangoreader.ui;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.mangoreader.R;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

@SuppressLint("SetJavaScriptEnabled")
public class GameScreen extends Activity {
	File myDir,file;
	private WebView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);


		String string =  GameScreen.this.getExternalFilesDir(null).toString()
				+ "/MangoBook_"+"52d0763269702d5fe1d82800"+"/games/jigsaw/index.html";

		File f = new File(string);
		if(f.exists())
		{ 
			/* do something */ 
			Log.e("File available PAth", string);

			Toast.makeText(this, "file available"+string, Toast.LENGTH_SHORT).show();

			view = (WebView)findViewById(R.id.webView1);
			 view.getSettings().setJavaScriptEnabled(true);
			 view.getSettings().setDomStorageEnabled(true);
		   // 	String filestrinf = getFileAsString(f);
			//view.loadData(filestrinf, "text/html; charset=UTF-8", null);
			view.loadUrl("file:///"+string);
		}
		else
		{
			Log.e("File not PAth", string);
			Toast.makeText(this, "file available"+string, Toast.LENGTH_SHORT).show();

			//And your other stuffs goes here
		}


		/* view = (WebView)findViewById(R.id.webView1);
		// view.getSettings().setJavaScriptEnabled(true);
		 view.loadUrl(string);*/
	}

	public String getFileAsString(File file){ FileInputStream fis = null;
	BufferedInputStream bis = null;
	DataInputStream dis = null;
	StringBuffer sb = new StringBuffer();
	try {
		fis = new FileInputStream(file);
		bis = new BufferedInputStream(fis);
		dis = new DataInputStream(bis);

		while (dis.available() != 0) {
			sb.append( dis.readLine() +"\n");
		}
		fis.close();
		bis.close();
		dis.close();

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return sb.toString();
	}
	
}


