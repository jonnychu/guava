package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.action;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.AbstractAction;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.HourPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min.MinPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec.SecPanel;

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
		timePanel.repaint();
	}
}
