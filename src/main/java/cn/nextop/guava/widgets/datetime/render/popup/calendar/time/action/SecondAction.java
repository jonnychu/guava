package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.action;

import static cn.nextop.guava.widgets.datetime.render.util.Faster.getDummyCalendarFromTime;
import static cn.nextop.guava.widgets.datetime.render.util.Faster.getXDateTimeModelFromTime;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec.widget.SecWidet;

/**
 * @author jonny
 */
public class SecondAction extends AbstractTimeAction {
	
	@Override
	protected boolean updateData(IFigure container, IFigure widget) {
		final SecWidet w = (SecWidet) widget;
		final TimePanel tp = (TimePanel) container;
		final DummyCalendar dc = getDummyCalendarFromTime(tp);
		final XDateTimeModel model = getXDateTimeModelFromTime(tp);
		
		//
		dc.selectSecond(w.getSecond()); model.setTime(dc.getSelectedTime()); return true;
	}
}
