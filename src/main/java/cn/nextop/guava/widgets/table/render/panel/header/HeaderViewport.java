package cn.nextop.guava.widgets.table.render.panel.header;

import org.eclipse.draw2d.RangeModel;

import cn.nextop.guava.widgets.table.render.panel.XTablePanel;
import cn.nextop.guava.widgets.table.render.panel.common.Viewport;

/**
 * @author jonny
 */
public class HeaderViewport extends Viewport {
	//
	protected XTablePanel tablePanel;
	protected HeaderContentPanel header;
	
	public XTablePanel getTablePanel() {
		return tablePanel;
	}
	
	public HeaderContentPanel getHeader() {
		return header;
	}

	/**
	 * 
	 */
	public HeaderViewport(XTablePanel tablePanel, RangeModel hRangeModel, RangeModel vRangeModel) {
		super("header.viewport", hRangeModel, vRangeModel);
		this.tablePanel = tablePanel;
		vRangeModel.addPropertyChangeListener(this);
		hRangeModel.addPropertyChangeListener(this);
		setContents(this.header = new HeaderContentPanel(this));
	}
}
