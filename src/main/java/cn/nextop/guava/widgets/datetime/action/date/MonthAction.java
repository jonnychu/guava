package cn.nextop.guava.widgets.datetime.action.date;

import static cn.nextop.guava.support.Objects.cast;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.support.glossary.PanelType;
import cn.nextop.guava.widgets.datetime.support.glossary.Type;

/**
 * @author jonny
 */
public class MonthAction extends AbstractDateAction {
	//
	private final Type type;
	
	/**
	 * 
	 */
	public MonthAction(Type type) { this.type = type; }

	@Override
	public boolean updateData(IFigure container, IFigure widget) {
		final DatePanel dp = cast(container);
		DummyCalendar dummyCalendar = dp.getBuilder().getDummyCalendar();
		//
		if (this.type == Type.SELECT) {	return true; }
		else if(this.type == Type.UP) { dummyCalendar.nextMonth(); }
		else if(this.type == Type.DOWN) { dummyCalendar.prevMonth(); }
		return true;
	}
	
	@Override
	public void onAction(IFigure container, IFigure widget) {
		super.onAction(container, widget);
		final DatePanel dp = cast(container);
		CalendarPanel cp = dp.getBuilder().getCalendarPanel();
		if(this.type == Type.SELECT) cp.panel(PanelType.MONTH);
	}
}
