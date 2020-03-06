package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.widget.HourWidet;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.ShortcutPanel;

/**
 * @author jonny
 */
public class HourContent extends AbstractPanel {
	//
	private HourWidet[] items;
	private final int itemHeight = ShortcutPanel.itemHeight;
	
	/**
	 * 
	 */
	public HourContent(HourWidet[] items) {
		super("hour.content");
		this.items = items;	for (HourWidet item : items) { add(item); }
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		HourContent parent = (HourContent) container;
		final Rectangle r = parent.getBounds();
		//
		int p = 0, h = itemHeight; for (HourWidet item : this.items) {
			item.setBounds(new Rectangle(r.x, p++ * h, r.width, h));
		}
		parent.setBounds(new Rectangle(0, 0, r.width, p * h));
	}
}
