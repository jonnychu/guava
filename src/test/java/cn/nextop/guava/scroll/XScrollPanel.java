package cn.nextop.guava.scroll;

import static java.lang.Math.max;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.scroll.bar.XRangeModel;
import cn.nextop.guava.scroll.bar.XScrollBar;
import cn.nextop.guava.widgets.AbstractPanel;

/**
 * @author jonny
 */
public class XScrollPanel extends AbstractPanel {
	
	protected XScrollBar hBar, vBar;
	protected XViewport header, data; 
	protected XRangeModel hRangeModel, vRangeModel;
	
	/**
	 * 
	 */
	public XScrollPanel(String name) {
		super(name);
		this.hBar = new XScrollBar("horz", true); 
		this.vBar = new XScrollBar("vert", false);
		this.hRangeModel = this.hBar.getModel();
		this.vRangeModel = this.vBar.getModel();
		this.header = new XViewport(hRangeModel, null);
		this.data = new XViewport(hRangeModel, vRangeModel);
	}

	@Override
	protected void layoutManager(IFigure container) {
		XScrollPanel parent = (XScrollPanel) container;
		//
		final Rectangle r = parent.getClientArea();
		final int right = vBar.getPreferredSize().width;
		final int bottom = hBar.getPreferredSize().height;
		final Insets i1 = new Insets(0, 0, bottom, right);

		Dimension d1 = r.getSize();
		Dimension d2 = d1.getShrinked(i1.right, i1.bottom);
		d2.width = max(d2.width, 0); d2.height = max(d2.height, 0);
		int wHint = d2.width, hHint = d2.height;

		final Insets i2 = data.getInsets();
		Dimension minSize = new Dimension(i2.getWidth(), i2.getHeight());
		if (data.getContents() != null) {
			if (wHint > -1) wHint = Math.max(0, wHint - i2.getWidth());
			if (hHint > -1) hHint = Math.max(0, hHint - i2.getHeight());
			minSize.expand(data.getContents().getMinimumSize(wHint, hHint));
		}
		Dimension preferred = data.getPreferredSize(wHint, hHint).getCopy();
		preferred.height = minSize.height; preferred.width = minSize.width;

		boolean none = d1.contains(preferred);
		boolean both = !none && preferred.containsProper(d2);
		boolean showV = both || preferred.height > d1.height;
		boolean showH = both || preferred.width > d1.width;
		
		vBar.setVisible(showV); hBar.setVisible(showH);
		if (!showV) i1.right = 0; if (!showH) i1.bottom = 0;
		Rectangle r1 = r.getShrinked(i1); data.setBounds(r1);
		if (showV) vBar.setBounds(new Rectangle(r1.right(), r1.y, i1.right, r1.height));
		if (showH) hBar.setBounds(new Rectangle(r1.x, r1.bottom(), r1.width, i1.bottom));
		
		final int vStepInc = vBar.getStepIncrement();
		int vPageInc = vBar.getModel().getExtent() - vStepInc;
		if (vPageInc < vStepInc) vPageInc = vStepInc; vBar.setPageIncrement(vPageInc);

		final int hStepInc = hBar.getStepIncrement();
		int hPageInc = hBar.getModel().getExtent() - hStepInc;
		if (hPageInc < hStepInc) hPageInc = hStepInc; hBar.setPageIncrement(hPageInc);
	}
}
