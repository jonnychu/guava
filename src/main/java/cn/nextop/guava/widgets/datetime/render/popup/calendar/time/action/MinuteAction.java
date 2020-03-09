package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.action;

import static cn.nextop.guava.widgets.datetime.render.util.Faster.getDummyCalendar;
import static cn.nextop.guava.widgets.datetime.render.util.Faster.getXDateTimeModel;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min.widget.MinWidet;

/**
 * @author jonny
 */
public class MinuteAction extends AbstractTimeAction {
	
	@Override
	protected boolean updateData(IFigure container, IFigure widget) {
		final MinWidet w = (MinWidet) widget;
		final TimePanel tp = (TimePanel) container;
		final DummyCalendar dc = getDummyCalendar(tp);
		final XDateTimeModel model = getXDateTimeModel(tp);
		
		//
		dc.selectMintue(w.getMinute()); model.setTime(dc.getSelectedTime()); return true;
	}
}
