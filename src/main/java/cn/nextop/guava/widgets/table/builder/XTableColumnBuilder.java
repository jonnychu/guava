package cn.nextop.guava.widgets.table.builder;

import cn.nextop.guava.widgets.table.XTable;
import cn.nextop.guava.widgets.table.model.XTableColumns;
import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.render.table.header.widget.ColumnWidgets;

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
	
	/**
	 * 
	 */
	public XTableColumnBuilder pixel(int pixel) {
		this.column.setWidth(pixel); return this;
	}
	
	public XTableColumnBuilder text(String text) {
		this.column.setText(text); return this;
	}
	

	
	public ColumnWidgets build() {
		XTable table = builder.getXTable();
		XTableModel model = table.getModel();
		XTableColumns columns = model.getColumns();
		//
		columns.addColumns(this.column); return column;
	}
}
