package cn.nextop.guava.widgets.table.render.panel.header;

import static cn.nextop.guava.draw2d.scroll.support.glossary.Type.NEVER;

import org.eclipse.draw2d.RangeModel;

import cn.nextop.guava.widgets.table.XTable;
import cn.nextop.guava.widgets.table.render.panel.AbstractScrollPanel;

/**
 * @author jonny
 */
public class HeaderScrollPanel extends AbstractScrollPanel {
	//
	protected XTable table;
	protected HeaderContentPanel content;
	
	/**
	 * 
	 */
	public XTable getXTable() { return table; }
	public HeaderContentPanel getContent() { return content; }
	
	/**
	 * 
	 */
	public HeaderScrollPanel(XTable table, RangeModel horzRangModel, RangeModel vertRangeModel) {
		super("table.panel", NEVER, NEVER, horzRangModel, vertRangeModel); 
		this.table = table; setContents(this.content = new HeaderContentPanel(this));
	}
}
