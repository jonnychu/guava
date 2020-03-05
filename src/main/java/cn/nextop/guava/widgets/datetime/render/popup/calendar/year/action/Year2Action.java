package cn.nextop.guava.widgets.datetime.render.popup.calendar.year.action;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.glossary.PanelType;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.YearPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.widget.YearItemWidget;

/**
 * @author jonny
 */
public class Year2Action extends AbstractYearAction {

	@Override
	public boolean updateData(IFigure container, IFigure widget) {
		final YearItemWidget w = (YearItemWidget)widget;
		final YearPanel yearPanel = (YearPanel)container;
		final DummyCalendar calendar = yearPanel.getDummyCalendar();
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
