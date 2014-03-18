package net.mangoreader.gdx.view;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class OverlapTester {

	public static boolean overlapRectangles (Rectangle r1, Rectangle r2) {
		if (r1.x < r2.x + r2.width && r1.x + r1.width > r2.x && r1.y < r2.y + r2.height && r1.y + r1.height > r2.y)
			return true;
		else
			return false;
	}

	public static boolean pointInRectangle (Rectangle r, Vector2 p) {
		return r.x <= p.x && r.x + r.width >= p.x && r.y <= p.y && r.y + r.height >= p.y;
	}

	public static boolean pointInRectangle (Rectangle r, float x, float y) {
		//		System.out.println("x"+r.getHeight());
		//		System.out.println("Y"+r.getWidth());
		//		System.out.println("touch"+x);
		//		System.out.println("touch"+y);
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}

	/*	public static boolean pointInHomeLogo (Circle r, Vector2 p) {
		return r.x <= p.x && r.x + r.radius >= p.x && r.y <= p.y && r.y + r.radius >= p.y;
	}
	 */

	public static boolean pointtoplay (Circle c, Vector2 v){


		System.out.println("cy"+c.y);
		System.out.println("vy"+v.y);

		return c.x<=v.x && c.x+c.radius*2>=v.x && c.y <=v.y&&c.y+c.radius*2>=v.y;

	}
	
	public static boolean pointpause (Circle c, Vector2 v){


		System.out.println("cy"+c.y);
		System.out.println("vy"+v.y);

		return c.x<=v.x && c.x+c.radius*2>=v.x && c.y <=v.y&&c.y+c.radius*2>=v.y;

	}
	public static boolean pointInHomeLogo (Circle c, Vector2 v){


		System.out.println("cy"+c.y);
		System.out.println("vy"+v.y);

		return c.x<=v.x && c.x+c.radius*2>=v.x && c.y <=v.y&&c.y+c.radius*2>=v.y;

	}
	
	
	public static boolean pointtomenu (Circle c, Vector2 v){


		System.out.println("cy"+c.y);
		System.out.println("vy"+v.y);

		return c.x<=v.x && c.x+c.radius*2>=v.x && c.y <=v.y&&c.y+c.radius*2>=v.y;

	}


	public static boolean createstory (Rectangle r, float x, float y) {
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}

	public static boolean freestory (Rectangle r, float x, float y) {
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}
	public static boolean store (Rectangle r, float x, float y) {
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}

	public static boolean my_story (Rectangle r, float x, float y) {
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}

	public static boolean readforme (Rectangle r, float x, float y) {
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}
	public static boolean readbyme (Rectangle r, float x, float y) {
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}
	public static boolean playgames (Rectangle r, float x, float y) {
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}
	public static boolean detailimage (Rectangle r, float x, float y) {
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}

	public static boolean nextpage (Rectangle r, float x, float y) {
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}

	public static boolean previouspage (Rectangle r, float x, float y) {
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}

	public static boolean nextpageno (Rectangle r, float x, float y) {
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}

	public static boolean previouspageno (Rectangle r, float x, float y) {
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}
	
	public static boolean bedtime (Rectangle r, float x, float y) {
		return r.x <= x && r.x + r.width >= x && r.y <= y && r.y + r.height >= y;
	}

}
