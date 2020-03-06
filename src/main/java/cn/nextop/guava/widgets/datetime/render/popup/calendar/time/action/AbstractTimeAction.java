package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.action;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.AbstractAction;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.HourPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.widget.HourWidet;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min.MinPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min.widget.MinWidet;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec.SecPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec.widget.SecWidet;

/**
 * @author jonny
 */
public abstract class AbstractTimeAction extends AbstractAction {
	
	/**
	 * 
	 */
	public void updateUI(IFigure container, IFigure widget) {
		final TimePanel timePanel = (TimePanel) container;
		final DummyCalendar dc = timePanel.getDummyCalendarFromTime();
		final HourPanel hour = timePanel.getHourPanel();
		final MinPanel minute = timePanel.getMinPanel();
		final SecPanel second = timePanel.getSecPanel();
		
		//
		final HourWidet[] hours = hour.getItems();
		final MinWidet[] minutes = minute.getItems();
		final SecWidet[] seconds = second.getItems();
		
		int y1 = 0, y2 = 0, y3 = 0, h = 24;
		for (int i = 0; i < hours.length; i++) {
			final int v1 = i + 1;
			final boolean selected = dc.getSelectedHour() == v1;
			if(selected) y1 = i * h; hours[i].setValue(v1, selected);
		}
		
		for (int i = 0; i < minutes.length; i++) {
			final int v1 = i + 1;
			final boolean selected = dc.getSelectedMintue() == v1;
			if(selected) y2 = i * h; minutes[i].setValue(v1, selected);
		}
		
		for (int i = 0; i < seconds.length; i++) {
			final int v1 = i + 1;
			final boolean selected = dc.getSelectedSecond() == v1;
			if(selected) y3 = i * h; seconds[i].setValue(v1, selected);
		}
		
		//
		hour.scrollVerticalTo(y1); minute.scrollVerticalTo(y2); second.scrollVerticalTo(y3);
		
		//
		timePanel.repaint();
	}
}
