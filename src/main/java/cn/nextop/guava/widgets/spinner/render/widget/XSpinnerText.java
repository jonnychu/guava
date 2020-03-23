package cn.nextop.guava.widgets.spinner.render.widget;

import org.eclipse.draw2d.Graphics;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.spinner.render.AbstractXSpinnerWidget;

public class XSpinnerText extends AbstractXSpinnerWidget {
	
	/**
	 * 
	 */
	public XSpinnerText(String name) {
		super(name, false, false);
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		g.setBackgroundColor(Colors.COLOR_WHITE);
		g.fillRectangle(getClientArea());
	}
}
