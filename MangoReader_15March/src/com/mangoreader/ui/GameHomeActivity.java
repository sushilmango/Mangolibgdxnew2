package com.mangoreader.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

public class GameHomeActivity extends Activity {
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String path = getIntent().getStringExtra("GAME_URL");
		
		WebView webView = new WebView(this);
		webView.getSettings().setDomStorageEnabled(true);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setUseWideViewPort(true);
		webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		webView.loadUrl("file:///"+Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+path);
		
		setContentView(webView);
	}
}
