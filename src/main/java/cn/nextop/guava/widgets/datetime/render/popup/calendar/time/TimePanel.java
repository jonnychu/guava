package cn.nextop.guava.widgets.datetime.render.popup.calendar.time;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.HourPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min.MinPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec.SecPanel;

/**
 * @author jonny
 */
public class TimePanel extends AbstractPanel {
	//
	private SecPanel secPanel;
	private MinPanel minPanel;
	private HourPanel hourPanel;
	private CalendarPanel calendar;
	
	//
	public CalendarPanel getCalendarPanel() { return calendar; }

	/**
	 * 
	 */
	public TimePanel(CalendarPanel calendar) {
		super("time");
	}

	@Override
	protected void layoutManager(IFigure container) {
		TimePanel parent = (TimePanel) container;
		final Rectangle r = parent.getBounds();
		final int x = r.x, y = r.y, w = r.width, h = r.height;
		//
		final int bh = 40, w1 = w / 3, h1 = h - bh;
		Rectangle r1 = new Rectangle(x, y, w1, h1); hourPanel.setBounds(r1);
		Rectangle r2 = new Rectangle(x + r1.x, y, w1, h1); minPanel.setBounds(r2);
		Rectangle r3 = new Rectangle(x + r2.x, y, w - w1 * 2, h1); secPanel.setBounds(r3);
	}
}
