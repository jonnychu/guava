package cn.nextop.guava.widgets.table.model;

import cn.nextop.guava.widgets.table.model.basic.column.XTableColumns;
import cn.nextop.guava.widgets.table.model.basic.row.impl.XTableDefaultRows;

/**
 * @author jonny
 */
public class XTableModel<T> {
	//
	protected XTableDefaultRows<T> rows;
	protected XTableColumns columns;
	
	/**
	 * 
	 */
	public XTableModel() {
		rows = new XTableDefaultRows<>();
		columns = new XTableColumns();
	}
	
	/**
	 * 
	 */
	public XTableDefaultRows<T> getRows() {
		return rows;
	}

	public void setRows(XTableDefaultRows<T> rows) {
		this.rows = rows;
	}
	
	public XTableColumns getColumns() {
		return columns;
	}

	public void setColumns(XTableColumns columns) {
		this.columns = columns;
	}
}
