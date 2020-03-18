package cn.nextop.guava.scroll;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.AbstractPanel;

/**
 * @author jonny
 */
public class XScrollPanel extends AbstractPanel {
	
	protected XScrollBar hBar, vBar;
	
	/**
	 * 
	 */
	public XScrollPanel(String name) {
		super(name);
		this.hBar = new XScrollBar("horz", true); 
		this.vBar = new XScrollBar("vert", false);
	}

	@Override
	protected void layoutManager(IFigure container) {
		
	}
}
