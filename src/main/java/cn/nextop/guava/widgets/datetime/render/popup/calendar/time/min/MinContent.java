package cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min;

import static cn.nextop.guava.widgets.datetime.XDateTimePopup.ITEMHEIGHT;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.datetime.builder.XDateTimePopupBuilder;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.AbstractTimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min.widget.MinWidet;

/**
 * @author jonny
 */
public class MinContent extends AbstractTimePanel {
	//
	private MinWidet[] items;
	private final int itemHeight = ITEMHEIGHT;
	
	/**
	 * 
	 */
	public MinWidet[] getItems() { return items; }
	
	/**
	 * 
	 */
	public MinContent(String name, XDateTimePopupBuilder builder) {
		super(name); this.builder = builder;
		DummyCalendar dc = builder.getDateTimePopup().getDummyCalendar();
		this.items = new MinWidet[60];
		for (int i = 0; i < items.length; i++) {
			final int v = dc.getSelectedMintue();
			items[i] = new MinWidet(i, v == i); add(items[i]);
		}
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
