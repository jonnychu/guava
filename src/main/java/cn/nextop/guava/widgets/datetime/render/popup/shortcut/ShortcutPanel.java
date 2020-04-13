package cn.nextop.guava.widgets.datetime.render.popup.shortcut;

import static cn.nextop.guava.widgets.datetime.XDateTimePopup.ITEMHEIGHT;

import cn.nextop.guava.widgets.datetime.render.AbstractTimeScrollPanel;
import cn.nextop.guava.widgets.datetime.support.dispatcher.event.ScrollEvent;

/**
 * @author jonny
 */
public class ShortcutPanel extends AbstractTimeScrollPanel {
	
	/**
	 * 
	 */
	public ShortcutPanel(String name) {
		super(name);
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
