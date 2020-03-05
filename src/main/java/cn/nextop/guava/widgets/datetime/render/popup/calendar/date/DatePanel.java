package cn.nextop.guava.widgets.datetime.render.popup.calendar.date;

import static com.patrikdufresne.fontawesome.FontAwesome.angle_double_left;
import static com.patrikdufresne.fontawesome.FontAwesome.angle_double_right;
import static com.patrikdufresne.fontawesome.FontAwesome.angle_left;
import static com.patrikdufresne.fontawesome.FontAwesome.angle_right;
import static java.lang.Math.min;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.glossary.Type;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.model.DummyModel;
import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.common.widget.LineWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.DateItemWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.MonthWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.OkButtonWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.TimeButtonWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.WeekItemWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.YearWidget;

/**
 * @author jonny
 */
public class DatePanel extends AbstractPanel {
	//
	private CalendarPanel calendar;
	private DummyCalendar dummyCalendar;
	
	private OkButtonWidget btnOk;
	private WeekItemWidget[] weeks;
	private LineWidget line1, line2;
	private TimeButtonWidget btnTime;
	private DateItemWidget[][] dates;
	private YearWidget rollDownYear, rollUpYear, selectYear;
	private MonthWidget rollDownMonth, rollUpMonth, selectMonth;
	
	/**
	 * 
	 */
	public WeekItemWidget[] getWeeks() {return weeks;}
	public DateItemWidget[][] getDates() {return dates;}
	public OkButtonWidget getBtnOk() {return btnOk;}
	public TimeButtonWidget getBtnTime() {return btnTime;}
	public YearWidget getRollUpYear() {return rollUpYear;}
	public YearWidget getSelectYear() {return selectYear;}
	public CalendarPanel getCalendar() { return calendar; }
	public MonthWidget getRollUpMonth() {return rollUpMonth;}
	public MonthWidget getSelectMonth() {return selectMonth;}
	public YearWidget getRollDownYear() {return rollDownYear;}
	public MonthWidget getRollDownMonth() {return rollDownMonth;}
	public DummyCalendar getDummyCalendar() { return dummyCalendar; }
	
	/**
	 * 
	 */
	public DatePanel(CalendarPanel calendar) {
		this.calendar = calendar;
		this.weeks = new WeekItemWidget[7];
		this.dates = new DateItemWidget[6][7];
		this.dummyCalendar = calendar.getDummyCalendar();
		//
		add(line1 = new LineWidget());add(line2 = new LineWidget());
		add(rollUpMonth = new MonthWidget(angle_right, Type.UP));
		add(rollDownMonth = new MonthWidget(angle_left, Type.DOWN));
		add(rollUpYear = new YearWidget(angle_double_right, Type.UP));
		add(rollDownYear = new YearWidget(angle_double_left, Type.DOWN));
		add(selectYear = new YearWidget(dummyCalendar.getYearSymbol(), Type.SELECT));
		add(selectMonth = new MonthWidget(dummyCalendar.getMonthSymbol(), Type.SELECT));
		
		// week symbols
		String[] symbols = this.dummyCalendar.getWeekSymbols();
		for (int i = 0; i < symbols.length; i++) {
			add(weeks[i] = new WeekItemWidget(symbols[i]));
		}
		
		// day of month
		DummyModel[][] models = this.dummyCalendar.getCalendar();
		for (int i = 0; i < models.length; i++) {
			for (int j = 0; j < models[i].length; j++) {
				final DummyModel dm = models[i][j];
				int year = dm.getYear(), month = dm.getMonth(), day = dm.getDay();
				final boolean now = this.dummyCalendar.isNow(year, month, day);
				final boolean editable = this.dummyCalendar.isCurMonth(year, month);
				final boolean selected = this.dummyCalendar.isSelectedDate(year, month, day);
				add(dates[i][j] = new DateItemWidget(year, month, day, editable, selected, now));
			}
		}
		
		// button
		add(btnTime = new TimeButtonWidget("OK"));
		add(btnOk = new OkButtonWidget("Select Time"));
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
		final Rectangle r = parent.getBounds();
		final int x = r.x, y = r.y, w = r.width, h = r.height;
		
		// h = 40
		final int th = 40, bh = 40, mh = h - th - bh, space = 1;
		{
			final int mgn = 2, w1 = 20, h1 = 20;
			final int y1 = y + (th - w1) / 2, w2 = (w - w1 * 4 - mgn * 2) / 2;
			Rectangle r1 = new Rectangle(mgn + x, y1, w1, h1); rollDownYear.setBounds(r1);
			Rectangle r2 = new Rectangle(r1.x + w1, y1, w1, h1); rollDownMonth.setBounds(r2);
			Rectangle r3 = new Rectangle(r2.x + w1, y1, w2, h1); selectMonth.setBounds(r3);
			Rectangle r4 = new Rectangle(r3.x + w2, y1, w2, h1); selectYear.setBounds(r4);
			Rectangle r5 = new Rectangle(r4.x + w2, y1, w1 , h1); rollUpMonth.setBounds(r5);
			Rectangle r6 = new Rectangle(r5.x + w1, y1, w1 , h1); rollUpYear.setBounds(r6);
		}
		
		// h = ?
		final int sy1 = th + space;
		{
			final int w1 = min( mh / 7, w / 7), mgn1 = (w - w1 * 7) / 8;
			// title
			for (int i = 0; i < weeks.length; i++) {
				weeks[i].setBounds(new Rectangle(mgn1 * (i + 1) + x + i * w1, mgn1 + sy1, w1, w1));
			}
			// 
			int ty = sy1 + w1 + mgn1;
			for (int i = 0; i < dates.length; i++) {
				for (int j = 0; j < dates[i].length; j++) {
					dates[i][j].setBounds(new Rectangle(mgn1 * (j + 1) + x + j * w1, mgn1 * (i + 1) + ty + w1 * i, w1, w1));
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
}
