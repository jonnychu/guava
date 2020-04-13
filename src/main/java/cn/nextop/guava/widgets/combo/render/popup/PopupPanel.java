package cn.nextop.guava.widgets.combo.render.popup;

import static cn.nextop.guava.support.Objects.cast;
import static java.lang.Math.max;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.support.draw2d.scroll.bar.XRangeModel;
import cn.nextop.guava.support.draw2d.scroll.bar.XScrollBar;
import cn.nextop.guava.widgets.combo.XComboPopup;
import cn.nextop.guava.widgets.combo.model.XComboModel;
import cn.nextop.guava.widgets.combo.model.config.XComboConfig;
import cn.nextop.guava.widgets.combo.render.AbstractComboPanel;
import cn.nextop.guava.widgets.combo.render.popup.viewport.XViewport;
import cn.nextop.guava.widgets.combo.render.popup.viewport.YViewport;

/**
 * @author jonny
 */
public class PopupPanel extends AbstractComboPanel {
	//
	protected XViewport data;
	protected YViewport header;
	protected XComboPopup popup;
	protected XScrollBar hBar, vBar;
	protected XRangeModel hRangeModel, vRangeModel;
	
	//
	public XComboPopup getXComboPopup() {
		return popup;
	}

	/**
	 * 
	 */
	public PopupPanel(String name, XComboPopup popup) {
		super(name); this.popup = popup;
		this.hBar = new XScrollBar("horz", true); 
		this.vBar = new XScrollBar("vert", false);
		this.hRangeModel = this.hBar.getModel();
		this.vRangeModel = this.vBar.getModel();
		this.header = new YViewport("header", hRangeModel, null);
		this.data = new XViewport("data", hRangeModel, vRangeModel);
		
		add(this.data); add(this.header); add(this.hBar); add(this.vBar);
	}
	
	/**
	 * 
	 */
	public void setDataContents(IFigure figure) {
		this.data.setContents(figure);
	}
	
	public void setHeaderContents(IFigure figure) {
		this.header.setContents(figure);
	}
	
	@Override
	public void validate() {
		super.validate(); this.hBar.validate(); this.vBar.validate();
	}
	
	/**
	 * 
	 */
	public void pageUp() {
		if(!this.vBar.isVisible()) return;
		vBar.setValue(vBar.getValue() - vBar.getStepIncrement());
	}
	
	public void pageDown() {
		if(!this.vBar.isVisible()) return;
		vBar.setValue(vBar.getValue() + vBar.getStepIncrement());
	}
	
	/**
	 * 
	 */
	@Override
	protected void layoutManager(IFigure container) {
		PopupPanel parent = cast(container);
		XComboPopup popup = parent.getXComboPopup();
		XComboModel model = popup.getXCombo().getModel();
		final XComboConfig config = model.getXComboConfig();
		
		//
		final Rectangle cArea = parent.getClientArea();
		final int right = vBar.getPreferredSize().width;
		final int bottom = hBar.getPreferredSize().height;
		final Insets i1 = new Insets(0, 0, bottom, right);

		Dimension d1 = cArea.getSize();
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
		Dimension pref = data.getPreferredSize(wHint, hHint).getCopy();
		pref.height = minSize.height; pref.width = minSize.width;

		boolean none = d1.contains(pref);
		boolean both = !none && pref.containsProper(d2);
		boolean showV = both || pref.height > d1.height;
		boolean showH = both || pref.width > d1.width;
		
		vBar.setVisible(showV); hBar.setVisible(showH);
		if (!showV) i1.right = 0; if (!showH) i1.bottom = 0;
		final Rectangle r1 = cArea.getShrinked(i1);
		if(header.getContents() == null) {
			Rectangle r3 = new Rectangle(r1.x, r1.y, r1.width, r1.height); data.setBounds(r3);
		} else {
			Rectangle r2 = new Rectangle(r1.x, r1.y, r1.width, config.getHeaderHeight()); header.setBounds(r2);
			Rectangle r3 = new Rectangle(r1.x, r2.bottom(), r1.width, r1.height - r2.height); data.setBounds(r3);
		}
		
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