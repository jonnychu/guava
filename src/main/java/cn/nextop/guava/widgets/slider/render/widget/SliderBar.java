package cn.nextop.guava.widgets.slider.render.widget;

import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.slider.XSlider;
import cn.nextop.guava.widgets.slider.render.AbstractXSliderWidget;

/**
 * @author jonny
 */
public class SliderBar extends AbstractXSliderWidget {
	//
	private XSlider slider;
	
	/**
	 * 
	 */
	public SliderBar(String name, XSlider slider) {
		super(name, false, false); this.slider = slider;
	}

	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		final Rectangle r = getClientArea();
		int x = r.x, y = r.y, w = r.width, h = r.height;
		this.text = String.valueOf((int)slider.getValue());
		int width = (int) (slider.getValue() / slider.getValueRange() * r.width); 
		
		g.setBackgroundColor(Colors.COLOR_WHITE);
		g.fillRectangle(x + width, y, w - width, h);
		
		g.setBackgroundColor(Colors.COLOR_PROGRESS);
		g.fillRectangle(x, y, width, h);
		
		Dimension d1 = INSTANCE.getTextExtents(text, g.getFont());
		g.drawText(this.text, r.x + (w - d1.width) / 2, r.y + (h - d1.height) / 2);
	}
}