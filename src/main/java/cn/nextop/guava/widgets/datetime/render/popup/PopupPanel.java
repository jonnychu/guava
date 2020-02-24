package cn.nextop.guava.widgets.datetime.render.popup;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.CGUtils;
import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.XDateTimePopup;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.ShortcutPanel;

/**
 * @author jonny
 */
public class PopupPanel extends Figure {
	//
	private Layout layout;
	private XDateTimePopup popup;
	private CalendarPanel calendar;
	private ShortcutPanel shortcut;
	
	/**
	 * 
	 */
	public CalendarPanel getCalendar() { return calendar; }
	public ShortcutPanel getShortcut() { return shortcut; }
	
	/**
	 * 
	 */
	public PopupPanel(XDateTimePopup popup) {
		this.popup = popup;
		this.layout = new Layout();
		add(shortcut = new ShortcutPanel(this));
		add(calendar = new CalendarPanel(this));
	}
	
	@Override
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
		g.setForegroundColor(Colors.COLOR_DARK_GRAY);
		g.drawRectangle(CGUtils.getBorderRect(getBounds()));
	}
	
	@Override
	protected void paintChildren(Graphics graphics) {
		super.paintChildren(graphics); this.layout.layout(this);
	}
	
	/**
	 * 
	 */
	protected class Layout {
		//
		public void layout(PopupPanel parent) {
			Rectangle r = parent.getClientArea();
			ShortcutPanel shortcut = parent.getShortcut();
			CalendarPanel calendar = parent.getCalendar();
			//
			int w1 = (int)(r.width * 0.3), w2 = r.width - w1;
			shortcut.setBounds(new Rectangle(r.x, r.y, w1, r.height));
			calendar.setBounds(new Rectangle(r.x + w1, r.y, w2, r.height));
		}
	}
}
