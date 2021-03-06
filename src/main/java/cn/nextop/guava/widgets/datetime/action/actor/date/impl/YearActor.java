package cn.nextop.guava.widgets.datetime.action.actor.date.impl;

import static cn.nextop.guava.support.Objects.cast;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.action.actor.date.AbstractDateActor;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.support.glossary.PanelType;
import cn.nextop.guava.widgets.datetime.support.glossary.Type;

/**
 * @author jonny
 */
public class YearActor extends AbstractDateActor {
	//
	private final Type type;
	
	/**
	 * 
	 */
	public YearActor(Type type) { this.type = type; }

	@Override
	public boolean updateData(IFigure container, IFigure widget) {
		final DatePanel dp = cast(container);
		DummyCalendar dummyCalendar = dp.getFactory().getDummyCalendar();
		//
		if (this.type == Type.SELECT) {	return true; }
		else if(this.type == Type.UP) { dummyCalendar.nextYear(); }
		else if(this.type == Type.DOWN) { dummyCalendar.prevYear(); } 
		return true;
	}
	
	@Override
	public void onAction(IFigure container, IFigure widget) {
		super.onAction(container, widget);
		final DatePanel dp = cast(container);;
		final CalendarPanel cp = dp.getFactory().getCalendarPanel();
		if (this.type == Type.SELECT) cp.panel(PanelType.YEAR);
	}
}
