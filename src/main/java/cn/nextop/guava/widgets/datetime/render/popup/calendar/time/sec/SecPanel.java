package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec;

import cn.nextop.guava.draw2d.scroll.ScrollEvent;
import cn.nextop.guava.widgets.datetime.render.AbstractScrollPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec.widget.SecWidet;

/**
 * @author jonny
 */
public class SecPanel extends AbstractScrollPanel {
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
	public SecPanel(TimePanel timePanel) {
		super("second"); this.timePanel = timePanel;
		//
		setVerticalScrollStep(itemHeight);
		setHorizontalScrollBarVisibility(NEVER);
		setVerticalScrollBarVisibility(AUTOMATIC);
		//
		SecWidet[] items = new SecWidet[60];
		for (int i = 0; i < items.length; i++) {
			items[i] = new SecWidet(i+1);
		}
		setContents(new SecContent(items));
	}
	
	@Override
	protected boolean useLocalCoordinates() { return true; }
	
	@Override
	public void handleMouseWheel(ScrollEvent event) {
		super.handleMouseWheel(event);
		if(event.count > 0) pageUp(true); else pageDown(true);
	}
}
