package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.action;

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
		final DummyCalendar dc = datePanel.getDummyCalendarFromDate();
		final XDateTimeModel model = datePanel.getXDateTimeModelFromDate();
		//
		dc.select(w.getYear(), w.getMonth(), w.getDay());
		model.setTime(dc.getSelectTime()); return true;
	}
}
