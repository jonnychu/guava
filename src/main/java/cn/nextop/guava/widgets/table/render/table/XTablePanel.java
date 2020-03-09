package cn.nextop.guava.widgets.table.render.table;

import cn.nextop.guava.widgets.table.XTable;
import cn.nextop.guava.widgets.table.render.AbstractScrollPanel;


/**
 * @author jonny
 */
public class XTablePanel extends AbstractScrollPanel {
	//
	protected XTable table;
	protected ContentPanel headerPanel;
	
	/**
	 * 
	 */
	public XTable getXTable() { return table; }

	/**
	 * 
	 */
	public XTablePanel(XTable table) {
		super("table.panel"); this.table = table;
		add(headerPanel = new ContentPanel(this));
		
		setVerticalScrollStep(22);
		setHorizontalScrollBarVisibility(AUTOMATIC);
		setVerticalScrollBarVisibility(AUTOMATIC);
	}
}
