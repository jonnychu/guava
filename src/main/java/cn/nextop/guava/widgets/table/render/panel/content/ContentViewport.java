package cn.nextop.guava.widgets.table.render.panel.content;

import org.eclipse.draw2d.RangeModel;

import cn.nextop.guava.widgets.table.render.panel.XTablePanel;
import cn.nextop.guava.widgets.table.render.panel.common.Viewport;

/**
 * @author jonny
 */
public class ContentViewport extends Viewport {
	//
	protected XTablePanel tablePanel;
	protected ContentContentPanel content;
	
	public XTablePanel getTablePanel() {
		return tablePanel;
	}

	public ContentContentPanel getContent() {
		return content;
	}

	/**
	 * 
	 */
	public ContentViewport(XTablePanel tablePanel, RangeModel hRangeModel, RangeModel vRangeModel) {
		super("header.viewport", hRangeModel, vRangeModel);
		this.tablePanel = tablePanel;
		vRangeModel.addPropertyChangeListener(this);
		hRangeModel.addPropertyChangeListener(this);
		setContents(content = new ContentContentPanel(this));
	}
}
