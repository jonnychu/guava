package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour;

import cn.nextop.guava.draw2d.scroll.ScrollEvent;
import cn.nextop.guava.widgets.datetime.render.AbstractScrollPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.widget.HourWidet;

/**
 * @author jonny
 */
public class HourPanel extends AbstractScrollPanel {
	//
	private TimePanel timePanel;
	public static final int itemHeight = 24;
	
	/**
	 * 
	 */
	public TimePanel getTimePanel() { return timePanel; }
	
	/**
	 * 
	 */
	public HourPanel(TimePanel timePanel) {
		super("hour"); this.timePanel = timePanel;
		//
		setVerticalScrollStep(itemHeight);
		setHorizontalScrollBarVisibility(NEVER);
		setVerticalScrollBarVisibility(AUTOMATIC);
		//
		HourWidet[] items = new HourWidet[12];
		for (int i = 0; i < items.length; i++) {
			items[i] = new HourWidet(i+1);
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
