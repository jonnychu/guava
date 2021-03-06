package cn.nextop.guava.widgets.datetime.render.popup.calendar.common.widget;

import static cn.nextop.guava.support.Objects.cast;
import static cn.nextop.guava.widgets.datetime.action.actor.ActorManager.ActionType.TIME_SHOW;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.support.swt.CGUtils;
import cn.nextop.guava.support.swt.Colors;
import cn.nextop.guava.support.swt.Fonts;
import cn.nextop.guava.widgets.datetime.action.event.Event;
import cn.nextop.guava.widgets.datetime.action.event.Event.EventType;
import cn.nextop.guava.widgets.datetime.render.AbstractTimePanel;
import cn.nextop.guava.widgets.datetime.render.AbstractTimeWidget;

/**
 * @author jonny
 */
public class TimeButtonWidget extends AbstractTimeWidget {
	/**
	 * 
	 */
	public TimeButtonWidget(String text) {
		this.text = text;
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		final Rectangle r = getBounds(); g.setFont(Fonts.size(g.getFont(), 2));
		Dimension d1 = TextUtilities.INSTANCE.getTextExtents(text, g.getFont());
		if(enter) {
			g.setBackgroundColor(Colors.COLOR_WIDGET_MOTION_ENTER);
			g.fillRoundRectangle(CGUtils.scaleRect(r, -3), arc, arc);
		}
		g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
	}
	
	@Override
	public void handleMouseReleased(MouseEvent event) {
		super.handleMouseReleased(event); AbstractTimePanel panel = cast(getParent());
		panel.getFactory().getEventBus().submit(new Event(EventType.SHOW_TIME, TIME_SHOW, this, panel, null));
	}
}