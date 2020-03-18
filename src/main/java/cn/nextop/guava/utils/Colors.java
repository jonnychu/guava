package cn.nextop.guava.utils;

import static org.eclipse.swt.widgets.Display.getDefault;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * @author jonny
 */
public final class Colors {
	//
	protected static final Map<String, Color> COLORS = new HashMap<String, Color>();
	
	//
	public static final Color COLOR_RED = Display.getDefault().getSystemColor(SWT.COLOR_RED);
	public static final Color COLOR_BLUE = Display.getDefault().getSystemColor(SWT.COLOR_BLUE);
	public static final Color COLOR_CYAN = Display.getDefault().getSystemColor(SWT.COLOR_CYAN);
	public static final Color COLOR_GRAY = Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
	public static final Color COLOR_BLACK = Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
	public static final Color COLOR_GREEN = Display.getDefault().getSystemColor(SWT.COLOR_GREEN);
	public static final Color COLOR_WHITE = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
	public static final Color COLOR_YELLOW = Display.getDefault().getSystemColor(SWT.COLOR_YELLOW);
	public static final Color COLOR_MAGENTA = Display.getDefault().getSystemColor(SWT.COLOR_MAGENTA);
	
	//
	public static final Color COLOR_DARK_RED = Display.getDefault().getSystemColor(SWT.COLOR_DARK_RED);
	public static final Color COLOR_DARK_GRAY = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
	public static final Color COLOR_DARK_BLUE = Display.getDefault().getSystemColor(SWT.COLOR_DARK_BLUE);
	public static final Color COLOR_DARK_CYAN = Display.getDefault().getSystemColor(SWT.COLOR_DARK_CYAN);
	public static final Color COLOR_DARK_GREEN = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GREEN);
	public static final Color COLOR_DARK_YELLOW = Display.getDefault().getSystemColor(SWT.COLOR_DARK_YELLOW);
	public static final Color COLOR_DARK_MAGENTA = Display.getDefault().getSystemColor(SWT.COLOR_DARK_MAGENTA);
	
	//
	public static final Color COLOR_LIST_BACKGROUND = getDefault().getSystemColor(SWT.COLOR_LIST_BACKGROUND);
	public static final Color COLOR_WIDGET_FOREGROUND = getDefault().getSystemColor(SWT.COLOR_WIDGET_FOREGROUND);
	public static final Color COLOR_WIDGET_BACKGROUND = getDefault().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);
	public static final Color COLOR_WIDGET_NORMAL_SHADOW = getDefault().getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW);
	
	//
	public static final Color COLOR_LIGHT_GREEN = getColor(96, 255, 96);
	public static final Color COLOR_LIGHT_GRAY = getColor(192, 192, 192);
	public static final Color COLOR_LIGHT_BLUE = getColor(127, 127, 255);
	
	//
	public static final Color COLOR_WIDGET_PRESS = getColor(96 , 96 , 96 );
	public static final Color COLOR_WIDGET_ENTER = getColor(218, 218, 218);
	public static final Color COLOR_WIDGET_THUMB = getColor(205, 205, 205);
	public static final Color COLOR_WIDGET_THUMB_FOCUS = getColor(166, 166, 166);
	
	//
	public static final Color COLOR_WIDGET_SELECTED = getColor(0, 168, 243);
	public static final Color COLOR_WIDGET_MOTION_ENTER = getColor(140, 255, 251);
	
	/**
	 * Color
	 */
	public static final Color getColor(int r, int g, int b) {
		final String key = new StringBuilder(12).append(r).append(":").append(g).append(":").append(b).toString();
		Color c = COLORS.get(key); if (c == null) COLORS.put(key, c = new Color(getDefault(), r, g, b)); return c;
	}
}
