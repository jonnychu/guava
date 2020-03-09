package cn.nextop.guava.widgets.datetime.render.popup.calendar.month.action;

import static cn.nextop.guava.widgets.datetime.render.util.Faster.getDummyCalendar;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.glossary.PanelType;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.widget.MonthItemWidget;

/**
 * @author jonny
 */
public class MonthAction extends AbstractMonthAction {

	@Override
	public boolean updateData(IFigure container, IFigure widget) {
		final MonthItemWidget w = (MonthItemWidget)widget;
		final MonthPanel monthPanel = (MonthPanel)container;
		final DummyCalendar calendar = getDummyCalendar(monthPanel);
		//
		calendar.select(w.getYear(), w.getMonth()); return true;
	}
	
	public void onAction(IFigure container, IFigure widget) {
		super.onAction(container, widget);
		final MonthPanel monthPanel = (MonthPanel)container;
		final CalendarPanel calendarPanel = monthPanel.getCalendarPanel();
		//
		calendarPanel.panel(PanelType.DATE); 
	}
}
