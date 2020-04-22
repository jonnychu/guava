package cn.nextop.guava.widgets.table;

import static org.eclipse.swt.SWT.DOUBLE_BUFFERED;

import java.util.List;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.widgets.table.builder.internal.XTableFactory;
import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.model.row.IRow;

/**
 * @author jonny
 */
public class XTable extends Canvas {
	//
	private XTableModel model;
	private LightweightSystem lws;
	private XTableFactory factory;
	
	public XTable(Composite parent) {
		super(parent, DOUBLE_BUFFERED);
		this.model = new XTableModel();
		this.factory = new XTableFactory();
		this.lws = new LightweightSystem(this);
		this.lws.setContents(this.factory.build(this));
	}
	
	public void input(List<IRow> rows) {
		if(rows == null || rows.size() == 0) return;
		model.getRows().setRows(rows); factory.buildData();
	}
	
	/**
	 * 
	 */
	public XTableModel getModel() {
		return model;
	}
	
	public void setModel(XTableModel model) {
		this.model = model;
	}

	public XTableFactory getFactory() {
		return factory;
	}

	public void setFactory(XTableFactory factory) {
		this.factory = factory;
	}
}
