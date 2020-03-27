package cn.nextop.guava.widgets.slider.render.panel;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.CGUtils;
import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.slider.XSlider;
import cn.nextop.guava.widgets.slider.builder.XSliderBuilder;
import cn.nextop.guava.widgets.spinner.render.AbstractXSpinnerPanel;
import cn.nextop.guava.widgets.spinner.render.widget.XSpinnerButtonWidget;
import cn.nextop.guava.widgets.spinner.render.widget.XSpinnerTextWidget;

/**
 * @author jonny
 */
public class XSliderPanel extends AbstractXSpinnerPanel {
	//
	protected XSlider slider;
	
	/**
	 * 
	 */
	public XSlider getSlider() { return slider; }
	
	/**
	 * 
	 */
	public XSliderPanel(String name, XSlider slider) {
		super(name); this.slider = slider;
		addMouseMotionListener(new MouseMotionListener.Stub());
	}
	
	@Override
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
		g.setForegroundColor(Colors.COLOR_DARK_GRAY);
		g.drawRectangle(CGUtils.getBorderRect(getBounds()));
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		g.setBackgroundColor(Colors.COLOR_WHITE);
		g.fillRectangle(getBounds());
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		XSliderPanel parent = (XSliderPanel) container;
		XSliderBuilder builder = slider.getBuilder();
	}
}
