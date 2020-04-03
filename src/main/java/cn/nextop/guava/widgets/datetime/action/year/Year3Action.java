package cn.nextop.guava.widgets.datetime.action.year;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.YearPanel;
import cn.nextop.guava.widgets.datetime.support.glossary.Type;

/**
 * @author jonny
 */
public class Year3Action extends AbstractYearAction {
	//
	private final Type type;
	
	/**
	 * 
	 */
	public Year3Action(Type type) { this.type = type; }

	@Override
	public boolean updateData(IFigure container, IFigure widget) {
		final YearPanel yp = cast(container);
		DummyCalendar dummyCalendar = yp.getBuilder().getDummyCalendar();
		//
		if (this.type == Type.DOWN) { dummyCalendar.prev12Year(); }
		else if(this.type == Type.UP) { dummyCalendar.next12Year(); }
		else if(this.type == Type.SELECT) { return false; } return true;
	}
}
