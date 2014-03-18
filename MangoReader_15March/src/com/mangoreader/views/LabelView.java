
package com.mangoreader.views;

// Need the following import to get access to the app resources, since this
// class is in a sub-package.
import net.mangoreader.gdx.data.model.Layer;
import net.mangoreader.gdx.data.model.Layer.Style;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;

import com.mangoreader.R;


/**
 * Example of how to write a custom subclass of View. LabelView
 * is used to draw simple text views. Note that it does not handle
 * styled text or right-to-left writing systems.
 *
 */
public class LabelView extends View {
    private TextPaint mTextPaint;
    private String mText = "";
    private int mAscent;
    
    private int left, top;
    private int mWidth, mHeight;
	private StaticLayout mTextLayout;
    
    public void invalidateLayer(Context context, Layer layer){
    	
    	if(layer != null){
    		
    		Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
    		Point size = new Point();
    		display.getSize(size);
    		int width = size.x;
    		int height = size.y;
    		
    		Style style = layer.getStyle(); 
    		
    		if ("left".equals(layer.getAlignment())) {
    			left = 0;
    			top = 100;		
    			mWidth = width / 3;
    		}else if ("right".equals(layer.getAlignment())) {
    			left = (width * 60)/100;
    			top = 100;
    			mWidth = width / 3;
    		}else if ("top".equals(layer.getAlignment())) {
    			left = 0;
    			top = 100;	
    			mWidth = width / 3;
    		}else if ("bottom".equals(layer.getAlignment())) {
    			left = 0;
    			top = (height * 60)/100;	
    			mWidth = width / 3;
    		}else {
    			left = 0;
    			top = 100;
    			if(style != null){
    				mWidth = style.getWidth();
    				mHeight = style.getHeight();
    			}
    		}
    		
    		if(style != null){
				
    			String color = style.getColor();
    			if(color!=null){
    				try{
    				mTextPaint.setColor(Color.parseColor(color));
    				}catch(Exception e){
    					
    				}
    			}
			}
    		
    		mWidth = (mWidth == 0 ? width : mWidth);
    		
    		
    	}
    }
    
    /**
     * Constructor.  This version is only needed if you will be instantiating
     * the object manually (not from a layout XML file).
     * @param context
     */
    public LabelView(Context context) {
        super(context);
        initLabelView();
    }

    /**
     * Construct object, initializing with any attributes we understand from a
     * layout file. These attributes are defined in
     * SDK/assets/res/any/classes.xml.
     * 
     * @see android.view.View#View(android.content.Context, android.util.AttributeSet)
     */
    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLabelView();

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.LabelView);

        CharSequence s = a.getString(R.styleable.LabelView_text);
        if (s != null) {
            setText(s.toString());
        }

        // Retrieve the color(s) to be used for this view and apply them.
        // Note, if you only care about supporting a single color, that you
        // can instead call a.getColor() and pass that to setTextColor().
        setTextColor(a.getColor(R.styleable.LabelView_textColor, 0xFF000000));

        int textSize = a.getDimensionPixelOffset(R.styleable.LabelView_textSize, 0);
        if (textSize > 0) {
            setTextSize(textSize);
        }

        a.recycle();
    }

    private final void initLabelView() {
    	
    	mTextPaint = new TextPaint();
       // mTextPaint = new Paint();
    	mTextLayout = new StaticLayout(mText, mTextPaint, mWidth, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(32);
        mTextPaint.setColor(0xFF000000);
        setPadding(3, 3, 3, 3);
    }

    /**
     * Sets the text to display in this label
     * @param text The text to display. This will be drawn as one line.
     */
    public void setText(String text) {
        mText = text;
        mTextLayout = new StaticLayout(mText, mTextPaint, mWidth, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        requestLayout();
        invalidate();
    }

    /**
     * Sets the text size for this label
     * @param size Font size
     */
    public void setTextSize(int size) {
        mTextPaint.setTextSize(size);
        requestLayout();
        invalidate();
    }

    /**
     * Sets the text color for this label.
     * @param color ARGB value for the text
     */
    public void setTextColor(int color) {
        mTextPaint.setColor(color);
        invalidate();
    }

    /**
     * @see android.view.View#measure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    /**
     * Determines the width of this view
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text
            result = (int) mTextPaint.measureText(mText) + getPaddingLeft()
                    + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize);
            }
        }

        return result;
    }

    /**
     * Determines the height of this view
     * @param measureSpec A measureSpec packed into an int
     * @return The height of the view, honoring constraints from measureSpec
     */
    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        mAscent = (int) mTextPaint.ascent();
        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text (beware: ascent is a negative number)
            result = (int) (-mAscent + mTextPaint.descent()) + getPaddingTop()
                    + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by measureSpec
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * Render the text
     * 
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
       
        //canvas.drawText(mText, left + getPaddingLeft(), top + getPaddingTop() - mAscent, mTextPaint);
     //canvas.save();
     // calculate x and y position where your text will be placed

     canvas.translate(left + getPaddingLeft(), top + getPaddingTop() - mAscent);
     mTextLayout.draw(canvas);
    
     canvas.restore();
    }
}
