package cn.nextop.guava.widgets.datetime.render.popup.shortcut;

import static cn.nextop.guava.support.util.Objects.cast;
import static cn.nextop.guava.widgets.datetime.XDateTimePopup.ITEMHEIGHT;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.datetime.builder.XDateTimePopupBuilder;
import cn.nextop.guava.widgets.datetime.render.AbstractTimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.widget.ShortcutItem;
import cn.nextop.guava.widgets.datetime.support.glossary.Shortcut;

/**
 * @author jonny
 */
public class ShortcutContent extends AbstractTimePanel {
	//
	private ShortcutItem[] items;
	private final int itemHeight = ITEMHEIGHT;
	
	/**
	 * 
	 */
	public ShortcutContent(String name, XDateTimePopupBuilder builder) {
		super(name); this.builder = builder;
		
		final Shortcut[] scs = Shortcut.values();
		this.items = new ShortcutItem[scs.length];
		for (int i = 0; i < scs.length; i++) {
			items[i] = new ShortcutItem(scs[i]); add(items[i]);
		}
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		ShortcutContent parent = cast(container);
		final Rectangle r = parent.getBounds();
		//
		int p = 0, h = itemHeight; 
		for (ShortcutItem item : this.items) {
			item.setBounds(new Rectangle(r.x, p++ * h, r.width, h));
		}
		parent.setBounds(new Rectangle(0, 0, r.width, p * h));
	}
}