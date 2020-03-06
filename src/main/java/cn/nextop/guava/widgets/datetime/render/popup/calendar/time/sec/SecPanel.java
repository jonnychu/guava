package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec;

import cn.nextop.guava.draw2d.scroll.ScrollEvent;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.AbstractScrollPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec.widget.SecWidet;

/**
 * @author jonny
 */
public class SecPanel extends AbstractScrollPanel {
	//
	private SecWidet[] items;
	private TimePanel timePanel;
	public static final int itemHeight = 24;
	
	/**
	 * 
	 */
	public SecWidet[] getItems() { return items; }
	public TimePanel getTimePanel() { return timePanel; }
	
	/**
	 * 
	 */
	public SecPanel(TimePanel timePanel) {
		super("second"); this.timePanel = timePanel;
		DummyCalendar dc = getDummyCalendarFromSecond();
		//
		setVerticalScrollStep(itemHeight);
		setHorizontalScrollBarVisibility(NEVER);
		setVerticalScrollBarVisibility(AUTOMATIC);
		//
		this.items = new SecWidet[60];
		for (int i = 0; i < items.length; i++) {
			final int v = dc.getSelectedSecond();
			items[i] = new SecWidet(i, v == i);
		}
		setContents(new SecContent(items, this));
	}
	
	@Override
	protected boolean useLocalCoordinates() { return true; }
	
	@Override
	public void handleMouseWheel(ScrollEvent event) {
		super.handleMouseWheel(event);
		if(event.count > 0) pageUp(true); else pageDown(true);
	}
}
