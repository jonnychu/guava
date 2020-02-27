package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.mid.widget;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.render.AbstractWidget;

/**
 * 
 */
public class WeekItem extends AbstractWidget {
	
	public WeekItem(String text) {
		this.text = text;
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		Rectangle r = getBounds();
		g.setForegroundColor(Colors.COLOR_DARK_GRAY);
		Dimension d1 = TextUtilities.INSTANCE.getTextExtents(text, g.getFont());
		g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
	}
}