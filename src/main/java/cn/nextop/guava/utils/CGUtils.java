package cn.nextop.guava.utils;

import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author jonny
 */
public class CGUtils {
	
	public static Rectangle getBorderRect(Rectangle r) {
		return new Rectangle(r.x, r.y, r.width - 1, r.height - 1);
	}
}
