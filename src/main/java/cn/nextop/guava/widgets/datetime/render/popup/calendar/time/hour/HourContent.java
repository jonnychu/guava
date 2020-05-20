package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour;

import static cn.nextop.guava.widgets.datetime.XDateTimePopup.ITEMHEIGHT;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.AbstractTimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.widget.HourWidet;
import cn.nextop.guava.widgets.datetime.support.builder.XDateTimePopupBuilder;

/**
 * @author jonny
 */
public class HourContent extends AbstractTimePanel {
	//
	private HourWidet[] items;
	private final int itemHeight = ITEMHEIGHT;
	
	/**
	 * 
	 */
	public HourWidet[] getItems() { return items; }

	/**
	 * 
	 */
	public HourContent(String name, XDateTimePopupBuilder builder) {
		super(name); this.builder = builder;
		final DummyCalendar dc = builder.getDummyCalendar();
		//
		this.items = new HourWidet[24];
		for (int i = 0; i < items.length; i++) {
			final int v = dc.getSelectedHour();
			items[i] = new HourWidet(i, v == i); add(items[i]);
		}
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
