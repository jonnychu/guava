package cn.nextop.guava.widgets.table.render.table.header;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.table.render.AbstractPanel;
import cn.nextop.guava.widgets.table.render.table.XTablePanel;

/**
 * @author jonny
 */
public class HeaderPanel extends AbstractPanel {
	//
	protected XTablePanel tablePanel;
	
	/**
	 * @param name
	 */
	public HeaderPanel(XTablePanel tablePanel) {
		super("table.header");
		this.tablePanel = tablePanel;
	}

	@Override
	protected void layoutManager(IFigure container) {
		HeaderPanel parent = (HeaderPanel) container;
		final Rectangle r = parent.getBounds();
	}
}
