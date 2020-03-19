package cn.nextop.guava.widgets.table;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;

import java.util.List;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.render.panel.XTablePanel;

/**
 * @author jonny
 */
public class XTable extends Canvas {
	//
	private XTableModel<?> model;
	private LightweightSystem lws;
	private XTablePanel tablePanel;
	
	/**
	 * 
	 */
	public XTableModel<?> getModel() { return model; }
	public XTablePanel getTablePanel() { return tablePanel; }
	
	/**
	 * 
	 */
	public XTable(Composite parent, int style) {
		super(parent, SWT.DOUBLE_BUFFERED | style);
		this.setLayout(new FillLayout());
		this.model = new XTableModel<>();
		this.lws = new LightweightSystem(this);
		this.lws.setContents(tablePanel = new XTablePanel("table.panel", this));
	}
	
	/**
	 * 
	 */
	public void inputs(List<?> inputs) {
		model.getRows().reset(cast(inputs));
		this.tablePanel.repaint();
	}
}
