package cn.nextop.guava.widgets.table.builder;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.widgets.table.XTable;
import cn.nextop.guava.widgets.table.model.XTableColumns;
import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.render.panel.content.ContentContentPanel;
import cn.nextop.guava.widgets.table.render.panel.header.HeaderContentPanel;
import cn.nextop.guava.widgets.table.render.widget.internal.column.ColumnWidget;
import cn.nextop.guava.widgets.table.render.widget.internal.row.RowWidget;

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
		XTableModel model = this.table.getModel();
		XTableColumns columns = model.getColumns();
		List<ColumnWidget> all = columns.getColumns();
		
		// all column
		ContentContentPanel content = this.table.getTablePanel().getContent().getContent();
		HeaderContentPanel header = this.table.getTablePanel().getHeader().getHeader();
		for (ColumnWidget cw : all) {
			header.add(cw); 
			RowWidget rw = new RowWidget(); rw.setText("111111");
			content.add(rw);
		}
		return this.table;
	}
}
