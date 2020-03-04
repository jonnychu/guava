package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.action;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.DateItemWidget;

/**
 * @author jonny
 */
public class DayAction extends AbstractAction {
	
	@Override
	protected boolean updateData(IFigure container, IFigure widget) {
		final DateItemWidget w = (DateItemWidget)widget;
		final DatePanel datePanel = (DatePanel)container;
		final DummyCalendar calendar = datePanel.getDummyCalendar();
		
		if(w.isEditable()) return false;
		calendar.setDay(w.getDay());
		calendar.setMonth(w.getMonth());
		calendar.setYear(w.getYear());
		return true;
	}
}
