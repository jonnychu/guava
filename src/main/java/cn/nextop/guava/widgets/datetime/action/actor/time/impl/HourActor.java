package cn.nextop.guava.widgets.datetime.action.actor.time.impl;

import static cn.nextop.guava.support.Objects.cast;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.XDateTimePopup;
import cn.nextop.guava.widgets.datetime.action.actor.time.AbstractTimeActor;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.widget.HourWidet;

/**
 * @author jonny
 */
public class HourActor extends AbstractTimeActor {
	
	@Override
	protected boolean updateData(IFigure container, IFigure widget) {
		final HourWidet w = cast(widget);
		final TimePanel tp = cast(container);
		XDateTimePopup popup = tp.getFactory().getDateTimePopup();
		final XDateTimeModel model = popup.getDateTime().getModel();
		final DummyCalendar dummyCalendar = popup.getDummyCalendar();
		
		//
		dummyCalendar.selectHour(w.getHour()); 
		model.setTime(dummyCalendar.getSelectedTime()); return true;
	}
}
