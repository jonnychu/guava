package cn.nextop.guava.widgets.table.model.basic.cell;

import org.eclipse.swt.SWT;

/**
 * @author jonny
 */
public class XTableRowCell {
	private String text = "";
	private int width = 30;
	private int height = 22;
	private int align = SWT.CENTER;
	
	/**
	 * 
	 */
	public XTableRowCell() {
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getAlign() {
		return align;
	}

	public void setAlign(int align) {
		this.align = align;
	}
}
