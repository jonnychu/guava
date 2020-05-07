package cn.nextop.guava.widgets.table;

import static org.eclipse.swt.SWT.DOUBLE_BUFFERED;

import java.util.List;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import cn.nextop.guava.support.Lists;
import cn.nextop.guava.widgets.table.builder.internal.XTableFactory;
import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.model.row.IRow;
import cn.nextop.guava.widgets.table.render.panel.XTablePanel;

/**
 * @author jonny
 */
public class XTable extends Canvas {
	//
	private XTableModel model;
	private XTablePanel panel;
	private LightweightSystem lws;
	private XTableFactory factory;
	
	public XTable(Composite parent) {
		super(parent, DOUBLE_BUFFERED);
		this.model = new XTableModel();
		this.factory = new XTableFactory();
		this.lws = new LightweightSystem(this);
		this.lws.setContents(panel = this.factory.build(this));
		this.addListener(SWT.MouseWheel, new MouseWhellListener());
	}
	
	public void input(List<IRow> rows) {
		if(Lists.isEmpty(rows)) return;
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
	
	/**
	 * 
	 */
	private class MouseWhellListener implements Listener {
		@Override public void handleEvent(Event event) {
			if(event.count > 0) panel.pageUp(); else panel.pageDown();
		}
	}
}
