package cn.nextop.guava.widgets.table.render.widget.external;

import java.util.function.Consumer;

import org.eclipse.draw2d.Figure;

import cn.nextop.guava.widgets.table.model.row.IRow;

/**
 * @author jonny
 */
public class XTableWidget extends Figure {
	//
	protected IRow row;
	protected String text = "";
	protected final int arc = 3, offset = 1;
	protected Consumer<IRow> action;
	protected boolean enter, press;
	
	/**
	 * 
	 */
	public XTableWidget text(String text) {
		this.text = text; return this;
	}
	
	public XTableWidget action(Consumer<IRow> action) {
		this.action = action; return this;
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

	public Consumer<IRow> getAction() {
		return action;
	}

	public void setAction(Consumer<IRow> action) {
		this.action = action;
	}
}
