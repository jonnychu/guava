package cn.nextop.guava.widgets.datetime.action.actor.date.impl;

import static cn.nextop.guava.support.Objects.cast;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.XDateTimePopup;
import cn.nextop.guava.widgets.datetime.action.actor.date.AbstractDateActor;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.DateItemWidget;

/**
 * @author jonny
 */
public class DateActor extends AbstractDateActor {
	
	@Override
	public boolean updateData(IFigure container, IFigure widget) {
		final DatePanel dp = cast(container);
		final DateItemWidget w = cast(widget);
		XDateTimePopup popup = dp.getFactory().getDateTimePopup();
		final XDateTimeModel model = popup.getDateTime().getModel();
		final DummyCalendar dummyCalendar = popup.getDummyCalendar();
		//
		dummyCalendar.select(w.getYear(), w.getMonth(), w.getDay()); 
		model.setTime(dummyCalendar.getSelectedTime()); return true;
	}
}
