package cn.nextop.guava.widgets.datetime.action.date;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.AbstractAction;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.model.DummyModel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.DateItemWidget;

/**
 * @author jonny
 */
public abstract class AbstractDateAction extends AbstractAction {
	
	/**
	 * 
	 */
	public void updateUI(IFigure container, IFigure widget) {
		final DatePanel dp = cast(container);
		final DummyCalendar dummyCalendar = dp.getBuilder().getDummyCalendar();
		
		// update top ui
		dp.getSelectYear().setText(dummyCalendar.getYearSymbol());
		dp.getSelectMonth().setText(dummyCalendar.getMonthSymbol());
		
		// update date
		final DateItemWidget[][] dates = dp.getDates();
		final DummyModel[][] models = dummyCalendar.getCalendar();
		for (int i = 0; i < models.length; i++) {
			for (int j = 0; j < models[i].length; j++) {
				DummyModel dm = models[i][j]; 
				int month = dm.getMonth(), year = dm.getYear(), day = dm.getDay();
				final boolean isNow = dummyCalendar.isNow(year, month, day);
				final boolean editable = dummyCalendar.isCurMonth(year, month);
				boolean isSelected = dummyCalendar.isSelectedDate(year, month, day);
				dates[i][j].setValue(year, month, day, editable, isSelected, isNow); 
			}
		}
		
		// update ui
		dp.repaint();
	}
}