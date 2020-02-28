package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.action;

import static java.lang.String.valueOf;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.glossary.PanelType;
import cn.nextop.guava.widgets.datetime.glossary.Type;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.model.DummyModel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.DateItemWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.MonthWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget.YearWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;

/**
 * @author jonny
 */
public class MonthClickAction {
	//
	private final Type type;
	
	/**
	 * 
	 */
	public MonthClickAction(Type type) {
		this.type = type;
	}
	
	/**
	 * 
	 */
	public void onAction(IFigure container) {
		final DatePanel datePanel = (DatePanel)container;
		final DummyCalendar calendar = datePanel.getDummyCalendar();
		final CalendarPanel calendarPanel = datePanel.getCalendar();
		final MonthPanel monthPanel = calendarPanel.getMonthPanel();
		
		// get data
		if(this.type == Type.SELECT) {
			calendarPanel.panel(PanelType.MONTH);
			monthPanel.getSelectYear().setText(valueOf(calendar.getYear()));
		} else {
			String m = "", y = "";
			if(this.type == Type.DOWN) {
				m = calendar.prevMonth();
				y = valueOf(calendar.getYear());
			} else if(this.type == Type.UP) {
				m = calendar.nextMonth();
				y = valueOf(calendar.getYear());
			}
			
			// update ui
			final YearWidget year = datePanel.getSelectYear();
			final MonthWidget month = datePanel.getSelectMonth();
			year.setText(y); month.setText(m); year.repaint(); month.repaint();
			
			// update date
			DateItemWidget[][] dates = datePanel.getDates();
			DummyModel[][] models = datePanel.getDummyCalendar().getCalendar();
			for (int i = 0; i < models.length; i++) {
				for (int j = 0; j < models[i].length; j++) {
					DummyModel dm = models[i][j];
					dates[i][j].setEditable(dm.isEditable());
					dates[i][j].setText(valueOf(dm.getDay()));
					dates[i][j].repaint();
				}
			}
		}
	}
}
