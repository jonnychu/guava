package cn.nextop.guava.widgets.table.render;

import org.eclipse.draw2d.Figure;

import cn.nextop.guava.widgets.table.builder.internal.XTableFactory;
import cn.nextop.guava.widgets.table.model.column.Column;
import cn.nextop.guava.widgets.table.render.panel.RowPanel;

/**
 * @author jonny
 */
public abstract class AbstractXTableCellWidget extends Figure {
	// Default Value
	protected Column<?> column;
	protected String text = "";
	protected RowPanel rowPanel;
	protected XTableFactory factory;
	protected boolean enter = false;
	protected boolean editable = true;
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
	
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	
	public RowPanel getRowPanel() {
		return rowPanel;
	}

	public void setRowPanel(RowPanel rowPanel) {
		this.rowPanel = rowPanel;
	}
	
	public XTableFactory getFactory() {
		return factory;
	}

	public void setFactory(XTableFactory factory) {
		this.factory = factory;
	}
}
