package cn.nextop.guava.widgets.datetime.render.popup.calendar.year.action;

import static cn.nextop.guava.widgets.datetime.support.tuil.Faster.getDummyCalendar;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.YearPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.widget.YearItemWidget;
import cn.nextop.guava.widgets.datetime.support.glossary.PanelType;

/**
 * @author jonny
 */
public class Year2Action extends AbstractYearAction {

	@Override
	public boolean updateData(IFigure container, IFigure widget) {
		final YearItemWidget w = (YearItemWidget)widget;
		final YearPanel yearPanel = (YearPanel)container;
		final DummyCalendar calendar = getDummyCalendar(yearPanel);
		//
		calendar.select(w.getYear()); return true;
	}
	
	public void onAction(IFigure container, IFigure widget) {
		super.onAction(container, widget);
		final YearPanel yearPanel = (YearPanel)container;
		final CalendarPanel calendarPanel = yearPanel.getCalendarPanel();
		//
		calendarPanel.panel(PanelType.MONTH); // switch month panel
	}
}
