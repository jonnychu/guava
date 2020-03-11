package cn.nextop.guava.widgets.table.render.table.column;

import org.eclipse.draw2d.Figure;
import org.eclipse.swt.SWT;

/**
 * @author jonny
 */
public abstract class AbstractColumnWidget extends Figure {
	// Default Value
	protected boolean enter = false;
	protected boolean editable = true;
	protected boolean selected = false;
	//
	protected int width  = 30;
	protected int height = 26;
	protected int weight = 0;
	protected String text = "";
	private int align = SWT.CENTER;
	
	/**
	 * 
	 */
	public AbstractColumnWidget() {
	}
	

	/**
	 * 
	 */
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public boolean isEnter() {
		return enter;
	}

	public void setEnter(boolean enter) {
		this.enter = enter;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
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

	public int getAlign() {
		return align;
	}

	public void setAlign(int align) {
		this.align = align;
	}
}
