/*package com.mangoreader.audio;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;

import com.mangoreader.Displaybook;
import com.mangoreader.R;
import com.mangoreader.VideoViewActivity;

public class JplayerMediaFix extends TimerTask implements OnCompletionListener {

	private Displaybook activity;
	private String baseURL;
	private MediaPlayer player;
	private boolean completed;

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public JplayerMediaFix(String link, Displaybook activity) {
		this.baseURL = link;
		this.activity = activity;
		completed = false;
	}

	public boolean isPlaying() {
		if (player == null) {
			return false;
		}
		return player.isPlaying();
	}

	public MediaPlayer getPlayer() {
		return player;
	}

	public void setPlayer(MediaPlayer player) {
		if (this.player != null) {
			this.player.stop();
			this.player.reset();
			this.player.release();
		}
		this.player = player;
	}

	public void processContent(String aContent) {
		// activity.setText(aContent);
		Log.v("text  ghggg", "" + aContent);

	}

	public void PlayAudio(String link) {
		// need for another string so that base url is safe
		String source = baseURL + link;
		File file=new File(source);
		if(!file.exists()){
			activity.runOnUiThread(new Runnable() {
				
				public void run() {
					// TODO Auto-generated method stub
				activity.getPlayButton().setBackgroundResource(R.drawable.playcontrol);	
				
				}
			});
			return;
		}
		Log.v("Player ", "" + source);
		if (player != null) {
			player.stop();
			player.reset();
			player.release();

		} else {
			if (activity.getTimer() == null) {
				Timer timer = new Timer();
				timer.schedule(this, 100, 100);
				activity.setTimer(timer);
			}
		}
		player = new MediaPlayer();
		try {
			player.setDataSource(source);
			player.prepare();
			player.start();
			player.setOnCompletionListener(this);

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void prepare(String link) {
		String temp = baseURL + link;
		Log.v("Player ", "" + temp);
		player = new MediaPlayer();
		try {
			player.setDataSource(temp);
			player.prepare();

			player.setOnCompletionListener(this);
			if (activity.getTimer() == null) {
				Timer timer = new Timer();
				timer.schedule(this, 100, 100);
				activity.setTimer(timer);
			}

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void play() {

		player.start();
	}

	public void pause() {

		player.pause();
	}

	public void startVideoInFullscreen(String location) {
		Intent intent = new Intent(activity, VideoViewActivity.class);

		location = activity.getOpenoebps() + "/" + location;
		Log.v("location", "" + location);
		intent.putExtra("location", location);
		activity.startActivity(intent);
	}

	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		completed = true;

		activity.runOnUiThread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				activity.getPlayOrPause().setImageResource(
						R.drawable.playcontrol);
				activity.loadUrl("javascript:$('#jquery_jplayer').data('handleAudio').resetCues()");
				activity.getNext().performClick();
				
			}
		});

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// Log.d("vvip", "task called");
		if (player != null) {
			try {
				if (player.isPlaying()) {
					float totalduration = player.getDuration();
					float currentDuration = player.getCurrentPosition();
					float percentage;
					percentage = currentDuration / totalduration;
					percentage = percentage * 100;
					currentDuration = currentDuration / 1000;
					activity.sendData(percentage, currentDuration);

				}
			} catch (Exception e) {

			}
		}
	}

}
*/