package cn.nextop.guava.widgets.datetime.render.popup.calendar.time;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.common.widget.DateButtonWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.common.widget.LineWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.HourPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min.MinPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec.SecPanel;

/**
 * @author jonny
 */
public class TimePanel extends AbstractPanel {
	//
	private CalendarPanel calendar;
	
	//
	private LineWidget line;
	private SecPanel secPanel;
	private MinPanel minPanel;
	private HourPanel hourPanel;
	private DateButtonWidget btnDate;
	
	//
	public SecPanel getSecPanel() { return secPanel; }
	public MinPanel getMinPanel() { return minPanel; }
	public HourPanel getHourPanel() { return hourPanel; }
	public CalendarPanel getCalendarPanel() { return calendar; }

	/**
	 * 
	 */
	public TimePanel(CalendarPanel calendar) {
		super("time"); this.calendar = calendar;
		//
		add(line = new LineWidget());
		add(secPanel = new SecPanel(this));
		add(minPanel = new MinPanel(this));
		add(hourPanel = new HourPanel(this));
		add(btnDate = new DateButtonWidget("Select Date"));
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g); 
		g.setBackgroundColor(Colors.COLOR_WHITE);
		g.fillRectangle(getBounds());
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		TimePanel parent = (TimePanel) container;
		final Rectangle r = parent.getBounds();
		final int x = r.x, y = r.y, w = r.width, h = r.height;
		//
		final int bh = 40, space = 1, h1 = h - bh;
		{
			int w1 = w / 3;
			Rectangle r1 = new Rectangle(x, y, w1, h1); hourPanel.setBounds(r1);
			Rectangle r2 = new Rectangle(r1.x + w1, y, w1, h1); minPanel.setBounds(r2);
			Rectangle r3 = new Rectangle(r2.x + w1, y, w - w1 * 2, h1); secPanel.setBounds(r3);
		}
		
		line.setBounds(new Rectangle(x, y + h1, w, space));
		
		final int sy2 = h1;
		{
			int w1 = w , h2 = bh - 4;
			Rectangle r1 = new Rectangle(x, sy2 + (bh - h2) / 2, w1, h2); btnDate.setBounds(r1);
		}
	}
}
