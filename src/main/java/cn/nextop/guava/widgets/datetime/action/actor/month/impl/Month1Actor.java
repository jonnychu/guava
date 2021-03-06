package cn.nextop.guava.widgets.datetime.action.actor.month.impl;

import static cn.nextop.guava.support.Objects.cast;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.action.actor.month.AbstractMonthActor;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.widget.MonthItemWidget;
import cn.nextop.guava.widgets.datetime.support.glossary.PanelType;

/**
 * @author jonny
 */
public class Month1Actor extends AbstractMonthActor {

	@Override
	public boolean updateData(IFigure container, IFigure widget) {
		final MonthPanel mp = cast(container);
		final MonthItemWidget w = cast(widget);
		DummyCalendar dummyCalendar = mp.getFactory().getDummyCalendar();
		//
		dummyCalendar.select(w.getYear(), w.getMonth()); return true;
	}
	
	public void onAction(IFigure container, IFigure widget) {
		super.onAction(container, widget);
		final MonthPanel mp = cast(container);
		final CalendarPanel cp = mp.getFactory().getCalendarPanel();
		//
		cp.panel(PanelType.DATE); 
	}
}
