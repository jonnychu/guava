package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.action;

import static java.lang.String.valueOf;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.model.DummyModel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.DateItemWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.MonthWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.YearWidget;

/**
 * @author jonny
 */
public abstract class AbstractDateAction {
	
	protected abstract boolean updateData(IFigure container, IFigure widget);
	
	/**
	 * 
	 */
	public void onAction(IFigure container, IFigure widget) {
		if(updateData(container, widget)) updateUI(container, widget);
	}
	
	/**
	 * 
	 */
	protected void updateUI(IFigure container, IFigure widget) {
		final DatePanel datePanel = (DatePanel)container;
		final DummyCalendar calendar = datePanel.getDummyCalendar();
		
		// update top ui
		final YearWidget yearWidget = datePanel.getSelectYear();
		final MonthWidget monthWidget = datePanel.getSelectMonth();
		yearWidget.setText(valueOf(calendar.getYear())); yearWidget.repaint();
		monthWidget.setText(calendar.getMonthSymbol()); monthWidget.repaint();
		
		// update date
		final DummyModel[][] models = calendar.getCalendar();
		final DateItemWidget[][] dates = datePanel.getDates();
		for (int i = 0; i < models.length; i++) {
			for (int j = 0; j < models[i].length; j++) {
				final DummyModel dm = models[i][j];  boolean editable = dm.isEditable();
				final int month = dm.getMonth(), year = dm.getYear(), day = dm.getDay();
				dates[i][j].setValue(year, month, day, editable); dates[i][j].repaint();
			}
		}
	}
}
