package cn.nextop.guava.widgets.table.render.viewport;

import cn.nextop.guava.support.draw2d.scroll.bar.XRangeModel;

/**
 * @author jonny
 */
public class YViewport extends XViewport {

	public YViewport(String name, XRangeModel hRangeModel, XRangeModel vRangeModel) {
		super(name, hRangeModel, vRangeModel);
	}

	@Override
	protected void readjustScrollBars() { 
		if(this.view == null) return;
		int extent2 = getClientArea().height;
		int max2 = this.view.getBounds().height;
		if(this.vRangeModel != null)
			this.vRangeModel.setAll(0, extent2, max2);
	}
}
