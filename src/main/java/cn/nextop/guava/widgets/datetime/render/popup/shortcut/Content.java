package cn.nextop.guava.widgets.datetime.render.popup.shortcut;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.widget.ShortcutItem;

/**
 * @author jonny
 */
public class Content extends AbstractPanel {
	//
	private ShortcutItem[] items;
	private ShortcutPanel shortcutPanel;
	private final int itemHeight = ShortcutPanel.itemHeight;
	
	/**
	 * 
	 */
	public ShortcutPanel getShortcutPanel() { return shortcutPanel; }

	/**
	 * 
	 */
	public Content(ShortcutItem[] items, ShortcutPanel shortcutPanel) {
		super("contentpanel");
		this.shortcutPanel = shortcutPanel;
		this.items = items;	for (ShortcutItem item : items) { add(item); }
	}
	
	@Override
	protected boolean useLocalCoordinates() { return true; }
	
	@Override
	protected void layoutManager(IFigure container) {
		Content parent = (Content)container;
		Rectangle r = parent.getBounds();
		//
		int p = 0, h = itemHeight; for (ShortcutItem item : this.items) {
			item.setBounds(new Rectangle(r.x, p++ * h, r.width, h));
		}
		parent.setBounds(new Rectangle(0, 0, r.width, p * h));
	}
}