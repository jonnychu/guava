package cn.nextop.guava.widgets.datetime.render.popup.calendar.common.widget;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.datetime.render.AbstractTimeWidget;

/**
 * @author jonny
 */
public class LineWidget extends AbstractTimeWidget {
	
	@Override
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
		final Rectangle r = getBounds();
		g.drawLine(r.x, r.y, r.x + r.width, r.y);
	}
}
