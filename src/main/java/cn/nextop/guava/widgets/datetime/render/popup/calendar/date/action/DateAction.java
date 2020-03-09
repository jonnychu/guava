package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.action;

import static cn.nextop.guava.widgets.datetime.render.util.Faster.getDummyCalendar;
import static cn.nextop.guava.widgets.datetime.render.util.Faster.getXDateTimeModel;

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
		final DummyCalendar dc = getDummyCalendar(datePanel);
		final XDateTimeModel model = getXDateTimeModel(datePanel);
		//
		dc.select(w.getYear(), w.getMonth(), w.getDay());
		model.setTime(dc.getSelectedTime()); return true;
	}
}
