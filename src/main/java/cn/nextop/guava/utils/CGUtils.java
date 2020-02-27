package cn.nextop.guava.utils;

import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author jonny
 */
public class CGUtils {
	//
	public static Rectangle getBorderRect(Rectangle r) {
		return new Rectangle(r.x, r.y, r.width - 1, r.height - 1);
	}
	
	public static Rectangle scaleRect(Rectangle r, int p) {
		int cx = r.x + r.width / 2, cy = r.y + r.height / 2;
		return new Rectangle(cx - r.width / 2 - p, cy - r.height / 2 - p, r.width + 2 * p, r.height + 2 * p);
	}
	
	public static void main(String[] args) {
		Rectangle r = new Rectangle(10, 10, 20, 20);
		System.out.println(CGUtils.scaleRect(r, 2));
	}
}
