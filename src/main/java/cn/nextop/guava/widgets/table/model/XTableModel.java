package cn.nextop.guava.widgets.table.model;

import cn.nextop.guava.widgets.table.model.basic.row.XTableRows;

/**
 * @author jonny
 */
public class XTableModel<T> {
	//
	protected XTableRows<T> rows;
	protected XTableColumns columns;
	
	/**
	 * 
	 */
	public XTableModel() {
		rows = new XTableRows<>();
		columns = new XTableColumns();
	}
	
	/**
	 * 
	 */
	public XTableRows<T> getRows() {
		return rows;
	}

	public void setRows(XTableRows<T> rows) {
		this.rows = rows;
	}
	
	public XTableColumns getColumns() {
		return columns;
	}

	public void setColumns(XTableColumns columns) {
		this.columns = columns;
	}
}
