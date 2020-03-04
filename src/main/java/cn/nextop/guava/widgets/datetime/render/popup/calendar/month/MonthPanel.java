package cn.nextop.guava.widgets.datetime.render.popup.calendar.month;

import static com.patrikdufresne.fontawesome.FontAwesome.angle_double_left;
import static com.patrikdufresne.fontawesome.FontAwesome.angle_double_right;
import static java.lang.Math.min;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.glossary.Type;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.common.widget.LineWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.widget.MonthItemWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.widget.OkButtonWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.widget.TimeButtonWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.widget.YearWidget;

/**
 * @author jonny
 */
public class MonthPanel extends AbstractPanel {
	//
	private CalendarPanel calendarPanel;
	private DummyCalendar dummyCalendar;
	//
	private OkButtonWidget btnOk;
	private LineWidget line1, line2;
	private TimeButtonWidget btnTime;
	private MonthItemWidget[][] dates;
	private YearWidget rollDownYear, rollUpYear, selectYear;
	
	/**
	 * 
	 */
	public OkButtonWidget getBtnOk() { return btnOk; }
	public MonthItemWidget[][] getDates() {	return dates; }
	public YearWidget getRollUpYear() { return rollUpYear; }
	public YearWidget getSelectYear() { return selectYear; }
	public TimeButtonWidget getBtnTime() { return btnTime; }
	public YearWidget getRollDownYear() { return rollDownYear; }
	public CalendarPanel getalendarPanel() { return calendarPanel; }
	public DummyCalendar getDummyCalendar() { return dummyCalendar; }
	
	/**
	 * 
	 */
	public MonthPanel(CalendarPanel calendar) {
		this.calendarPanel = calendar;
		this.dates = new MonthItemWidget[4][3];
		final XDateTimeModel model = getXDateTimeModel();
		this.dummyCalendar = new DummyCalendar(model.getTime1());
		// add widgets
		add(line1 = new LineWidget());
		add(line2 = new LineWidget());
		add(btnTime = new TimeButtonWidget("OK"));
		add(btnOk = new OkButtonWidget("Select Time"));
		add(rollUpYear = new YearWidget(angle_double_right, Type.UP));
		add(rollDownYear = new YearWidget(angle_double_left, Type.DOWN));
		add(selectYear = new YearWidget(dummyCalendar.getYearSymbol(), Type.SELECT));
		//
		int month = 0; for (int i = 0; i < dates.length; i++) {
			for (int j = 0; j < dates[i].length; j++) {
				String name = dummyCalendar.getMonthSymbol(month);
				add(dates[i][j] = new MonthItemWidget(month, name)); month++;
			}
		}
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		g.setBackgroundColor(Colors.COLOR_WHITE);
		g.fillRectangle(getBounds());
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		MonthPanel parent = (MonthPanel)container;
		final Rectangle r = parent.getBounds();
		final int x = r.x, y = r.y, w = r.width, h = r.height;
		// h = 40
		final int th = 40, bh = 40, mh = h - th - bh, space = 1;
		{
			final int mgn = 2, w1 = 20, h1 = 20;
			final int y1 = y + (th - w1) / 2, w2 = (w - w1 * 2 - mgn * 2);
			Rectangle r1 = new Rectangle(mgn + x, y1, w1, h1); rollDownYear.setBounds(r1);
			Rectangle r2 = new Rectangle(r1.x + w1, y1, w2, h1); selectYear.setBounds(r2);
			Rectangle r3 = new Rectangle(r2.x + w2, y1, w1, h1); rollUpYear.setBounds(r3);
		}
		
		// h = ?
		final int sy1 = th + space;
		{
			final int w1 = min( mh / 4, w / 3), mgn1 = (w - w1 * 3) / 4, mgn2 = (mh - w1 * 4) / 5 ;
			for (int i = 0; i < dates.length; i++) {
				for (int j = 0; j < dates[i].length; j++) {
					dates[i][j].setBounds(new Rectangle(mgn1 * (j + 1) + x + j * w1, mgn2 * (i + 1) + sy1 + w1 * i, w1, w1));
				}
			}
		}
		
		// h = 40
		final int sy2 = th + mh - space;
		{
			int w1 = w / 2 , h1 = bh - 4;
			Rectangle r1 = new Rectangle(x, sy2 + (bh - h1) / 2, w1, h1); btnOk.setBounds(r1);
			Rectangle r2 = new Rectangle(x + r1.width, sy2 + (bh - h1) / 2, w1, h1); btnTime.setBounds(r2);
		}
		
		// line
		{
			line1.setBounds(new Rectangle(x, th, w, space));
			line2.setBounds(new Rectangle(x, h - bh, w, space));
		}
	}
	
	private XDateTimeModel getXDateTimeModel() {
		return this.calendarPanel.getPopupPanel().getPopup().getDateTime().getModel();
	}
}
