package cn.nextop.guava.widgets.datetime.action.time;

import static cn.nextop.guava.support.Objects.cast;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.XDateTimePopup;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min.widget.MinWidet;

/**
 * @author jonny
 */
public class MinuteAction extends AbstractTimeActor {
	
	@Override
	protected boolean updateData(IFigure container, IFigure widget) {
		final MinWidet w = cast(widget);
		final TimePanel tp = cast(container);
		XDateTimePopup popup = tp.getBuilder().getDateTimePopup();
		final XDateTimeModel model = popup.getDateTime().getModel();
		final DummyCalendar dummyCalendar = popup.getDummyCalendar();
		
		//
		dummyCalendar.selectMintue(w.getMinute()); 
		model.setTime(dummyCalendar.getSelectedTime()); return true;
	}
}
