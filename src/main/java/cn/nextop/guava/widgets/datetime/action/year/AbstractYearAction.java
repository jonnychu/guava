package cn.nextop.guava.widgets.datetime.action.year;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;
import static java.lang.Integer.parseInt;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.AbstractAction;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.YearPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.widget.YearItemWidget;

/**
 * @author jonny
 */
public abstract class AbstractYearAction extends AbstractAction {
	
	/**
	 * 
	 */
	public void updateUI(IFigure container, IFigure widget) {
		final YearPanel yp = cast(container);
		final DummyCalendar dummyCalendar = yp.getBuilder().getDummyCalendar();
		
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