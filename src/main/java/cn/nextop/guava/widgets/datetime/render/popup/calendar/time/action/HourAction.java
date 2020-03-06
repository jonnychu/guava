package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.action;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.widget.HourWidet;

/**
 * @author jonny
 */
public class HourAction extends AbstractTimeAction {
	
	@Override
	protected boolean updateData(IFigure container, IFigure widget) {
		final HourWidet w = (HourWidet) widget;
		final TimePanel tp = (TimePanel) container;
		final DummyCalendar dc = tp.getDummyCalendarFromTime();
		final XDateTimeModel model = tp.getXDateTimeModelFromTime();
		
		//
		
		dc.selectHour(w.getHour()); model.setTime(dc.getSelectedTime()); 
		System.out.println(w.getHour() + "" + dc.getSelectedHour());
		return true;
	}
}
