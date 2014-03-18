/*package com.mangoreader.javascriptinterface;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;

import com.mangoreader.PortraitActivity;
import com.mangoreader.VideoViewActivity;
import com.mangoreader.database.DatabaseHandler;
import com.mangoreader.databaseclass.HighlightNote;

public class TextBookJavascriptInterface {

	private PortraitActivity activity;

	private DatabaseHandler db;

	private int left, top;
	private String selection, surroundingText, extendSurroundingText;
	private int startOffset;
	private String jsonLocation;

	public TextBookJavascriptInterface(PortraitActivity activity) {
		super();
		this.activity = activity;

	}

	public void processContent(String aContent) {
		Log.v("change if done", aContent);
		// Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
	}

	public void important(final String jsonLocation, String selection,
			int startOffset, String surroundingText,
			String otherNodeSurroundingtext) {
		
		 * if (selection == null) { if (activity.getWebview().getLinear() !=
		 * null) { activity.getWebview().getLinear().setVisibility(View.GONE); }
		 * } if (selection.length() == 0) { if
		 * (activity.getWebview().getLinear() != null) {
		 * activity.getWebview().getLinear().setVisibility(View.GONE); } }
		 
		this.setJsonLocation(jsonLocation);
		this.selection = selection;
		this.startOffset = startOffset;
		this.surroundingText = surroundingText;
		this.extendSurroundingText = otherNodeSurroundingtext;
	}

	public void highlight(String selection, int startOffset,
			String surroundingText, String otherNodeSurroundingtext) {
		// if (selection == null) {
		// return;
		// }
		// if (this.selection == null) {
		// return;
		// }

		if (selection.length() == 0) {
			if (this.selection.length() == 0) {
				return;
			} else {
				selection = this.selection;
				startOffset = this.startOffset;
				surroundingText = this.surroundingText;
				otherNodeSurroundingtext = this.extendSurroundingText;
			}
		}

		db = new DatabaseHandler(activity);
		if (surroundingText.equals(otherNodeSurroundingtext)) {// new string
			String left = surroundingText.substring(0, startOffset);
			String actualSelection = selection;
			int rightIndex = startOffset + actualSelection.length();
			String right = surroundingText.substring(rightIndex);
			String newSurroundingString = left
					+ "<span class=\"uiWebviewHighlight\" style=\"background-color: yellow; color: black;\">"
					+ actualSelection + "</span>" + right;
			Log.i("old", surroundingText);
			Log.i("new", newSurroundingString);
			activity.highlight(surroundingText, newSurroundingString);

			long date = System.currentTimeMillis();
			db.insertHighlight(activity.getIdentity(), activity.getPage_no(),
					date, date, 1, selection, 0, 0, startOffset,
					surroundingText);
		} else {// need to check for Notes too
			ArrayList<HighlightNote> highlight = db.getHighlightNoteAt(
					activity.getPage_no(), activity.getIdentity());
			int i = 0;
			HighlightNote highlightItem = null;
			for (; i < highlight.size(); i++) {
				highlightItem = highlight.get(i);
				if (highlightItem.getSurroundingText().contains(selection)) {
					int currentStartOffset = highlightItem.getSurroundingText()
							.indexOf(selection);
					int currentEndOffset = currentStartOffset
							+ selection.length();
					int oldStartOffset = highlightItem.getStartOffset();
					int oldEndOffset = highlightItem.getSelectedText().length()
							+ oldStartOffset;
					boolean val = currentEndOffset < currentStartOffset;
					val = val | (currentStartOffset > oldEndOffset);
					if (!val) {
						break;
					}
				}
			}// end if
			if (i == highlight.size()) {
				activity.runOnUiThread(new Runnable() {

					public void run() {
						// TODO Auto-generated method stub
						AlertDialog.Builder builder = new AlertDialog.Builder(
								activity);
						builder.setTitle("Message");
						builder.setMessage("Cannot highlight more than one paragraph");
						builder.setNeutralButton("OK", new OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();

							}
						});
						builder.show();
					}
				});
				return;
			}// end if
			highlight = db.getHighlightWithSurroundingText(
					activity.getPage_no(), activity.getIdentity(),
					highlightItem.getSurroundingText());
			int currentStartOffset = highlightItem.getSurroundingText()
					.indexOf(selection);
			int currentEndOffset = currentStartOffset + selection.length();

			for (HighlightNote item : highlight) {
				int oldIndexStart = item.getStartOffset();
				int oldIndexEnd = item.getStartOffset()
						+ item.getSelectedText().length();
				// currentstart-oldstart -----currentend <---->oldend
				if (currentStartOffset < oldIndexStart
						&& currentEndOffset <= oldIndexEnd) {
					Log.v("oldselection", highlightItem.getSelectedText());
					Log.v("newselection", selection);
					String tempText = selection.substring(0, oldIndexStart
							- currentStartOffset);
					tempText = tempText + highlightItem.getSelectedText();
					db.updateSelectedHighlight(currentStartOffset, tempText,
							item.getIdentity());
					activity.removeAllHighlight();

					return;
				}
				// oldstart<--->currentstart-----oldend----currentend
				else if (currentStartOffset >= oldIndexStart
						&& oldIndexEnd < currentEndOffset) {
					Log.v("oldselection", highlightItem.getSelectedText());
					Log.v("newselection", selection);
					String tempText = item.getSelectedText().substring(0,
							currentStartOffset - oldIndexStart);
					tempText = tempText + selection;
					db.updateSelectedHighlight(oldIndexStart, tempText,
							item.getIdentity());
					activity.removeAllHighlight();

					return;

				}
				// oldstart----currentstart-----currentend---oldend
				else if (oldIndexStart > currentStartOffset
						&& currentEndOffset < oldIndexEnd) {
					return;
				} else if ((oldIndexStart == currentStartOffset && currentEndOffset == oldIndexEnd)) {
					return;
				} else if (currentStartOffset < oldIndexStart
						&& currentEndOffset > oldIndexEnd) {
					db.updateSelectedHighlight(currentStartOffset, selection,
							item.getIdentity());
					activity.removeAllHighlight();

					return;
				}
			}// highlight checking is over
			highlight = db.getNoteWithSurroundingText(activity.getPage_no(),
					activity.getIdentity(), highlightItem.getSurroundingText());
			// currentStartOffset = startOffset;
			// currentEndOffset = currentStartOffset + selection.length();
			for (HighlightNote item : highlight) {
				int oldIndexStart = item.getStartOffset();
				int oldIndexEnd = item.getStartOffset()
						+ item.getSelectedText().length();
				// currentstart-oldstart -----currentend <---->oldend
				if (currentStartOffset < oldIndexStart
						&& currentEndOffset <= oldIndexEnd) {
					Log.v("oldselection", highlightItem.getSelectedText());
					Log.v("newselection", selection);
					String tempText = selection.substring(0, oldIndexStart
							- currentStartOffset);
					tempText = tempText + highlightItem.getSelectedText();
					db.updateNote(currentStartOffset, tempText,
							item.getIdentity(), item.getNotes());
					activity.removeAllHighlight();

					break;
				}
				 * fixed
				 
				// oldstart<--->currentstart-----oldend----currentend
				else if (currentStartOffset >= oldIndexStart
						&& oldIndexEnd < currentEndOffset) {
					Log.v("oldselection", highlightItem.getSelectedText());
					Log.v("newselection", selection);
					String tempText = item.getSelectedText().substring(0,
							currentStartOffset - oldIndexStart);
					tempText = tempText + selection;

					db.updateNote(currentStartOffset, tempText,
							item.getIdentity(), item.getNotes());
					activity.removeAllHighlight();

					break;

				}
				// oldstart----currentstart-----currentend---oldend
				else if (oldIndexStart > currentStartOffset
						&& currentEndOffset < oldIndexEnd) {
					break;
				} else if ((oldIndexStart == currentStartOffset && currentEndOffset == oldIndexEnd)) {
					break;
				} else if (currentStartOffset < oldIndexStart
						&& currentEndOffset > oldIndexEnd) {
					db.updateNote(currentStartOffset, selection,
							item.getIdentity(), item.getNotes());
					activity.removeAllHighlight();

					break;
				}
			}// note checking is over

		}
	}

	public void notes(String jsonLocation, String selection, int startOffset,
			String surroundingText, String otherNodeSurroundingtext) {
		Log.i("location", jsonLocation);
		try {
			if (jsonLocation != null) {
				JSONObject josnObject = new JSONObject(jsonLocation);
				setLeft(josnObject.getInt("left"));
				setTop(josnObject.getInt("top"));
				this.selection = selection;
				this.surroundingText = surroundingText;
				this.extendSurroundingText = otherNodeSurroundingtext;
				this.startOffset = startOffset;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void startVideoInFullscreen(String location) {
		Intent intent = new Intent(activity, VideoViewActivity.class);

		location = activity.getOpenoebps() + "/" + location;
		Log.v("location", "" + location);
		intent.putExtra("location", location);
		activity.startActivity(intent);
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public String getSurroundingText() {
		return surroundingText;
	}

	public void setSurroundingText(String surroundingText) {
		this.surroundingText = surroundingText;
	}

	public String getExtendSurroundingText() {
		return extendSurroundingText;
	}

	public void setExtendSurroundingText(String extendSurroundingText) {
		this.extendSurroundingText = extendSurroundingText;
	}

	public int getStartOffset() {
		return startOffset;
	}

	public void setStartOffset(int startOffset) {
		this.startOffset = startOffset;
	}

	public String getJsonLocation() {
		return jsonLocation;
	}

	public void setJsonLocation(String jsonLocation) {
		this.jsonLocation = jsonLocation;
	}

}
*/