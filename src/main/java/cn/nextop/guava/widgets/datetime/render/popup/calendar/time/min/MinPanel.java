package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min;

import cn.nextop.guava.draw2d.scroll.ScrollEvent;
import cn.nextop.guava.widgets.datetime.render.AbstractScrollPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min.widget.MinWidet;

/**
 * @author jonny
 */
public class MinPanel extends AbstractScrollPanel {
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
	public MinPanel(TimePanel timePanel) {
		super("minute"); this.timePanel = timePanel;
		//
		setVerticalScrollStep(itemHeight);
		setHorizontalScrollBarVisibility(NEVER);
		setVerticalScrollBarVisibility(AUTOMATIC);
		//
		MinWidet[] items = new MinWidet[60];
		for (int i = 0; i < items.length; i++) {
			items[i] = new MinWidet(i+1);
		}
		setContents(new MinContent(items));
	}
	
	@Override
	protected boolean useLocalCoordinates() { return true; }
	
	@Override
	public void handleMouseWheel(ScrollEvent event) {
		super.handleMouseWheel(event);
		if(event.count > 0) pageUp(true); else pageDown(true);
	}
}
