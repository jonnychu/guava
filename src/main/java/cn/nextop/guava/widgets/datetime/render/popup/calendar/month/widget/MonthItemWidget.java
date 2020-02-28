package cn.nextop.guava.widgets.datetime.render.popup.calendar.month.widget;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.CGUtils;
import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.render.AbstractWidget;

/**
 * @author jonny
 */
public class MonthItemWidget extends AbstractWidget {
	//
	private int month;
	
	/**
	 * 
	 */
	public int getMonth() { return month; }

	/**
	 * 
	 */
	public MonthItemWidget(int month, String name) {
		this.text = name; this.month = month;
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g); final Rectangle r = getBounds();
		Dimension d1 = TextUtilities.INSTANCE.getTextExtents(text, g.getFont());
		if(selected) {
			g.setBackgroundColor(Colors.COLOR_CYAN);
			g.fillRoundRectangle(CGUtils.scaleRect(r, -5), arc, arc);
		}
		g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
	}
}
