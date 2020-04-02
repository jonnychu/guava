package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec;

import static cn.nextop.guava.widgets.datetime.XDateTimePopup.ITEMHEIGHT;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.datetime.builder.XDateTimePopupBuilder;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.AbstractTimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec.widget.SecWidet;

/**
 * @author jonny
 */
public class SecContent extends AbstractTimePanel {
	//
	private SecWidet[] items;
	private final int itemHeight = ITEMHEIGHT;
	
	/**
	 * 
	 */
	public SecWidet[] getItems() { return items; }

	/**
	 * 
	 */
	public SecContent(String name, XDateTimePopupBuilder builder) {
		super(name); this.builder = builder;
		final DummyCalendar dc = builder.getDummyCalendar();
		//
		this.items = new SecWidet[60];
		for (int i = 0; i < items.length; i++) {
			final int v = dc.getSelectedSecond();
			items[i] = new SecWidet(i, v == i); add(items[i]);
		}
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		SecContent parent = (SecContent) container;
		final Rectangle r = parent.getBounds();
		//
		int p = 0, h = itemHeight; for (SecWidet item : this.items) {
			item.setBounds(new Rectangle(r.x, p++ * h, r.width, h));
		}
		parent.setBounds(new Rectangle(0, 0, r.width, p * h));
	}
}
