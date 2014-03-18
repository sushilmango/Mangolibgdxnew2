package net.mangoreader.gdx.utils;

import com.badlogic.gdx.math.Vector2;

public class Alignment { 
	
	public final static int LEFT = 0;
	public final static int RIGHT = 1;
	public final static int TOP = 2;
	public final static int BOTTOM = 3;
	public final static int CENTER = 4;
	
	private Vector2 imagePosVector; 
	private Vector2 txtPosVector;
	
	private Vector2 imageDimVector; 
	
	public Vector2 getImageAlignment(){
		
		return imagePosVector;
	}
	
	public Vector2 getTextAlignment(){
		
		return txtPosVector;
	}
	
	public Vector2 getImageDimVector(){
		
		return imageDimVector;
	}
	
	public Alignment(float camerawidth, float cameraheight, int align) {
		
		imagePosVector = new Vector2();
		
		
		imageDimVector = new Vector2();
		
		switch (align) {
		case LEFT:
			
			txtPosVector = new Vector2();
			
			imagePosVector.x = 0f;
			imagePosVector.y = 0f;
			
			
			txtPosVector.x = 675f;
			txtPosVector.y = 700f;
			
			imageDimVector.x = camerawidth * .65f;
			imageDimVector.y = cameraheight;
			
			break;
		case RIGHT:
			
			txtPosVector = new Vector2();
			imagePosVector.x = 358.4f;
			imagePosVector.y = 0f;
			
			txtPosVector.x = 10f;
			txtPosVector.y = 700f;
			
			imageDimVector.x = camerawidth * .65f;
			imageDimVector.y = cameraheight;
			
			break;
		case TOP:
			
			txtPosVector = new Vector2();
			imagePosVector.x = 0f;
			imagePosVector.y = 268.8f;
			
			txtPosVector.x = 10f;
			txtPosVector.y = 700f;
			
			imageDimVector.x = camerawidth ;
			imageDimVector.y = cameraheight * .65f;
			
			break;
		case BOTTOM:
			
			txtPosVector = new Vector2();
			imagePosVector.x = 0f;
			imagePosVector.y = 0f;
			
			txtPosVector.x = 10f;
			txtPosVector.y = 250f;
			
			imageDimVector.x = camerawidth;
			imageDimVector.y = cameraheight * .65f;
			
			break;

		default:
			
			imagePosVector.x= 0f;
			imagePosVector.y = 0f;
			imageDimVector.x = camerawidth;
			imageDimVector.y = cameraheight;
			
			
			break;
		}
	}
	

	/*public static Vector2 imagepositionright() {

		Vector2 v2 = new Vector2();
		v2.x = 358.4f;
		v2.y = 0f;

		return v2;
	}

	public static Vector2 textpositionright() {

		Vector2 v2 = new Vector2();
		v2.x = 675f;
		v2.y = 700f;

		return v2;

	}

	public static Vector2 imagepositionleft() {

		Vector2 v2 = new Vector2();
		v2.x = 0f;
		v2.y = 0f;
		return v2;
	}

	public static Vector2 textpositionleft() {
		
		Vector2 v2 = new Vector2();
		v2.x = 10f;
		v2.y = 700f;

		return v2;

	}

	public static Vector2 imagepositiontop() {
		Vector2 v2 = new Vector2();
		v2.x = 0f;
		v2.y = 268.8f;
		
		return v2;

	}

	public static Vector2 textpositionBottom() {

		Vector2 v2 = new Vector2();
		v2.x = 10f;
		v2.y = 250f;
		return v2;
	}

	public static Vector2 imagepositionbottom() {

		Vector2 v2 = new Vector2();
		v2.x = 0f;
		v2.y = 0f;

		return v2;

	}

	public static Vector2 textpositionTop() {

		Vector2 v2 = new Vector2();
		v2.x = 10f;
		v2.y = 700f;
		return v2;
	}*/

}
