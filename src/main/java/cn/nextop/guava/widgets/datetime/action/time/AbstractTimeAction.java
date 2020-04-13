package cn.nextop.guava.widgets.datetime.action.time;

import static cn.nextop.guava.support.Objects.cast;
import static cn.nextop.guava.widgets.datetime.XDateTimePopup.ITEMHEIGHT;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.AbstractAction;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.HourContent;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.HourPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.widget.HourWidet;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min.MinContent;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min.MinPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min.widget.MinWidet;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec.SecContent;
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
		final TimePanel timePanel = cast(container);
		final HourPanel hour = timePanel.getBuilder().getHourPanel();
		final MinPanel minute = timePanel.getBuilder().getMinPanel();
		final SecPanel second = timePanel.getBuilder().getSecPanel();
		final DummyCalendar dc = timePanel.getBuilder().getDummyCalendar();
		
		//
		final HourWidet[] hours = ((HourContent)hour.getContents()).getItems();
		final MinWidet[] minutes = ((MinContent)minute.getContents()).getItems();
		final SecWidet[] seconds = ((SecContent)second.getContents()).getItems();
		
		int y1 = 0, y2 = 0, y3 = 0, h = ITEMHEIGHT;;
		for (int i = 0; i < hours.length; i++) {
			final boolean selected = dc.getSelectedHour() == i;
			if(selected) y1 = i * h; hours[i].setValue(i, selected);
		}
		
		for (int i = 0; i < minutes.length; i++) {
			final boolean selected = dc.getSelectedMintue() == i;
			if(selected) y2 = i * h; minutes[i].setValue(i, selected);
		}
		
		for (int i = 0; i < seconds.length; i++) {
			final boolean selected = dc.getSelectedSecond() == i;
			if(selected) y3 = i * h; seconds[i].setValue(i, selected);
		}
		
		//
		hour.scrollVerticalTo(y1); minute.scrollVerticalTo(y2); second.scrollVerticalTo(y3);
		
		//
		timePanel.repaint();
	}
}
