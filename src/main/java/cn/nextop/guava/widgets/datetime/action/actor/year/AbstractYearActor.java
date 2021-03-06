package cn.nextop.guava.widgets.datetime.action.actor.year;

import static cn.nextop.guava.support.Objects.cast;
import static java.lang.Integer.parseInt;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.AbstractActor;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.YearPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.widget.YearItemWidget;

/**
 * @author jonny
 */
public abstract class AbstractYearActor extends AbstractActor {
	
	/**
	 * 
	 */
	public void updateUI(IFigure container, IFigure widget) {
		final YearPanel yp = cast(container);
		final DummyCalendar dummyCalendar = yp.getFactory().getDummyCalendar();
		
		// update top ui
		yp.getYearWidget().setText(dummyCalendar.getYearSymbol());
		
		// update year ui
		final String[] years = dummyCalendar.getYears();
		YearItemWidget[][] yearItems = yp.getYearsWidget();
		int index = 0; for (int i = 0; i < yearItems.length; i++) {
			for (int j = 0; j < yearItems[i].length; j++) {
				String name = years[index]; int year = parseInt(name);
				boolean selected = dummyCalendar.isSelectedYear(year);
				yearItems[i][j].setValue(year, name, selected); index++;
			}
		}
		
		//
		yp.repaint();
	}
}
