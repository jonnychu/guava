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
//		XTableModel model = this.table.getModel();
//		XTableColumns columns = model.getColumns();
//		List<XTableColumn> all = columns.getColumns();
//		XTablePanel tablePanel = this.table.getTablePanel();
//		HeaderContentPanel header = tablePanel.getHeader().getHeader();
//		ContentContentPanel content = tablePanel.getContent().getContent();
//		for (XTableColumn m : all) {
//			header.add(new ColumnWidget(m)); 
//		}
		return this.table;
	}
}
