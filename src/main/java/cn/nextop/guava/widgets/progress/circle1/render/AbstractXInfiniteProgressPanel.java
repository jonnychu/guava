package cn.nextop.guava.widgets.progress.circle1.render;

import cn.nextop.guava.widgets.AbstractPanel;

/**
 * @author jonny
 */
public abstract class AbstractXInfiniteProgressPanel extends AbstractPanel {
	//
	protected float angle1 = -270f;
	protected int margin = 2, thickness = 10;
	
	/**
	 * 
	 */
	public AbstractXInfiniteProgressPanel(String name) {
		super(name);
	}
}
