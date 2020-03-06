package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min.widget.MinWidet;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.ShortcutPanel;

/**
 * @author jonny
 */
public class MinContent extends AbstractPanel {
	//
	private MinWidet[] items;
	private final int itemHeight = ShortcutPanel.itemHeight;
	
	/**
	 * 
	 */
	public MinContent(MinWidet[] items) {
		super("minute.content");
		this.items = items;	for (MinWidet item : items) { add(item); }
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		MinContent parent = (MinContent) container;
		final Rectangle r = parent.getBounds();
		//
		int p = 0, h = itemHeight; for (MinWidet item : this.items) {
			item.setBounds(new Rectangle(r.x, p++ * h, r.width, h));
		}
		parent.setBounds(new Rectangle(0, 0, r.width, p * h));
	}

}
