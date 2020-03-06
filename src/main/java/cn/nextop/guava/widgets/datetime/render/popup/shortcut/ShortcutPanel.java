package cn.nextop.guava.widgets.datetime.render.popup.shortcut;

import cn.nextop.guava.draw2d.scroll.ScrollEvent;
import cn.nextop.guava.widgets.datetime.glossary.Shortcut;
import cn.nextop.guava.widgets.datetime.render.AbstractScrollPanel;
import cn.nextop.guava.widgets.datetime.render.popup.PopupPanel;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.widget.ShortcutItem;

/**
 * @author jonny
 */
public class ShortcutPanel extends AbstractScrollPanel {
	//
	public static final int itemHeight = 24;
	//
	private PopupPanel popupPanel;
	
	/**
	 * 
	 */
	public PopupPanel getPopupPanel() { return popupPanel; }

	/**
	 * 
	 */
	public ShortcutPanel(PopupPanel popupPanel) {
		super("shortcutpanel");
		this.popupPanel = popupPanel;
		//
		setVerticalScrollStep(itemHeight);
		setHorizontalScrollBarVisibility(NEVER);
		setVerticalScrollBarVisibility(AUTOMATIC);
		//
		Shortcut[] scs = Shortcut.values();
		ShortcutItem[] items = new ShortcutItem[scs.length];
		for (int i = 0; i < scs.length; i++) {
			items[i] = new ShortcutItem(scs[i]);
		}
		Content content = new Content(items, this); setContents(content);
	}
	
	@Override
	public void handleMouseWheel(ScrollEvent event) {
		super.handleMouseWheel(event);
		if(event.count > 0) pageUp(true); else pageDown(true);
	}
}
