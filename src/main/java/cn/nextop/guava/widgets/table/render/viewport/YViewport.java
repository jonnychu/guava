package cn.nextop.guava.widgets.table.render.viewport;

import cn.nextop.guava.support.draw2d.scroll.bar.XRangeModel;

/**
 * @author jonny
 */
public class YViewport extends XViewport {
	
	/**
	 * 
	 */
	public YViewport(String name, XRangeModel hRangeModel,
			XRangeModel vRangeModel) {
		super(name, hRangeModel, vRangeModel);
	}
	
	@Override
	protected void readjustScrollBars() { } //NOP
}
