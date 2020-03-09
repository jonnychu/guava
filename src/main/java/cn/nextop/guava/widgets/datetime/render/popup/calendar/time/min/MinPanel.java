package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min;

import static cn.nextop.guava.widgets.datetime.render.util.Faster.getDummyCalendarFromTime;

import cn.nextop.guava.draw2d.scroll.ScrollEvent;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.AbstractScrollPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min.widget.MinWidet;

/**
 * @author jonny
 */
public class MinPanel extends AbstractScrollPanel {
	//
	private MinWidet[] items;
	private TimePanel timePanel;
	public static final int itemHeight = 24;
	
	/**
	 * 
	 */
	public MinWidet[] getItems() { return items; }
	public TimePanel getTimePanel() { return timePanel; }
	
	/**
	 * 
	 */
	public MinPanel(TimePanel tp) {
		super("minute"); this.timePanel = tp;
		DummyCalendar dc = getDummyCalendarFromTime(tp);
		//
		setVerticalScrollStep(itemHeight);
		setHorizontalScrollBarVisibility(NEVER);
		setVerticalScrollBarVisibility(AUTOMATIC);
		//
		this.items = new MinWidet[60];
		for (int i = 0; i < items.length; i++) {
			final int v = dc.getSelectedMintue();
			items[i] = new MinWidet(i, v == i);
		}
		setContents(new MinContent(items, this));
	}
	
	@Override
	protected boolean useLocalCoordinates() { return true; }
	
	@Override
	public void handleMouseWheel(ScrollEvent event) {
		super.handleMouseWheel(event);
		if(event.count > 0) pageUp(true); else pageDown(true);
	}
}
