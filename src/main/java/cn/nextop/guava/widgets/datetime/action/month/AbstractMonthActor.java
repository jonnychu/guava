package cn.nextop.guava.widgets.datetime.action.month;

import static cn.nextop.guava.support.Objects.cast;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.AbstractActor;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.widget.MonthItemWidget;

/**
 * @author jonny
 */
public abstract class AbstractMonthActor extends AbstractActor {
	
	/**
	 * 
	 */
	public void updateUI(IFigure container, IFigure widget) {
		final MonthPanel mp = cast(container);
		DummyCalendar dummyCalendar = mp.getBuilder().getDummyCalendar();
		
		// update top ui
		mp.getSelectYear().setText(dummyCalendar.getYearSymbol());
		
		// update date
		final MonthItemWidget[][] months = mp.getMonths();
		int month = 0; for (int i = 0; i < months.length; i++) {
			for (int j = 0; j < months[i].length; j++) {
				int year = dummyCalendar.getYear();
				String name = dummyCalendar.getMonthSymbol(month);
				boolean selected = dummyCalendar.isSelectedMonth(year, month);
				months[i][j].setValue(month, year, name, selected); month++;
			}
		}
		
		//
		mp.repaint();
	}
}
