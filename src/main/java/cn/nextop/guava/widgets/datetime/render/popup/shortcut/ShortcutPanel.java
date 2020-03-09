package cn.nextop.guava.widgets.datetime.render.popup.shortcut;

import cn.nextop.guava.draw2d.scroll.ScrollEvent;
import cn.nextop.guava.widgets.AbstractScrollPanel;
import cn.nextop.guava.widgets.datetime.render.popup.PopupPanel;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.widget.ShortcutItem;
import cn.nextop.guava.widgets.datetime.support.glossary.Shortcut;

/**
 * @author jonny
 */
public class ShortcutPanel extends AbstractScrollPanel {
	//
	private PopupPanel popupPanel;
	public static final int itemHeight = 24;

	/**
	 * 
	 */
	public PopupPanel getPopupPanel() { return popupPanel; }

	/**
	 * 
	 */
	public ShortcutPanel(PopupPanel popupPanel) {
		super("shortcut.panel");
		this.popupPanel = popupPanel;
		//
		setVerticalScrollStep(itemHeight);
		setHorizontalScrollBarVisibility(NEVER);
		setVerticalScrollBarVisibility(AUTOMATIC);
		//
		final Shortcut[] scs = Shortcut.values();
		ShortcutItem[] items = new ShortcutItem[scs.length];
		for (int i = 0; i < scs.length; i++) {
			items[i] = new ShortcutItem(scs[i]);
		}
		setContents(new ShortcutContent(items, this));
	}
	
	@Override
	protected boolean useLocalCoordinates() { return true; }
	
	@Override
	public void handleMouseWheel(ScrollEvent event) {
		super.handleMouseWheel(event);
		if(event.count > 0) pageUp(true); else pageDown(true);
	}
}
