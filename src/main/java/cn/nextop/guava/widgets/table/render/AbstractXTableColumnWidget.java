package cn.nextop.guava.widgets.table.render;

import org.eclipse.draw2d.Figure;

import cn.nextop.guava.widgets.table.model.column.Column;

/**
 * @author jonny
 */
public abstract class AbstractXTableColumnWidget extends Figure {
	// Default Value
	protected Column<?> column;
	protected String text = "";
	protected boolean editable = true;
	protected boolean selected = false;
	//
	protected final int margin = 8, arc = 3, oval = 5;
	
	/**
	 * 
	 */
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Column<?> getColumn() {
		return column;
	}

	public void setColumn(Column<?> column) {
		this.column = column;
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
}
