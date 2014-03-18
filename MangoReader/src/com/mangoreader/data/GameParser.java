package com.mangoreader.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;

import net.mangoreader.gdx.data.model.Layer;
import net.mangoreader.gdx.data.model.Layer.Style;
import net.mangoreader.gdx.data.model.Page;
import net.mangoreader.gdx.data.model.Story;
import net.mangoreader.gdx.data.model.WordMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class GameParser {

	private Context mContext;
	private File dir; 

	public GameParser(String id,Context context) {
		mContext = context;
		dir = new File( mContext.getExternalFilesDir(null).toString()
				+ "/MangoBook_"+id);


		System.out.println("PATH ::"+dir); 
		//Log.e("PATH", ""+dir);


		if (dir.exists()) {
			//Log.e("file is exist", "");
		}else {
			//Log.e("file is not exist", "");
		}
	}

	public boolean isExists(){
		return dir.exists();
	}

	public Story getStory() throws JSONException{

		Story story = null;

		if(isExists()){



			String [] files = dir.list(new FilenameFilter() {
				@Override
				public boolean accept(File file, String path) { 
					path = path.toLowerCase();

					System.out.println("JSON path "+ path); 
					//Log.e("Json path", ""+path);
					return path.endsWith(".json");
				}
			});



			if(files != null && files.length > 0){
				//MangoTODO read only one json file
				File storyJson = new File(dir,files[0]);
				StringBuffer sbf = new StringBuffer();
				FileReader fio = null;
				BufferedReader br =	null;

				try{
					fio = new FileReader(storyJson);
					br = new BufferedReader(fio);
					String line;
					while((line = br.readLine()) != null){
						sbf.append(line);
					}

				}catch(IOException e){
					e.printStackTrace();
				}finally{
					if(br != null){
						try{
							br.close();
						}catch(IOException e){
							e.printStackTrace();
						}
					}
				}

				JSONObject jsonObj = new JSONObject(sbf.toString()); 

				story = new Story();

				JSONArray pageArray = jsonObj.getJSONArray("pages"); 

				System.out.println(" page array :"+ pageArray); 

				Page[] pages = new Page[ pageArray.length()];

				for (int i = 0 ; i < pageArray.length(); i++){

					Page page = new Page();

					System.out.println("Size of page array :"+ pageArray.length()); 

					JSONObject pageJSON = pageArray.getJSONObject(i);

					//	page.setPageNo(pageJSON.getInt("pageNo"));

					JSONArray layerArray = pageJSON.getJSONArray("layers");

					Layer[] layers = new Layer[layerArray.length()];
					for (int j = 0 ; j < layerArray.length(); j++){

						JSONObject layerJSON = layerArray.getJSONObject(j);
						Layer layer = new Layer();
						Style style = new Style();

						layer.setAlignment(getString(layerJSON,"alignment"));
						layer.setType(getString(layerJSON,"type"));
						layer.setUrl(getString(layerJSON,"url"));
						layer.setText(getString(layerJSON,"text"));

						if("text".equals(layer.getType())){
							
							try {
								JSONObject jsonstyle = layerJSON.getJSONObject("style");
								
								if(jsonstyle == null){return null;}

								String height = jsonstyle.getString("height");
								//int h = Integer.parseInt(height);
								style.setHeight(getInt(height));
								
								//Log.e("height", ""+getInt(height));

								String width = jsonstyle.getString("width");
								//int w = Integer.parseInt(width);
								
								style.setWidth(getInt(width));
								//Log.e("width", ""+getInt(width));


								String left = jsonstyle.getString("left");
							//	int l = Integer.parseInt(left);
								style.setLeft(getInt(left));
								//Log.e("left", ""+getInt(left));

							                      
								String top = jsonstyle.getString("top");
							//	int t = Integer.parseInt(top);
							     style.settop(getInt(top));
								
								
								//Log.e("top", ""+getInt(top));

								String top_ratio = jsonstyle.getString("top_ratio");
							
								//Float top_rat = Float.parseFloat(top_ratio);
								style.setTop_ratio(getFloat(top_ratio));
								//Log.e("top_ratio", ""+getFloat(top_ratio));


								String left_ratio = jsonstyle.getString("left_ratio");
							//	Float left_rat = Float.parseFloat(left_ratio);
								//style.setLeft_ratio(left_rat);
								//Log.e("left_ratio", ""+getFloat(left_ratio));
								
								String font_family = jsonstyle.getString("font-family");
								//int font = Integer.parseInt(font_family);
							//	style.setFont_family(font);
								//Log.e("font", ""+getInt(font_family));
								

								String color = jsonstyle.getString("color");
								//int left_rat = Integer.parseInt(left_ratio);
								//style.setColor(color);
								//Log.e("color", ""+color);

								
								String z_index = jsonstyle.getString("z-index");
								//int zindex = Integer.parseInt(z_index);
								//style.setZ_index(zindex);
								//Log.e("z_index", ""+getInt(z_index));
								
							} catch (Exception e) {
								// TODO: handle exception
							}

						
                                     

						}


						if("audio".equals(layer.getType())){

							JSONArray wordArray = layerJSON.getJSONArray("wordMap");

							WordMap[] wordMaps = new WordMap[wordArray.length()];

							for(int w= 0 ; w < wordArray.length(); w++){

								JSONObject wordMapJSON = wordArray.getJSONObject(w);

								WordMap wordMap = new WordMap();
								wordMap.setWord(wordMapJSON.getString("word"));
								wordMap.setStep(wordMapJSON.getString("step"));
								wordMap.setWordIdx(wordMapJSON.getString("wordIdx"));
								wordMaps[w] = wordMap;
							}

							layer.setWordMap(wordMaps);

							JSONArray timeArray = layerJSON.getJSONArray("wordTimes");

							String[] wordTimes = new String[timeArray.length()];

							for (int w = 0; w < timeArray.length(); w++) {

								wordTimes [w] = timeArray.getString(w);
							}

							layer.setWordTimes(wordTimes);

						}//End of audio type


						layers[j] = layer;
					}

					page.setLayers(layers);


					pages[i] = page;
				}

				story.setPages(pages);

			}
		}


		return story;	

	}

	private String getString(JSONObject json, String key){

		try{
			return json.getString(key);
		}catch(JSONException e){}
		catch(Exception e){}

		return "";
	}
	
	private float getFloat(String value) {
		try {
			return Float.parseFloat(value);

		} catch (NumberFormatException e) {
			return 0f;
		} 
	}
	


	private int getInt( String value){
		try {
			return Integer.parseInt(value);

		} catch (NumberFormatException e) {
			return 0;
		}
	}

}
