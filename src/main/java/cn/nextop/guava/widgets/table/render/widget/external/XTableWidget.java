package cn.nextop.guava.widgets.table.render.widget.external;

import static cn.nextop.guava.support.util.Objects.cast;

import java.util.function.Consumer;

import org.eclipse.draw2d.Figure;

import cn.nextop.guava.widgets.table.model.row.IRow;
import cn.nextop.guava.widgets.table.render.panel.RowPanel;
import cn.nextop.guava.widgets.table.render.widget.DefaultCellWidget;

/**
 * @author jonny
 */
public class XTableWidget extends Figure {
	//
	protected String text = "";
	protected Consumer<IRow> action;
	protected boolean enter, press;
	protected final int arc = 3, offset = 1;
	
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
	
	public IRow getRow() {
		DefaultCellWidget dcw = cast(getParent());
		RowPanel rp = cast(dcw.getParent()); return rp.getRow();
	}
}
