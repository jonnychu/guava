package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour;

import cn.nextop.guava.draw2d.scroll.ScrollEvent;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.AbstractScrollPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.widget.HourWidet;

/**
 * @author jonny
 */
public class HourPanel extends AbstractScrollPanel {
	//
	private HourWidet[] items;
	private TimePanel timePanel;
	public static final int itemHeight = 24;
	
	/**
	 * 
	 */
	public HourWidet[] getItems() { return items; }
	public TimePanel getTimePanel() { return timePanel; }
	
	/**
	 * 
	 */
	public HourPanel(TimePanel timePanel) {
		super("hour"); this.timePanel = timePanel;
		DummyCalendar dc = getDummyCalendarFromHour();
		//
		setVerticalScrollStep(itemHeight);
		setHorizontalScrollBarVisibility(NEVER);
		setVerticalScrollBarVisibility(AUTOMATIC);
		//
		this.items = new HourWidet[23];
		for (int i = 0; i < items.length; i++) {
			final int v = dc.getSelectedHour();
			items[i] = new HourWidet(i, v == i);
		}
		setContents(new HourContent(items));
	}
	
	@Override
	protected boolean useLocalCoordinates() { return true; }
	
	@Override
	public void handleMouseWheel(ScrollEvent event) {
		super.handleMouseWheel(event);
		if(event.count > 0) pageUp(true); else pageDown(true);
	}
}
