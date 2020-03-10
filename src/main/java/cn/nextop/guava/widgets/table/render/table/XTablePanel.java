package cn.nextop.guava.widgets.table.render.table;

import cn.nextop.guava.widgets.table.XTable;
import cn.nextop.guava.widgets.table.render.AbstractScrollPanel;


/**
 * @author jonny
 */
public class XTablePanel extends AbstractScrollPanel {
	//
	protected XTable table;
	protected ContentPanel contentPanel;
	
	/**
	 * 
	 */
	public ContentPanel getContentPanel() {
		return contentPanel;
	}
	
	/**
	 * 
	 */
	public XTable getXTable() { return table; }

	/**
	 * 
	 */
	public XTablePanel(XTable table) {
		super("table.panel"); this.table = table;
		
		setVerticalScrollStep(22);
		setContents(contentPanel = new ContentPanel(this));
	}
}
