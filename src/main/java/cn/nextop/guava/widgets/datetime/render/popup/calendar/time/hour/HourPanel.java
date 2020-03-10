package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour;

import static cn.nextop.guava.widgets.datetime.support.tuil.Faster.getDummyCalendar;

import cn.nextop.guava.draw2d.scroll.support.event.ScrollEvent;
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
	public HourPanel(TimePanel tp) {
		super("hour"); this.timePanel = tp;
		DummyCalendar dc = getDummyCalendar(tp);
		//
		setVerticalScrollStep(itemHeight);
		setHorizontalScrollBarVisibility(NEVER);
		setVerticalScrollBarVisibility(AUTOMATIC);
		//
		this.items = new HourWidet[24];
		for (int i = 0; i < items.length; i++) {
			final int v = dc.getSelectedHour();
			items[i] = new HourWidet(i, v == i);
		}
		setContents(new HourContent(items, this));
	}
	
	@Override
	protected boolean useLocalCoordinates() { return true; }
	
	@Override
	public void handleMouseWheel(ScrollEvent event) {
		super.handleMouseWheel(event);
		if(event.count > 0) pageUp(true); else pageDown(true);
	}
}
