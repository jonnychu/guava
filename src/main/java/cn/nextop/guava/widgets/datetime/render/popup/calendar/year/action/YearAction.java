package cn.nextop.guava.widgets.datetime.render.popup.calendar.year.action;

import static cn.nextop.guava.widgets.datetime.render.util.Faster.getDummyCalendar;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.glossary.Type;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.YearPanel;

/**
 * @author jonny
 */
public class YearAction extends AbstractYearAction {
	//
	private final Type type;
	
	/**
	 * 
	 */
	public YearAction(Type type) { this.type = type; }

	@Override
	public boolean updateData(IFigure container, IFigure widget) {
		final YearPanel yearPanel = (YearPanel)container;
		final DummyCalendar calendar = getDummyCalendar(yearPanel);
		//
		if (this.type == Type.DOWN) { calendar.prev12Year(); }
		else if(this.type == Type.UP) { calendar.next12Year(); }
		else if(this.type == Type.SELECT) { return false; } return true;
	}
}
