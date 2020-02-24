package cn.nextop.guava.widgets.datetime.render.popup.calendar.date;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.part.BottomPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.part.MidPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.part.TopPanel;

public class DatePanel extends Figure {
	//
	private Layout layout;
	private TopPanel topPanel;
	private MidPanel midPanel;
	private CalendarPanel calendar;
	private BottomPanel bottomPanel;
	
	/**
	 * 
	 */
	public TopPanel getTopPanel() {	return topPanel; }
	public MidPanel getMidPanel() {	return midPanel; }
	public CalendarPanel getCalendar() { return calendar; }
	public BottomPanel getBottomPanel() { return bottomPanel; }

	/**
	 * 
	 */
	public DatePanel(CalendarPanel calendar) {
		this.calendar = calendar;
		this.layout = new Layout();
		add(topPanel = new TopPanel(this));
		add(midPanel = new MidPanel(this));
		add(bottomPanel = new BottomPanel(this));
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		g.setBackgroundColor(Colors.COLOR_WHITE);
		g.fillRectangle(getBounds());
	}
	
	@Override
	protected void paintChildren(Graphics g) {
		super.paintChildren(g); this.layout.layout(this);
	}
	
	/**
	 * 
	 */
	protected class Layout {
		//
		public void layout(DatePanel parent) {
			Rectangle r = parent.getClientArea();
			//
			TopPanel topPanel = parent.getTopPanel();
			MidPanel midPanel = parent.getMidPanel();
			BottomPanel bottomPanel = parent.getBottomPanel();
			//
			final int th = 30, bh = 30, mh = r.height - th - bh;
			final int x = r.x, y = r.y, w = r.width, h = r.height;
			topPanel.setBounds(new Rectangle(x, y, w, th));
			midPanel.setBounds(new Rectangle(x, y, w, mh));
			bottomPanel.setBounds(new Rectangle(x, h - th - bh, w, bh));
		}
	}
}
