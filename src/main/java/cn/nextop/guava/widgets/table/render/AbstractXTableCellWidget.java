package cn.nextop.guava.widgets.table.render;

import org.eclipse.draw2d.Figure;

import cn.nextop.guava.widgets.table.builder.internal.XTableFactory;
import cn.nextop.guava.widgets.table.model.column.Column;
import cn.nextop.guava.widgets.table.model.row.IRow;

/**
 * @author jonny
 */
public abstract class AbstractXTableCellWidget extends Figure {
	// Default Value
	protected IRow row;
	protected Column<?> column;
	protected String text = "";
	protected boolean enter = false;
	protected boolean editable = true;
	protected boolean selected = false;
	protected XTableFactory factory;
	//
	protected final int margin = 8, arc = 3, oval = 5;
	
	/**
	 * 
	 */
	public AbstractXTableCellWidget() {
	}
	
	/**
	 * 
	 */
	public IRow getRow() {
		return row;
	}

	public void setRow(IRow row) {
		this.row = row;
	}
	
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
	
	public XTableFactory getFactory() {
		return factory;
	}

	public void setFactory(XTableFactory factory) {
		this.factory = factory;
	}
}
