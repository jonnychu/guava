package cn.nextop.guava.widgets.datetime.render.popup.shortcut.widget;

import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.XDateTime;
import cn.nextop.guava.widgets.datetime.render.AbstractTimeWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.action.ShowDateAction;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.action.ShowTimeAction;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.ShortcutContent;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.ShortcutPanel;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.action.ShortcutAction;
import cn.nextop.guava.widgets.datetime.render.text.acton.ShowTextAction;
import cn.nextop.guava.widgets.datetime.support.glossary.Shortcut;

/**
 * @author jonny
 */
public class ShortcutItem extends AbstractTimeWidget {
	//
	private Shortcut shortcut;
	
	/**
	 * 
	 */
	public ShortcutItem(Shortcut shortcut) {
		this.shortcut = shortcut;
		this.text = shortcut.name();
	}
	
	public Shortcut getShortcut() { return shortcut; }

	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		Rectangle rect = getBounds();
		if(enter) g.setBackgroundColor(Colors.COLOR_WIDGET_MOTION_ENTER);
		g.fillRectangle(rect);
		
		//
		Dimension d1 = INSTANCE.getStringExtents(text, g.getFont());
		g.drawString(text, rect.x + 10, rect.y + (rect.height - d1.height) / 2);
	}
	
	@Override
	public void handleMouseReleased(MouseEvent event) {
		super.handleMouseReleased(event); 
		final ShortcutContent content = (ShortcutContent) getParent();
		final ShortcutPanel ssp = content.getShortcutPanel();
		final CalendarPanel cp = ssp.getPopupPanel().getCalendarPanel();
		final XDateTime xdt = ssp.getPopupPanel().getXDateTimePopup().getDateTime();
		//
		new ShortcutAction().onAction(content, this);
		new ShowDateAction().onAction(cp.getDatePanel(), null);
		new ShowTimeAction().onAction(cp.getTimePanel(), null);
		new ShowTextAction().onAction(xdt.getTextPanel(), null);
	}
}