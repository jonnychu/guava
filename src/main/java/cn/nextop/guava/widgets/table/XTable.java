package cn.nextop.guava.widgets.table;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.render.XTablePanel;

/**
 * @author jonny
 */
public class XTable extends Canvas {
	//
	private XTableModel model;
	private LightweightSystem lws;
	private XTablePanel tablePanel;
	
	/**
	 * 
	 */
	public XTable(Composite parent) {
		super(parent, SWT.DOUBLE_BUFFERED);
		this.model = new XTableModel();
		this.lws = new LightweightSystem(this);
		this.lws.setContents(tablePanel = new XTablePanel());
	}
}
