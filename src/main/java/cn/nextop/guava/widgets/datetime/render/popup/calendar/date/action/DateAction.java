package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.action;

import static cn.nextop.guava.widgets.datetime.render.util.Faster.getDummyCalendarFromDate;
import static cn.nextop.guava.widgets.datetime.render.util.Faster.getXDateTimeModelFromDate;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.DateItemWidget;

/**
 * @author jonny
 */
public class DateAction extends AbstractDateAction {
	
	@Override
	public boolean updateData(IFigure container, IFigure widget) {
		final DateItemWidget w = (DateItemWidget)widget;
		final DatePanel datePanel = (DatePanel)container;
		final DummyCalendar dc = getDummyCalendarFromDate(datePanel);
		final XDateTimeModel model = getXDateTimeModelFromDate(datePanel);
		//
		dc.select(w.getYear(), w.getMonth(), w.getDay());
		model.setTime(dc.getSelectedTime()); return true;
	}
}
