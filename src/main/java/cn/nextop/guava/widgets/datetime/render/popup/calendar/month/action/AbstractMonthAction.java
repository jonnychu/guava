package cn.nextop.guava.widgets.datetime.render.popup.calendar.month.action;

import static cn.nextop.guava.widgets.datetime.support.tuil.Faster.getDummyCalendar;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.AbstractAction;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.widget.MonthItemWidget;

/**
 * @author jonny
 */
public abstract class AbstractMonthAction extends AbstractAction {
	
	/**
	 * 
	 */
	public void updateUI(IFigure container, IFigure widget) {
		final MonthPanel monthPanel = (MonthPanel)container;
		final DummyCalendar dummyCalendar = getDummyCalendar(monthPanel);
		
		// update top ui
		monthPanel.getSelectYear().setText(dummyCalendar.getYearSymbol());
		
		// update date
		final MonthItemWidget[][] months = monthPanel.getMonths();
		int month = 0; for (int i = 0; i < months.length; i++) {
			for (int j = 0; j < months[i].length; j++) {
				int year = dummyCalendar.getYear();
				String name = dummyCalendar.getMonthSymbol(month);
				boolean selected = dummyCalendar.isSelectedMonth(year, month);
				months[i][j].setValue(month, year, name, selected); month++;
			}
		}
		
		//
		monthPanel.repaint();
	}
}
