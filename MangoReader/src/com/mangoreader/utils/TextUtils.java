package com.mangoreader.utils;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;


public class TextUtils {

	public static boolean isNullText(String text){
		return text != null ?  text.trim().equalsIgnoreCase("null") ||
				text.trim().equalsIgnoreCase("")
				: true;
	}
	
	public static boolean isInteger(String text){
		try{
		Integer.parseInt(text);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}
	
	public static boolean isFloat(String text){
		try{
		Float.parseFloat(text);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}
	
	public static boolean isPercentageNumberString(String text){
		return isNullText(text)? false : text.endsWith("%");
	}
	
	public static int getIntegerNumber(String text){
		String num = isPercentageNumberString(text) ? text.replace("%","") : text;
		return isInteger(num) ? Integer.parseInt(num) : 0;
	}
	
	public static void applyFontStyle(View view ,String fontStyle)
	{
		TextView  label = (TextView) view;
		if (fontStyle.equalsIgnoreCase("BOLD")) {
			label.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
		} else if (fontStyle.equalsIgnoreCase("ITALIC")) {
			label.setTypeface(Typeface.DEFAULT,Typeface.ITALIC);
		} else if (fontStyle.equalsIgnoreCase("BOLD_ITALIC")) {
			label.setTypeface(Typeface.DEFAULT,Typeface.BOLD_ITALIC);
		} 
		else if (fontStyle.equalsIgnoreCase("underline")) {
			if((label.getText() !=null) &&(!isNullText(label.getText().toString())))
			{
				SpannableString span = new SpannableString(label.getText().toString());
				span.setSpan(new UnderlineSpan(), 0, label.getText().toString().length(), 0);
				label.setText(span);
			}
		} 	
		else {
			label.setTypeface(Typeface.DEFAULT,Typeface.NORMAL);
		}
	}
	
	public static boolean getNumberToBoolean(String text){
		return !isInteger(text) ? false : (Integer.parseInt(text) == 1 ? true : false );
	}
	
	/**
	 * Do not pass null if want to use default value. 
	 * @param text
	 * @return float value of text
	 */
	public static float getFloat(String text){
		String num = isPercentageNumberString(text) ? text.replace("%","") : text;
		return isFloat(num) ? Float.parseFloat(num) : 0;
		
	}
	
	
	
}
