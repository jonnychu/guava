package cn.nextop.guava.widgets.table.render.panel.viewport;

import cn.nextop.guava.draw2d.scroll.XViewport;
import cn.nextop.guava.draw2d.scroll.bar.XRangeModel;

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