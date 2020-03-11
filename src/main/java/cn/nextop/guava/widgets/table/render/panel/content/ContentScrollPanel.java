package cn.nextop.guava.widgets.table.render.panel.content;

import static cn.nextop.guava.draw2d.scroll.support.glossary.Type.NEVER;

import org.eclipse.draw2d.RangeModel;

import cn.nextop.guava.widgets.table.XTable;
import cn.nextop.guava.widgets.table.render.panel.AbstractScrollPanel;


/**
 * @author jonny
 */
public class ContentScrollPanel extends AbstractScrollPanel {
	//
	protected XTable table;
	protected ContentContentPanel content;
	
	/**
	 * 
	 */
	public XTable getXTable() { return table; }
	public ContentContentPanel getContent() { return content; }
	
	/**
	 * 
	 */
	public ContentScrollPanel(XTable table, RangeModel horzRangModel, RangeModel vertRangeModel) {
		super("content.panel", NEVER, NEVER, horzRangModel, vertRangeModel);
		this.table = table; setContents(this.content = new ContentContentPanel(this));
	}
}
