package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.action;

import static cn.nextop.guava.widgets.datetime.render.util.Faster.getDummyCalendarFromDate;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.glossary.PanelType;
import cn.nextop.guava.widgets.datetime.glossary.Type;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;

/**
 * @author jonny
 */
public class MonthAction extends AbstractDateAction {
	//
	private final Type type;
	
	/**
	 * 
	 */
	public MonthAction(Type type) { this.type = type; }

	@Override
	public boolean updateData(IFigure container, IFigure widget) {
		final DatePanel datePanel = (DatePanel)container;
		final DummyCalendar calendar = getDummyCalendarFromDate(datePanel);
		//
		if (this.type == Type.SELECT) {	return true; }
		else if(this.type == Type.UP) { calendar.nextMonth(); }
		else if(this.type == Type.DOWN) { calendar.prevMonth(); } return true;
	}
	
	@Override
	public void onAction(IFigure container, IFigure widget) {
		super.onAction(container, widget);
		final DatePanel datePanel = (DatePanel)container;
		final CalendarPanel calendarPanel = datePanel.getCalendarPanel();
		if (this.type == Type.SELECT) calendarPanel.panel(PanelType.MONTH);
	}
}
