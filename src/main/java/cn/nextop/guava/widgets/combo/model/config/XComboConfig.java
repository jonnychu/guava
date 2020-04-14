package cn.nextop.guava.widgets.combo.model.config;

import org.eclipse.swt.SWT;

/**
 * @author jonny
 */
public class XComboConfig {
	//
	private boolean header = true;
	private int style = SWT.SINGLE;
	private int itemHeight = 24, headerHeight = 24;
	private int popupWidth = 300, popupHeight = 200;
	
	/**
	 * 
	 */
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}
	
	public boolean hasHeader() {
		return header;
	}

	public void setHeader(boolean header) {
		this.header = header;
	}
	
	public int getPopupWidth() {
		return popupWidth;
	}
	public void setPopupWidth(int popupWidth) {
		this.popupWidth = popupWidth;
	}
	
	public int getItemHeight() {
		return itemHeight;
	}
	public void setItemHeight(int itemHeight) {
		this.itemHeight = itemHeight;
	}
	
	public int getPopupHeight() {
		return popupHeight;
	}
	public void setPopupHeight(int popupHeight) {
		this.popupHeight = popupHeight;
	}
	
	public int getHeaderHeight() {
		return headerHeight;
	}
	public void setHeaderHeight(int headerHeight) {
		this.headerHeight = headerHeight;
	}
}
