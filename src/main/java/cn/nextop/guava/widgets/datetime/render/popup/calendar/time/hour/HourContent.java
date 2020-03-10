package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.datetime.render.AbstractTimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.widget.HourWidet;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.ShortcutPanel;

/**
 * @author jonny
 */
public class HourContent extends AbstractTimePanel {
	//
	private HourWidet[] items;
	private HourPanel hourPanel;
	private final int itemHeight = ShortcutPanel.itemHeight;
	
	/**
	 * 
	 */
	public HourPanel getHourPanel() { return hourPanel; }

	/**
	 * 
	 */
	public HourContent(HourWidet[] items, HourPanel hourPanel) {
		super("hour.content"); this.hourPanel = hourPanel;
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
