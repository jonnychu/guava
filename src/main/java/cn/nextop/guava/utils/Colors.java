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
	public static final Color COLOR_BLACK = Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
	public static final Color COLOR_GREEN = Display.getDefault().getSystemColor(SWT.COLOR_GREEN);
	public static final Color COLOR_WHITE = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
	public static final Color COLOR_DARK_RED = Display.getDefault().getSystemColor(SWT.COLOR_DARK_RED);
	public static final Color COLOR_DARK_GRAY = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
	public static final Color COLOR_LIST_BACKGROUND = getDefault().getSystemColor(SWT.COLOR_LIST_BACKGROUND);
	public static final Color COLOR_WIDGET_FOREGROUND = getDefault().getSystemColor(SWT.COLOR_WIDGET_FOREGROUND);
	public static final Color COLOR_WIDGET_BACKGROUND = getDefault().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);
	public static final Color COLOR_WIDGET_NORMAL_SHADOW = getDefault().getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW);
	
	//
	public static final Color black = getColor(0, 0, 0);
	public static final Color red = getColor(255, 0, 0);
	public static final Color blue = getColor(0, 0, 255);
	public static final Color green = getColor(0, 255, 0);
	public static final Color cyan = getColor(0, 255, 255);
	public static final Color gray = getColor(128, 128, 128);
	public static final Color orange = getColor(255, 196, 0);
	public static final Color yellow = getColor(255, 255, 0);
	public static final Color darkBlue = getColor(0, 0, 127);
	public static final Color white = getColor(255, 255, 255);
	public static final Color darkGray = getColor(64, 64, 64);
	public static final Color darkGreen = getColor(0, 127, 0);
	public static final Color lightGreen = getColor(96, 255, 96);
	public static final Color lightGray = getColor(192, 192, 192);
	public static final Color lightBlue = getColor(127, 127, 255);
	
	/**
	 * Color
	 */
	public static final Color getColor(int r, int g, int b) {
		final String key = new StringBuilder(12).append(r).append(":").append(g).append(":").append(b).toString();
		Color c = COLORS.get(key); if (c == null) COLORS.put(key, c = new Color(getDefault(), r, g, b)); return c;
	}
}
