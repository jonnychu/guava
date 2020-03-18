package cn.nextop.guava.widgets.table.render.widget.internal.column;

import org.eclipse.draw2d.Figure;

import cn.nextop.guava.widgets.table.model.basic.column.XTableColumn;

/**
 * @author jonny
 */
public abstract class AbstractColumnWidget extends Figure {
	// Default Value
	protected String text;
	protected boolean enter = false;
	protected boolean editable = true;
	protected boolean selected = false;
	//
	protected XTableColumn column;
	
	/**
	 * 
	 */
	public AbstractColumnWidget() {
	}
	
	/**
	 * 
	 */
	public XTableColumn getColumn() {
		return column;
	}

	public void setColumn(XTableColumn column) {
		this.column = column;
	}
}
