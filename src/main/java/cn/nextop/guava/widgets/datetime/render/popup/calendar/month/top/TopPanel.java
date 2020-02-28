package cn.nextop.guava.widgets.datetime.render.popup.calendar.month.top;

import static com.patrikdufresne.fontawesome.FontAwesome.angle_double_left;
import static com.patrikdufresne.fontawesome.FontAwesome.angle_double_right;
import static java.lang.String.valueOf;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.datetime.glossary.Type;
import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.top.widget.YearWidget;
import cn.nextop.guava.widgets.datetime.utils.DummyCalendar;

/**
 * @author jonny
 */
public class TopPanel extends AbstractPanel {
	//
	private MonthPanel monthPanel;
	private DummyCalendar dummyCalendar;
	private YearWidget rollDownYear, rollUpYear, selectYear;
	
	/**
	 * 
	 */
	public YearWidget getSelectYear() { return selectYear; }
	public MonthPanel getMonthPanel() { return monthPanel; }

	/**
	 * 
	 */
	public TopPanel(MonthPanel monthPanel) {
		this.monthPanel = monthPanel;
		this.dummyCalendar = monthPanel.getDummyCalendar();
		add(rollUpYear = new YearWidget(this, angle_double_right, Type.UP));
		add(rollDownYear = new YearWidget(this, angle_double_left, Type.DOWN));
		add(selectYear = new YearWidget(this, valueOf(dummyCalendar.getYear()), Type.SELECT));
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		TopPanel parent = (TopPanel)container;
		final Rectangle r = parent.getBounds();
		//
		final int x = r.x, w = r.width, h = r.height;
		final int margin = 2, w1 = 20, h1 = 20, cy = (h - w1) / 2;
		Rectangle r1 = new Rectangle(margin + x, cy, w1, h1); rollDownYear.setBounds(r1);
		final int mmw = (w - w1 * 2 - margin * 2);
		Rectangle r2 = new Rectangle(r1.x + w1, cy, mmw, h1); selectYear.setBounds(r2);
		Rectangle r3 = new Rectangle(r2.x + mmw, cy, w1 , h1); rollUpYear.setBounds(r3);
	}
}
