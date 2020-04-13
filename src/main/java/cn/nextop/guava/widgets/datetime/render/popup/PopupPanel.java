package cn.nextop.guava.widgets.datetime.render.popup;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.support.swt.CGUtils;
import cn.nextop.guava.support.swt.Colors;
import cn.nextop.guava.widgets.datetime.builder.XDateTimePopupBuilder;
import cn.nextop.guava.widgets.datetime.render.AbstractTimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.ShortcutPanel;

/**
 * @author jonny
 */
public class PopupPanel extends AbstractTimePanel {
	
	/**
	 * 
	 */
	public PopupPanel(String name, XDateTimePopupBuilder builder) {
		super(name); this.builder = builder;
	}
	
	@Override
	public boolean isOpaque() { return true; }
	
	@Override
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
		g.setForegroundColor(Colors.COLOR_DARK_GRAY);
		g.drawRectangle(CGUtils.getBorderRect(getBounds()));
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		final Rectangle r = container.getBounds();
		final CalendarPanel calendar = this.builder.getCalendarPanel();
		final ShortcutPanel shortcut = this.builder.getShortcutPanel();
		//
		int w1 = (int)(r.width * 0.3), w2 = r.width - w1;
		Rectangle r1 = new Rectangle(r.x, r.y, w1, r.height); shortcut.setBounds(r1);
		Rectangle r2 = new Rectangle(r.x + w1, r.y, w2, r.height); calendar.setBounds(r2);
	}
}
