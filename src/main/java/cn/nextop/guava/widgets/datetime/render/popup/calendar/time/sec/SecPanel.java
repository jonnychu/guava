package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec;

import static cn.nextop.guava.widgets.datetime.XDateTimePopup.ITEMHEIGHT;

import cn.nextop.guava.draw2d.scroll.support.event.ScrollEvent;
import cn.nextop.guava.widgets.datetime.render.AbstractTimeScrollPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;

/**
 * @author jonny
 */
public class SecPanel extends AbstractTimeScrollPanel {
	//
	private TimePanel timePanel;
	
	/**
	 * 
	 */
	public TimePanel getTimePanel() { return timePanel; }
	
	/**
	 * 
	 */
	public SecPanel(TimePanel tp) {
		super("second"); this.timePanel = tp;
		//
		setVerticalScrollStep(ITEMHEIGHT);
		setHorizontalScrollBarVisibility(NEVER);
		setVerticalScrollBarVisibility(AUTOMATIC);
	}
	
	@Override
	protected boolean useLocalCoordinates() { return true; }
	
	@Override
	public void handleMouseWheel(ScrollEvent event) {
		super.handleMouseWheel(event);
		if(event.count > 0) pageUp(true); else pageDown(true);
	}
}
