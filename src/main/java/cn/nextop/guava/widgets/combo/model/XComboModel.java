package cn.nextop.guava.widgets.combo.model;

import cn.nextop.guava.widgets.combo.model.colum.Columns;
import cn.nextop.guava.widgets.combo.model.config.XComboConfig;
import cn.nextop.guava.widgets.combo.model.row.Rows;

/**
 * @author jonny
 */
public class XComboModel {
	//
	private Rows rows;
	private Columns colums;
	private boolean enable;
	private XComboConfig config;
	
	
	/**
	 * 
	 */
	public XComboModel() {
		this.config = new XComboConfig();
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
	
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	public Columns getColums() {
		return colums;
	}

	public void setColums(Columns colums) {
		this.colums = colums;
	}

	public XComboConfig getXComboConfig() {
		return config;
	}

	public void setXComboConfig(XComboConfig config) {
		this.config = config;
	}
}
