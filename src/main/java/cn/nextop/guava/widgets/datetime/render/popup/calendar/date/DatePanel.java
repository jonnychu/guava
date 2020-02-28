package cn.nextop.guava.widgets.datetime.render.popup.calendar.date;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.bottom.BottomPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.mid.MidPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.top.TopPanel;
import cn.nextop.guava.widgets.datetime.utils.DummyCalendar;

/**
 * @author jonny
 */
public class DatePanel extends AbstractPanel {
	//
	private TopPanel topPanel;
	private MidPanel midPanel;
	private CalendarPanel calendar;
	private BottomPanel bottomPanel;
	private DummyCalendar dummyCalendar;
	
	/**
	 * 
	 */
	public TopPanel getTopPanel() {	return topPanel; }
	public MidPanel getMidPanel() {	return midPanel; }
	public CalendarPanel getCalendar() { return calendar; }
	public BottomPanel getBottomPanel() { return bottomPanel; }
	public DummyCalendar getDummyCalendar() { return dummyCalendar; }
	
	/**
	 * 
	 */
	public DatePanel(CalendarPanel calendar) {
		this.calendar = calendar;
		//
		XDateTimeModel model = getXDateTimeModel();
		this.dummyCalendar = new DummyCalendar(model.getTime1());
		//
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
	protected void layoutManager(IFigure container) {
		DatePanel parent = (DatePanel)container;
		Rectangle r = parent.getBounds();
		//
		TopPanel topPanel = parent.getTopPanel();
		MidPanel midPanel = parent.getMidPanel();
		BottomPanel bottomPanel = parent.getBottomPanel();
		//
		final int th = 40, bh = 40, mh = r.height - th - bh;
		final int x = r.x, y = r.y, w = r.width;
		Rectangle r1 = new Rectangle(x, y, w, th); topPanel.setBounds(r1);
		Rectangle r2 = new Rectangle(x, r1.height, w, mh); midPanel.setBounds(r2);
		Rectangle r3 = new Rectangle(x, r1.height + r2.height, w, bh); bottomPanel.setBounds(r3);
	}
	
	private XDateTimeModel getXDateTimeModel() {
		return this.calendar.getPopupPanel().getPopup().getDateTime().getModel();
	}
}
