package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.top;

import static com.patrikdufresne.fontawesome.FontAwesome.angle_double_left;
import static com.patrikdufresne.fontawesome.FontAwesome.angle_double_right;
import static com.patrikdufresne.fontawesome.FontAwesome.angle_left;
import static com.patrikdufresne.fontawesome.FontAwesome.angle_right;
import static java.lang.String.valueOf;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.glossary.Type;
import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.top.widgets.MonthWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.top.widgets.YearWidget;
import cn.nextop.guava.widgets.datetime.render.utils.DummyCalendar;

/**
 * @author jonny
 */
public class TopPanel extends AbstractPanel {
	//
	private DatePanel datePanel;
	private DummyCalendar dummyCalendar;
	private YearWidget rollDownYear, rollUpYear, selectYear;
	private MonthWidget rollDownMonth, rollUpMonth, selectMonth;
	
	/**
	 * 
	 */
	public YearWidget getSelectYear() { return selectYear; }
	public MonthWidget getSelectMonth() { return selectMonth; }
	public DatePanel getDatePanel() { return datePanel; }
	
	/**
	 * 
	 */
	public TopPanel(DatePanel datePanel) {
		this.datePanel = datePanel;
		this.dummyCalendar = datePanel.getDummyCalendar();
		add(rollUpMonth = new MonthWidget(this, angle_right, Type.UP));
		add(rollDownMonth = new MonthWidget(this, angle_left, Type.DOWN));
		add(rollUpYear = new YearWidget(this, angle_double_right, Type.UP));
		add(rollDownYear = new YearWidget(this, angle_double_left, Type.DOWN));
		add(selectMonth = new MonthWidget(this, dummyCalendar.getMonthSymbol(), Type.SELECT));
		add(selectYear = new YearWidget(this, valueOf(dummyCalendar.getYear()), Type.SELECT));
	}
	
	@Override
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
		final Rectangle r = getBounds();
		g.setForegroundColor(Colors.COLOR_DARK_GRAY);
		g.drawLine(r.x, r.height - 1, r.x + r.width , r.height - 1);
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		TopPanel parent = (TopPanel)container;
		final Rectangle r = parent.getBounds();
		//
		final int x = r.x, w = r.width, h = r.height;
		final int margin = 2, w1 = 20, h1 = 20, cy = (h - w1) / 2;
		Rectangle r1 = new Rectangle(margin + x, cy, w1, h1); rollDownYear.setBounds(r1);
		Rectangle r2 = new Rectangle(r1.x + w1 , cy, w1, h1); rollDownMonth.setBounds(r2);
		final int mmw = (w - w1 * 4 - margin * 2) / 2;
		Rectangle r3 = new Rectangle(r2.x + w1 , cy, mmw, h1); selectMonth.setBounds(r3);
		Rectangle r4 = new Rectangle(r3.x + mmw, cy, mmw, h1); selectYear.setBounds(r4);
		Rectangle r5 = new Rectangle(r4.x + mmw, cy, w1 , h1); rollUpMonth.setBounds(r5);
		Rectangle r6 = new Rectangle(r5.x + w1 , cy, w1 , h1); rollUpYear.setBounds(r6);
	}
}
