package cn.nextop.guava.widgets.datetime.render.popup.calendar.year.action;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.XDateTimePopup;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.YearPanel;
import cn.nextop.guava.widgets.datetime.support.glossary.Type;

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
		final YearPanel yp = cast(container);
		XDateTimePopup popup = yp.getBuilder().getDateTimePopup();
		final DummyCalendar dummyCalendar = popup.getDummyCalendar();
		//
		if (this.type == Type.DOWN) { dummyCalendar.prev12Year(); }
		else if(this.type == Type.UP) { dummyCalendar.next12Year(); }
		else if(this.type == Type.SELECT) { return false; } return true;
	}
}
