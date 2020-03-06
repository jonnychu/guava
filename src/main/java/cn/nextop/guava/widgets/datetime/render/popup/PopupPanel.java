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
	private CalendarPanel calendar;
	private ShortcutPanel shortcut;
	private XDateTimePopup dateTimePopup;
	
	/**
	 * 
	 */
	public ShortcutPanel getShortcut() { return shortcut; }
	public XDateTimePopup getXDateTimePopup() { return dateTimePopup; }
	public CalendarPanel getCalendarPanel() { return calendar; }
	
	/**
	 * 
	 */
	public PopupPanel(XDateTimePopup dateTimePopup) {
		super("popuppanel");
		this.dateTimePopup = dateTimePopup;
		//
		add(shortcut = new ShortcutPanel(this));
		add(calendar = new CalendarPanel(this));
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
		final PopupPanel parent = (PopupPanel)container;
		final CalendarPanel calendar = parent.getCalendarPanel();
		final ShortcutPanel shortcut = parent.getShortcut();
		//
		int w1 = (int)(r.width * 0.3), w2 = r.width - w1;
		Rectangle r1 = new Rectangle(r.x, r.y, w1, r.height); shortcut.setBounds(r1);
		Rectangle r2 = new Rectangle(r.x + w1, r.y, w2, r.height); calendar.setBounds(r2);
	}
}
