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
	private Columns colums;
	private XTableConfig config;
	private boolean enable = true;
	
	/**
	 * 
	 */
	public XTableModel() {
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

	public Columns getColums() {
		return colums;
	}

	public void setColums(Columns colums) {
		this.colums = colums;
	}

	public XTableConfig getConfig() {
		return config;
	}

	public void setConfig(XTableConfig config) {
		this.config = config;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}
