package cn.nextop.guava.widgets.datetime.render.popup.calendar.month.action;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.XDateTimePopup;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.widget.MonthItemWidget;
import cn.nextop.guava.widgets.datetime.support.glossary.PanelType;

/**
 * @author jonny
 */
public class MonthAction extends AbstractMonthAction {

	@Override
	public boolean updateData(IFigure container, IFigure widget) {
		final MonthPanel mp = cast(container);
		final MonthItemWidget w = cast(widget);
		XDateTimePopup popup = mp.getBuilder().getDateTimePopup();
		final DummyCalendar dummyCalendar = popup.getDummyCalendar();
		//
		dummyCalendar.select(w.getYear(), w.getMonth()); return true;
	}
	
	public void onAction(IFigure container, IFigure widget) {
		super.onAction(container, widget);
		final MonthPanel mp = cast(container);
		final CalendarPanel cp = mp.getBuilder().getCalendarPanel();
		//
		cp.panel(PanelType.DATE); 
	}
}
