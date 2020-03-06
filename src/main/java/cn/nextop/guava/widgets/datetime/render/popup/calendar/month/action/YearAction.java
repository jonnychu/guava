package cn.nextop.guava.widgets.datetime.render.popup.calendar.month.action;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.glossary.PanelType;
import cn.nextop.guava.widgets.datetime.glossary.Type;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;

/**
 * @author jonny
 */
public class YearAction extends AbstractMonthAction {
	//
	private final Type type;
	
	/**
	 * 
	 */
	public YearAction(Type type) {
		this.type = type;
	}

	@Override
	public boolean updateData(IFigure container, IFigure widget) {
		final MonthPanel monthPanel = (MonthPanel)container;
		final DummyCalendar calendar = monthPanel.getDummyCalendarFromMonth();
		//
		if (this.type == Type.SELECT) {	return true; }
		else if(this.type == Type.UP) { calendar.nextYear(); }
		else if(this.type == Type.DOWN) { calendar.prevYear(); } return true;
	}
	
	@Override
	public void onAction(IFigure container, IFigure widget) {
		super.onAction(container, widget);
		final MonthPanel monthPanel = (MonthPanel)container;
		final CalendarPanel calendarPanel = monthPanel.getCalendarPanel();
		if (this.type == Type.SELECT) calendarPanel.panel(PanelType.YEAR);
	}
}
