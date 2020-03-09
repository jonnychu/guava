package cn.nextop.guava.widgets.table.render;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.table.AbstractPanel;
import cn.nextop.guava.widgets.table.XTable;


/**
 * @author jonny
 */
public class XTablePanel extends AbstractPanel {
	//
	protected XTable table;
	
	/**
	 * 
	 */
	public XTable getXTable() { return table; }

	/**
	 * 
	 */
	public XTablePanel(XTable table) {
		super("table.panel");
	}

	@Override
	protected void layoutManager(IFigure container) {
		XTablePanel parent = (XTablePanel) container;
		final Rectangle r = parent.getBounds();
		
	}
}
