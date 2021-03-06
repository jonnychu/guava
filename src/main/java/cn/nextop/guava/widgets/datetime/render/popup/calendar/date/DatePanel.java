package cn.nextop.guava.widgets.datetime.render.popup.calendar.date;

import static com.patrikdufresne.fontawesome.FontAwesome.angle_double_left;
import static com.patrikdufresne.fontawesome.FontAwesome.angle_double_right;
import static com.patrikdufresne.fontawesome.FontAwesome.angle_left;
import static com.patrikdufresne.fontawesome.FontAwesome.angle_right;
import static java.lang.Math.min;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.support.swt.Colors;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.model.DummyModel;
import cn.nextop.guava.widgets.datetime.render.AbstractTimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.common.widget.LineWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.common.widget.TimeButtonWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.DateItemWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.MonthWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.WeekItemWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.YearWidget;
import cn.nextop.guava.widgets.datetime.support.builder.XDateTimePopupBuilder;
import cn.nextop.guava.widgets.datetime.support.glossary.Type;

/**
 * @author jonny
 */
public class DatePanel extends AbstractTimePanel {
	//
	private WeekItemWidget[] weeks;
	private LineWidget line1, line2;
	private TimeButtonWidget btnTime;
	private DateItemWidget[][] dates;
	private YearWidget rollDownYear, rollUpYear, selectYear;
	private MonthWidget rollDownMonth, rollUpMonth, selectMonth;
	
	/**
	 * 
	 */
	public DateItemWidget[][] getDates() { return dates; }
	public YearWidget getSelectYear() { return selectYear; }
	public MonthWidget getSelectMonth() { return selectMonth; }
	
	/**
	 * 
	 */
	public DatePanel(String name, XDateTimePopupBuilder builder) {
		super(name); this.builder = builder;
		this.weeks = new WeekItemWidget[7];
		this.dates = new DateItemWidget[6][7];
		final DummyCalendar dc = builder.getDummyCalendar();
		//
		add(line1 = new LineWidget());add(line2 = new LineWidget());
		add(rollUpMonth = new MonthWidget(angle_right, Type.UP));
		add(rollDownMonth = new MonthWidget(angle_left, Type.DOWN));
		add(rollUpYear = new YearWidget(angle_double_right, Type.UP));
		add(rollDownYear = new YearWidget(angle_double_left, Type.DOWN));
		add(selectYear = new YearWidget(dc.getYearSymbol(), Type.SELECT));
		add(selectMonth = new MonthWidget(dc.getMonthSymbol(), Type.SELECT));
		
		// week symbols
		String[] symbols = dc.getWeekSymbols();
		for (int i = 0; i < symbols.length; i++) {
			add(weeks[i] = new WeekItemWidget(symbols[i]));
		}
		
		// day of month
		DummyModel[][] models = dc.getCalendar();
		for (int i = 0; i < models.length; i++) {
			for (int j = 0; j < models[i].length; j++) {
				final DummyModel dm = models[i][j];
				int year = dm.getYear(), month = dm.getMonth(), day = dm.getDay();
				final boolean now = dc.isNow(year, month, day);
				final boolean editable = dc.isCurMonth(year, month);
				final boolean selected = dc.isSelectedDate(year, month, day);
				add(dates[i][j] = new DateItemWidget(year, month, day, editable, selected, now));
			}
		}
		
		// button
		add(btnTime = new TimeButtonWidget("Select Time"));
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
			final int mgn = 2, w1 = 20, h1 = 30;
			final int y1 = y + (th - h1) / 2, w2 = (w - w1 * 4 - mgn * 2) / 2;
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
		final int sy2 = th + mh;
		{
			int w1 = w , h1 = bh - 4;
			Rectangle r1 = new Rectangle(x, sy2 + (bh - h1) / 2, w1, h1); btnTime.setBounds(r1);
		}
		
		// line
		{
			line1.setBounds(new Rectangle(x, th, w, space));
			line2.setBounds(new Rectangle(x, h - bh, w, space));
		}
	}
}
