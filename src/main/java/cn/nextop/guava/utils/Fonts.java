package cn.nextop.guava.utils;

import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

/**
 * @author jonny
 */
public final class Fonts {
	//
	public static final Font SYSTEM = Display.getDefault().getSystemFont();
	
	/**
	 * Font
	 */
	public static Font getSystemFont() {
		return SYSTEM;
	}
	
	/**
	 * 
	 */
	public static final int getHeight(Font font) {
		return font.getFontData()[0].getHeight();
	}
	
	public static final Font bold(final Font font) {
		FontDescriptor fd = FontDescriptor.createFrom(font);
		return fd.setStyle(SWT.BOLD).createFont(Display.getDefault());
	}
	
	public static final Font size(Font font, int delta) {
		FontDescriptor fd = FontDescriptor.createFrom(font);
		int height = fd.getFontData()[0].getHeight() + delta;
		return fd.setHeight(height).createFont(Display.getDefault());
	}
}
