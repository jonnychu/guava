package cn.nextop.guava.widgets.table.model;

/**
 * @author jonny
 */
public class XTableModel {
	//
	protected XTableRows rows;
	protected XTableColumns columns;
	
	/**
	 * 
	 */
	public XTableModel() {
		rows = new XTableRows();
		columns = new XTableColumns();
	}
	
	/**
	 * 
	 */
	public XTableRows getRows() {
		return rows;
	}

	public void setRows(XTableRows rows) {
		this.rows = rows;
	}

	public XTableColumns getColumns() {
		return columns;
	}

	public void setColumns(XTableColumns columns) {
		this.columns = columns;
	}
	
	
}
