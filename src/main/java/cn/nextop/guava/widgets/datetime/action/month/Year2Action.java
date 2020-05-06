package cn.nextop.guava.widgets.datetime.action.month;

import static cn.nextop.guava.support.util.Objects.cast;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.support.glossary.PanelType;
import cn.nextop.guava.widgets.datetime.support.glossary.Type;

/**
 * @author jonny
 */
public class Year2Action extends AbstractMonthAction {
	//
	private final Type type;
	
	/**
	 * 
	 */
	public Year2Action(Type type) {
		this.type = type;
	}

	@Override
	public boolean updateData(IFigure container, IFigure widget) {
		final MonthPanel mp = cast(container);
		DummyCalendar dummyCalendar = mp.getBuilder().getDummyCalendar();
		//
		if (this.type == Type.SELECT) {	return true; }
		else if(this.type == Type.UP) { dummyCalendar.nextYear(); }
		else if(this.type == Type.DOWN) { dummyCalendar.prevYear(); } 
		return true;
	}
	
	@Override
	public void onAction(IFigure container, IFigure widget) {
		super.onAction(container, widget);
		final MonthPanel mp = cast(container);
		final CalendarPanel cp = mp.getBuilder().getCalendarPanel();
		if (this.type == Type.SELECT) cp.panel(PanelType.YEAR);
	}
}
