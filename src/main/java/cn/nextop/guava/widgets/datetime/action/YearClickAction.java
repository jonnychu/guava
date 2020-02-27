package cn.nextop.guava.widgets.datetime.action;

import static java.lang.String.valueOf;

import org.eclipse.draw2d.Figure;

import cn.nextop.guava.widgets.datetime.glossary.Type;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.mid.widget.DateItem;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.top.TopPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.top.widgets.MonthWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.top.widgets.YearWidget;
import cn.nextop.guava.widgets.datetime.render.utils.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.utils.DummyModel;

/**
 * @author jonny
 */
public class YearClickAction {
	//
	private final Type type;
	
	/**
	 * 
	 */
	public YearClickAction(Type type) {
		this.type = type;
	}
	
	/**
	 * 
	 */
	public void onAction(Figure container) {
		final TopPanel topPanel = (TopPanel)container;
		final DatePanel panel = topPanel.getDatePanel();
		final DummyCalendar calendar = panel.getDummyCalendar();
		
		// get data
		String m = "", y = "";
		if(this.type == Type.DOWN) {
			y = valueOf(calendar.prevYear());
			m = calendar.getMonthSymbol();
		} else if(this.type == Type.UP) {
			y = valueOf(calendar.nextYear());
			m = calendar.getMonthSymbol();
		}
		
		// update top ui
		final YearWidget year = topPanel.getSelectYear();
		final MonthWidget month = topPanel.getSelectMonth();
		year.setText(y); month.setText(m); year.repaint(); month.repaint();
		
		// update date
		DateItem[][] dates = panel.getMidPanel().getDates();
		DummyModel[][] models = panel.getDummyCalendar().getCalendar();
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
