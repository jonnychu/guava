package cn.nextop.guava.widgets.datetime.render.popup;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.CGUtils;
import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.XDateTimePopup;
import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.ShortcutPanel;

/**
 * @author jonny
 */
public class PopupPanel extends AbstractPanel {
	//
	private XDateTimePopup popup;
	private CalendarPanel calendar;
	private ShortcutPanel shortcut;
	
	/**
	 * 
	 */
	public XDateTimePopup getPopup() { return popup; }
	public CalendarPanel getCalendar() { return calendar; }
	public ShortcutPanel getShortcut() { return shortcut; }
	
	/**
	 * 
	 */
	public PopupPanel(XDateTimePopup popup) {
		this.popup = popup;
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
	public boolean isOpaque() {
		return true;
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		PopupPanel parent = (PopupPanel)container;
		Rectangle r = container.getBounds();
		ShortcutPanel shortcut = parent.getShortcut();
		CalendarPanel calendar = parent.getCalendar();
		//
		int w1 = (int)(r.width * 0.2), w2 = r.width - w1;
		Rectangle r1 = new Rectangle(r.x, r.y, w1, r.height); shortcut.setBounds(r1);
		Rectangle r2 = new Rectangle(r.x + w1, r.y, w2, r.height); calendar.setBounds(r2);
//		System.out.println("1 " + shortcut.getBounds() + ", 2 " + calendar.getBounds());
	}
}
