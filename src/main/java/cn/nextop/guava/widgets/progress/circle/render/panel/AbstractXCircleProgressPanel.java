package cn.nextop.guava.widgets.progress.circle.render.panel;

import cn.nextop.guava.widgets.AbstractPanel;

/**
 * @author jonny
 */
public abstract class AbstractXCircleProgressPanel extends AbstractPanel {
	//
	protected float angle1 = -270f;
	protected int margin = 2, thickness = 15;
	
	/**
	 * 
	 */
	public AbstractXCircleProgressPanel(String name) {
		super(name);
	}
}
