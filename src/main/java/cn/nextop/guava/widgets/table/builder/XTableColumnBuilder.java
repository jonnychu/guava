package cn.nextop.guava.widgets.table.builder;

import cn.nextop.guava.widgets.table.XTable;
import cn.nextop.guava.widgets.table.model.XTableColumns;
import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.model.basic.column.XTableColumn;
import cn.nextop.guava.widgets.table.support.property.impl.XProperty;

/**
 * @author jonny
 */
public class XTableColumnBuilder {
	//
	protected XTableColumn column;
	protected XTableBuilder builder;
	
	/**
	 * 
	 */
	public XTableColumnBuilder(XTableBuilder builder) {
		this.builder = builder;
		this.column = new XTableColumn();
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
	
	public XTableColumnBuilder property(Class<?> clazz, String name) {
		this.column.setProperty(new XProperty<>(clazz, name)); return this;
	}
	
	public XTableColumn build() {
		XTable table = builder.getXTable();
		XTableModel<?> model = table.getModel();
		XTableColumns columns = model.getColumns();
		//
		columns.addColumns(this.column); return column;
	}
}
