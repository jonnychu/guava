package cn.nextop.guava.widgets.datetime.action.year;

import static cn.nextop.guava.support.util.Objects.cast;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.builder.XDateTimePopupBuilder;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.YearPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.widget.YearItemWidget;
import cn.nextop.guava.widgets.datetime.support.glossary.PanelType;

/**
 * @author jonny
 */
public class Year4Action extends AbstractYearAction {

	@Override
	public boolean updateData(IFigure container, IFigure widget) {
		final YearPanel yp = cast(container);
		final YearItemWidget w = cast(widget);
		final DummyCalendar dummyCalendar = yp.getBuilder().getDummyCalendar();
		//
		dummyCalendar.select(w.getYear()); return true;
	}
	
	public void onAction(IFigure container, IFigure widget) {
		super.onAction(container, widget);
		final YearPanel yearPanel = cast(container);
		XDateTimePopupBuilder builder = yearPanel.getBuilder();
		final CalendarPanel calendarPanel = builder.getCalendarPanel();
		//
		calendarPanel.panel(PanelType.MONTH); // switch month panel
	}
}
