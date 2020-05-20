package cn.nextop.guava.widgets.datetime.render.popup.shortcut.widget;

import static cn.nextop.guava.support.Objects.cast;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.support.swt.Colors;
import cn.nextop.guava.widgets.datetime.action.event.Event;
import cn.nextop.guava.widgets.datetime.action.event.Event.EventType;
import cn.nextop.guava.widgets.datetime.action.actor.ActorManager.ActionType;
import cn.nextop.guava.widgets.datetime.render.AbstractTimeWidget;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.ShortcutContent;
import cn.nextop.guava.widgets.datetime.support.builder.XDateTimePopupBuilder;
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
		this.shortcut = shortcut; this.text = shortcut.name();
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
		final ShortcutContent content = cast(getParent());
		final XDateTimePopupBuilder builder = content.getFactory();
		//
//		content.getBuilder().getActionFactory()
//		.onAction(ActionType.SHORTCUT, content, this)
//		.onAction(ActionType.TEXT_SHOW, builder.getTextPanel(), null)
//		.onAction(ActionType.DATE_SHOW, builder.getDatePanel(), null)
//		.onAction(ActionType.TIME_SHOW, builder.getTimePanel(), null);
		
		builder.getEventBus()
		.submit(new Event(EventType.COMMON, ActionType.SHORTCUT, this, content, this))
		.submit(new Event(EventType.COMMON, ActionType.TEXT_SHOW, this, builder.getTextPanel(), null))
		.submit(new Event(EventType.COMMON, ActionType.DATE_SHOW, this, builder.getDatePanel(), null))
		.submit(new Event(EventType.COMMON, ActionType.TIME_SHOW, this, builder.getTimePanel(), null));
	}
}