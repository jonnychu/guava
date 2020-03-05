package cn.nextop.guava.widgets.datetime.render.popup.calendar.year.action;

import static java.lang.Integer.parseInt;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.AbstractAction;
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
		final YearPanel yearPanel = (YearPanel)container;
		final DummyCalendar dummyCalendar = yearPanel.getDummyCalendar();
		
		// update top ui
		yearPanel.getSelectYear().setText(dummyCalendar.getYearSymbol());
		
		// update year ui
		final String[] years = dummyCalendar.getYears();
		final YearItemWidget[][] yearItems = yearPanel.getYears();
		int index = 0; for (int i = 0; i < yearItems.length; i++) {
			for (int j = 0; j < yearItems[i].length; j++) {
				String name = years[index]; int year = parseInt(name);
				boolean selected = dummyCalendar.isSelectedYear(year);
				yearItems[i][j].setValue(year, name, selected); index++;
			}
		}
		
		//
		yearPanel.repaint();
	}
}
