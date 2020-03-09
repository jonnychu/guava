package cn.nextop.guava.widgets.table;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.text.TextPanel;
import cn.nextop.guava.widgets.table.model.XTableModel;

/**
 * @author jonny
 */
public class XTable extends Canvas {
	//
	private XTableModel model;
	private LightweightSystem lws;
	
	/**
	 * 
	 */
	public XTable(Composite parent) {
		super(parent, SWT.DOUBLE_BUFFERED);
		this.model = new XTableModel();
		this.lws = new LightweightSystem(this);
//		this.lws.setContents(textPanel = new TextPanel(this));
	}

}
