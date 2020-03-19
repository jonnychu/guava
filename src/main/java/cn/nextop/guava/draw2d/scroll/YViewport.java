package cn.nextop.guava.draw2d.scroll;

import cn.nextop.guava.draw2d.scroll.bar.XRangeModel;

public class YViewport extends XViewport {

	public YViewport(String name, XRangeModel hRangeModel, XRangeModel vRangeModel) {
		super(name, hRangeModel, vRangeModel);
	}
	
	@Override
	protected void readjustScrollBars() {
		//NOP
	}
}
