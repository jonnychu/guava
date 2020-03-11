package cn.nextop.guava.widgets.table.model.basic.column;

import org.eclipse.swt.SWT;

/**
 * @author jonny
 */
public class XTableColumn {
	//
	private String text = "";
	private int width = 30;
	private int height = 26;
	private int weight = 0;
	private int align = SWT.CENTER;
	
	/**
	 * 
	 */
	public XTableColumn() {
	}
	
	/**
	 * 
	 */
	public int getAlign() {
		return align;
	}

	public void setAlign(int align) {
		this.align = align;
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
