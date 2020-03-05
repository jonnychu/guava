package cn.nextop.guava.widgets.datetime.render.popup.calendar.year.widget;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.CGUtils;
import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.render.AbstractWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.action.ShowMonthAction;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.YearPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.action.Year2Action;

/**
 * @author jonny
 */
public class YearItemWidget extends AbstractWidget {
	//
	private int year;
	
	/**
	 * 
	 */
	public int getYear() { return year; }

	/**
	 * 
	 */
	public YearItemWidget(int year, String name, boolean selected) {
		this.text = name; this.year = year; this.selected = selected;
	}
	
	public void setValue(int year, String name, boolean selected) {
		this.text = name; this.year = year; this.selected = selected;
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		final Rectangle r = getBounds();
		final Rectangle r1 = CGUtils.scaleRect(r, -5);
		Dimension d1 = TextUtilities.INSTANCE.getTextExtents(text, g.getFont());
		if(selected) {
			g.setForegroundColor(Colors.COLOR_WHITE);
			g.setBackgroundColor(Colors.COLOR_LIGHT_BLUE);
			g.fillRoundRectangle(r1, arc, arc);
		} else {
			if(enter) {
				g.setBackgroundColor(Colors.COLOR_CYAN);
				g.fillRoundRectangle(r1, arc, arc);
			}
		}
		g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
	}
	
	@Override
	public void handleMouseReleased(MouseEvent event) {
		super.handleMouseReleased(event); 
		final YearPanel yp = (YearPanel)getParent();
		final MonthPanel mp = yp.getCalendarPanel().getMonthPanel();
		new Year2Action().onAction(yp, this); new ShowMonthAction().onAction(mp, null);
	}
}
