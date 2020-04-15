package cn.nextop.guava.widgets.combo.model;

import java.util.ArrayList;
import java.util.List;

import cn.nextop.guava.widgets.combo.model.colum.Columns;
import cn.nextop.guava.widgets.combo.model.config.XComboConfig;
import cn.nextop.guava.widgets.combo.model.row.IRow;
import cn.nextop.guava.widgets.combo.model.row.Rows;

/**
 * @author jonny
 */
public class XComboModel {
	//
	private Rows rows;
	private Columns colums;
	private XComboConfig config;
	private boolean enable = true;
	
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
	
	/**
	 * 
	 */
	public List<IRow> getSelection() {
		List<IRow> r = new ArrayList<>();
		for (IRow iRow : rows.getRows()) {
			if(iRow.isSelected()) r.add(iRow);
		}
		return r;
	}
}
