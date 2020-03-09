package cn.nextop.guava.widgets.datetime.render.popup.calendar.month.widget;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.CGUtils;
import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.AbstractWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.action.ShowDateAction;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.action.MonthAction;

/**
 * @author jonny
 */
public class MonthItemWidget extends AbstractWidget {
	//
	private int year, month;
	
	/**
	 * 
	 */
	public int getYear() { return year; }
	public int getMonth() { return month; }

	/**
	 * 
	 */
	public MonthItemWidget(int month, int year, String name, boolean selected) {
		this.text = name; this.year = year; this.month = month; this.selected = selected;
	}
	
	public void setValue(int month, int year, String name, boolean selected) {
		this.text = name; this.year = year; this.month = month; this.selected = selected;
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
				g.setBackgroundColor(Colors.COLOR_WIDGET_MOTION_ENTER);
				g.fillRoundRectangle(r1, arc, arc);
			}
		}
		g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
	}
	
	@Override
	public void handleMouseReleased(MouseEvent event) {
		super.handleMouseReleased(event); 
		MonthPanel mp = (MonthPanel)getParent();
		DatePanel dp = mp.getCalendarPanel().getDatePanel();
		new MonthAction().onAction(mp, this); new ShowDateAction().onAction(dp, null);
	}
}
