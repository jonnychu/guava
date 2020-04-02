package cn.nextop.guava.widgets.datetime.action.date;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.XDateTimePopup;
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
		final DatePanel dp = cast(container);
		final DateItemWidget w = cast(widget);
		XDateTimePopup popup = dp.getBuilder().getDateTimePopup();
		final XDateTimeModel model = popup.getDateTime().getModel();
		final DummyCalendar dummyCalendar = popup.getDummyCalendar();
		//
		dummyCalendar.select(w.getYear(), w.getMonth(), w.getDay()); 
		model.setTime(dummyCalendar.getSelectedTime()); return true;
	}
}
