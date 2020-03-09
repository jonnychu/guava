package cn.nextop.guava.widgets.table.builder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.widgets.table.XTable;

/**
 * @author jonny
 */
public class XTableBuilder {
	//
	private XTable table;
	
	/**
	 * 
	 */
	public XTable getXTable() { return table; }

	/**
	 * 
	 */
	public XTableBuilder(Composite parent) {
		this(parent, SWT.NONE);
	}
	
	public XTableBuilder(Composite parent, int style) {
		this.table = new XTable(parent, style);
	}
	
	/**
	 * 
	 */
	public XTableColumnBuilder column() {
		return new XTableColumnBuilder(this);
	}
	
	public XTable builder() {
		return this.table;
	}
}
