package cn.nextop.guava.widgets.datetime.action;

import static java.lang.String.valueOf;

import org.eclipse.draw2d.Figure;

import cn.nextop.guava.widgets.datetime.glossary.Type;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.mid.widget.DateItem;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.top.TopPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.top.widgets.MonthWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.top.widgets.YearWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.utils.DummyCalendar;
import cn.nextop.guava.widgets.datetime.utils.DummyModel;

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
	public void onAction(Figure container) {
		final TopPanel topPanel = (TopPanel)container;
		final DatePanel datePanel = topPanel.getDatePanel();
		final DummyCalendar calendar = datePanel.getDummyCalendar();
		final CalendarPanel calendarPanel = datePanel.getCalendar();
		final MonthPanel monthPanel = calendarPanel.getMonthPanel();
		
		// get data
		if(this.type == Type.SELECT) {
			monthPanel.getSelectYear().setText(valueOf(calendar.getYear()));
			datePanel.setVisible(false);
			monthPanel.setVisible(true);
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
			final YearWidget year = topPanel.getSelectYear();
			final MonthWidget month = topPanel.getSelectMonth();
			year.setText(y); month.setText(m); year.repaint(); month.repaint();
			
			// update date
			DateItem[][] dates = datePanel.getMidPanel().getDates();
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
