package cn.nextop.guava.scroll;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.scroll.bar.XRangeModel;
import cn.nextop.guava.scroll.bar.XScrollBar;
import cn.nextop.guava.scroll.viewport.DataViewport;
import cn.nextop.guava.widgets.AbstractPanel;

/**
 * @author jonny
 */
public class XScrollPanel extends AbstractPanel {
	
	protected XScrollBar hBar, vBar;
	protected DataViewport dataViewport; 
	protected XRangeModel horzRangeModel, vertRangeModel;
	
	/**
	 * 
	 */
	public XScrollPanel(String name) {
		super(name);
		this.hBar = new XScrollBar("horz", true); 
		this.vBar = new XScrollBar("vert", false);
		
		this.dataViewport = new DataViewport();
		this.vertRangeModel = this.vBar.getModel();
	}

	@Override
	protected void layoutManager(IFigure container) {
		
	}
}
