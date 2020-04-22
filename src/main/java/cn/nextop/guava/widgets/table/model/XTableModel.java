package cn.nextop.guava.widgets.table.model;

import cn.nextop.guava.widgets.table.model.column.Columns;
import cn.nextop.guava.widgets.table.model.config.XTableConfig;
import cn.nextop.guava.widgets.table.model.row.Rows;

/**
 * @author jonny
 */
public class XTableModel {
	//
	private Rows rows;
	private Columns columns;
	private XTableConfig config;
	private boolean enable = true;
	
	/**
	 * 
	 */
	public XTableModel() {
		this.rows = new Rows();
		this.columns = new Columns();
		this.config = new XTableConfig();
	}
	
	/**
	 * 
	 */
	public Rows getRows() {
		return rows;
	}

	public void setRows(Rows rows) {
		this.rows = rows;
	}

	public Columns getColumns() {
		return columns;
	}

	public void setColumns(Columns colums) {
		this.columns = colums;
	}

	public XTableConfig getXTableConfig() {
		return config;
	}

	public void setXTableConfig(XTableConfig config) {
		this.config = config;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}
