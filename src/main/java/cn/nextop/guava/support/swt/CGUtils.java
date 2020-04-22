package cn.nextop.guava.support.swt;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

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
	
	public static void fillRect(Graphics g, Rectangle r, Color color) {
		g.setBackgroundColor(color); g.fillRectangle(r);
	}
	
	public static void drawRect(Graphics g, Rectangle r, Color color) {
		if(color != null) g.setForegroundColor(color); g.drawRectangle(getBorderRect(r));
	}
	
	public static void drawLineRect(Graphics g, Rectangle r, Color color) {
		g.setForegroundColor(color);
		g.drawLine(r.x , r.y, r.x + r.width, r.y);
		g.drawLine(r.x + r.width, r.y, r.x + r.width, r.y + r.height);
		g.drawLine(r.x, r.y + r.height - 1, r.x + r.width, r.y + r.height - 1);
	}
	
	public static void drawLine(Graphics g, int x1, int y1, int x2, int y2, Color color) {
		g.setForegroundColor(color); g.drawLine(x1, y1, x2, y2);
	}
	
	public static void main(String[] args) {
		Rectangle r = new Rectangle(10, 10, 20, 20);
		System.out.println(CGUtils.scaleRect(r, 2));
	}
}
