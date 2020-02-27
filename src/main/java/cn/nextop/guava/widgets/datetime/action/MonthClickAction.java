package cn.nextop.guava.widgets.datetime.action;

import static java.lang.String.valueOf;

import org.eclipse.draw2d.Figure;

import cn.nextop.guava.widgets.datetime.glossary.Type;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.top.TopPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.top.widgets.MonthWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.top.widgets.YearWidget;
import cn.nextop.guava.widgets.datetime.render.utils.DummyCalendar;

/**
 * @author jonny
 */
public class MonthClickAction {
	//
	private final Type type;
	
	/**
	 * 
	 */
	public MonthClickAction(Type type) {
		this.type = type;
	}
	
	/**
	 * 
	 */
	public void onAction(Figure container) {
		final TopPanel topPanel = (TopPanel)container;
		final DatePanel panel = topPanel.getDatePanel();
		final DummyCalendar calendar = panel.getDummyCalendar();
		
		// get data
		String m = "", y = "";
		if(this.type == Type.DOWN) {
			m = calendar.prevMonth();
			y = valueOf(calendar.getYear());
		} else if(this.type == Type.UP) {
			m = calendar.nextMonth();
			y = valueOf(calendar.getYear());
		}
		
		// update ui
		final YearWidget year = topPanel.getSelectYear();
		final MonthWidget month = topPanel.getSelectMonth();
		year.setText(y); month.setText(m); year.repaint(); month.repaint();
	}
}
