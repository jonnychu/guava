package cn.nextop.guava.widgets.table.builder;

import cn.nextop.guava.widgets.table.render.header.widget.ColumnWidgets;

/**
 * @author jonny
 */
public class XTableColumnBuilder {
	//
	protected XTableBuilder builder;
	protected ColumnWidgets column = new ColumnWidgets();
	
	/**
	 * 
	 */
	public XTableColumnBuilder(XTableBuilder builder) {
		this.builder = builder;
	}
	
	//
	public XTableColumnBuilder text(String text) {
		this.column.setText(text); return this;
	}
}
