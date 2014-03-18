package com.mangoreader.network.response;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mangoreader.databases.Bookdata;
import com.mangoreader.databases.DatabaseHandler;
import com.mangoreader.network.request.BookDownloadRequest;
import com.mangoreader.ui.LoginScreen;
import com.mangoreader.ui.HomeScreenActivity;
import com.mangoreader.utils.Defines;

public class BookDownloadResponse extends AsyncTask<byte[], Void, Boolean>{

	private String book_id;
	String root;
	File myDir,file;

	private Context mContext;


	ProgressDialog progressBar;
	private int progressBarStatus = 0;


	private long fileSize = 0;

	private Handler progressBarHandler;

	public BookDownloadResponse(Context context,String book_id) {
		this.book_id = book_id;
		mContext = context;
		System.out.println("????????????????????????????????????????? bbok id "+book_id);
		
	}

	@Override
	protected Boolean doInBackground(byte[]... params) {

		myDir = new File( mContext.getExternalFilesDir(null).toString()
				+ "/MangoBook_"+book_id);

		myDir.mkdirs();

		String fname = "zipped";
		file = new File (myDir, fname);
		boolean isSucess = false;

		unzipepub();
		/*FileOutputStream fout = null;


		boolean isSucess = false;


			//		////////////////////////////////////////////////
		long bytes = file.length();
		//		//double kilobytes = (bytes / 1024);
		fileSize = bytes;
		//

		//;
		//
		//

		 


		String sdcard_path = Environment.getExternalStorageDirectory().getAbsolutePath();

		File dir = new File(sdcard_path,Defines.ROOT_DIR);

		if(!dir.exists()){
			dir.mkdir();

			System.out.println("??"+dir.exists()); 
		}

		File mangoredaerFile = new File(sdcard_path+"/"+ Defines.ROOT_DIR +"/"+book_id);


		//root = Environment.getExternalStorageDirectory().toString();


		try {
			fout = new FileOutputStream(file);

			fout.write(params[0]);
			fout.flush();
			unzipepub();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

			if(fout != null){
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
*/
		if(file.exists()){
			isSucess = true;
		}

		return isSucess;
	}




	@Override
	protected void onPostExecute(Boolean result) {

		//MangoTODO Show dialog box on download failed!
		super.onPostExecute(result);


		Log.e("Download Complete", "huryyyyyyyyyyyyyyyyyyyyyyyyyyyyyyeeeeeeeee");
		DatabaseHandler databaseHandler = new DatabaseHandler(mContext);

		List<Bookdata> book = databaseHandler.getAlldata();       

		for (Bookdata cn : book) {
			String log = "Id: "+cn.getId()+" ,Name: " + cn.getBookName() + " ,Phone: " + cn.getPrice();
			// Writing Contacts to log
			Log.d("Name: ", log);
		}

	}

	@Override
	protected void onPreExecute() {
		
		
		
		
		super.onPreExecute();
	} 


	@SuppressLint("NewApi")
	private void unzipepub() {
		BufferedOutputStream bufferedOutputStream = null;
		FileInputStream fileInputStream;
		String descpath = myDir.getPath()+"/";
		Log.e("desc path", descpath);
		//	Log.e("store path", storepath);
		File dest_file = new File(descpath);
		//dest_file.mkdirs(); // creates if destination directory not existed

		try {
			Log.e("try","???????????????????/");
			fileInputStream = new FileInputStream(file);
			ZipInputStream zipInputStream = new ZipInputStream(
					new BufferedInputStream(fileInputStream));
			ZipEntry zipEntry;
			dest_file = new File(descpath + "/res");
			dest_file.mkdirs();

			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
				Log.e("while","???????????????????/");
				String zipEntryName = zipEntry.getName();
				File file = new File(descpath + zipEntryName);

				if (file.exists()) {

					Log.e("file exist","???????????????????/");
				} else {
					if (zipEntry.isDirectory()) {
						file.mkdirs();
						Log.e("directory", "in unzip loop");
					} else {
						if (!file.getParentFile().exists()) {
							file.getParentFile().mkdirs();
						}
						Log.e("File", descpath + zipEntryName);
						// file.createNewFile();
						byte buffer[] = new byte[1024];
						FileOutputStream fileOutputStream = new FileOutputStream(
								descpath + zipEntryName);
						// FileOutputStream
						// fileOutputStream=context.openFileOutput(descpath +
						// zipEntryName, Context.MODE_PRIVATE);
						bufferedOutputStream = new BufferedOutputStream(
								fileOutputStream, 1024);
						int count;

						while ((count = zipInputStream.read(buffer, 0, 1024)) != -1) {
							bufferedOutputStream.write(buffer, 0, count);
						}
						file.setReadable(true, true);

						bufferedOutputStream.flush();
						bufferedOutputStream.close();
					}
				}
			}
			zipInputStream.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public int doSomeTasks() {

		while (fileSize <= 1000000) {

			fileSize++;

			if (fileSize == 100000) {
				return 10;
			} else if (fileSize == 200000) {
				return 20;
			} else if (fileSize == 300000) {
				return 30;
			}
			// ...add your own

		}

		return 100;

	}

}
