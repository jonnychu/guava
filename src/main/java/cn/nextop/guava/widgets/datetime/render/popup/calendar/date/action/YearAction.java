package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.action;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.glossary.PanelType;
import cn.nextop.guava.widgets.datetime.glossary.Type;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;

/**
 * @author jonny
 */
public class YearAction extends AbstractDateAction {
	//
	private final Type type;
	
	/**
	 * 
	 */
	public YearAction(Type type) {
		this.type = type;
	}

	@Override
	public boolean updateData(IFigure container, IFigure widget) {
		final DatePanel datePanel = (DatePanel)container;
		final DummyCalendar calendar = datePanel.getDummyCalendar();
		if (this.type == Type.SELECT) {	return true; }
		else if(this.type == Type.UP) { calendar.nextYear(); }
		else if(this.type == Type.DOWN) { calendar.prevYear(); } 
		return true;
	}
	
	@Override
	public void onAction(IFigure container, IFigure widget) {
		super.onAction(container, widget);
		final DatePanel datePanel = (DatePanel)container;
		final CalendarPanel calendarPanel = datePanel.getCalendar();
		if (this.type == Type.SELECT) calendarPanel.panel(PanelType.YEAR);
	}
}
