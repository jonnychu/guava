package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget;

import static cn.nextop.guava.support.Objects.cast;
import static cn.nextop.guava.widgets.datetime.action.actor.ActorManager.ActionType.DATE_DATE;
import static cn.nextop.guava.widgets.datetime.action.actor.ActorManager.ActionType.TEXT_SHOW;
import static java.lang.String.valueOf;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

import cn.nextop.guava.support.swt.CGUtils;
import cn.nextop.guava.support.swt.Colors;
import cn.nextop.guava.widgets.datetime.action.event.Event;
import cn.nextop.guava.widgets.datetime.action.event.Event.EventType;
import cn.nextop.guava.widgets.datetime.render.AbstractTimeWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.text.TextPanel;

public class DateItemWidget extends AbstractTimeWidget {
	//
	private boolean now;
	private int year, month, day;
	
	/**
	 * 
	 */
	public int getDay() { return day; }
	public int getYear() { return year; }
	public int getMonth() { return month; }
	
	/**
	 * 
	 */
	public DateItemWidget(int year, int month, int day, boolean editable, boolean selected, boolean now) {
		this.text = valueOf(day); this.year = year; this.month = month; this.day = day; 
		this.editable = editable; this.selected = selected; this.now = now; 
	}
	
	public void setValue(int year, int month, int day, boolean editable, boolean selected, boolean now) {
		this.text = valueOf(day); this.year = year; this.month = month; this.day = day; 
		this.editable = editable; this.selected = selected; this.now = now; 
	}
	
	@Override	
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		final Rectangle r = getBounds();
		Dimension d1 = INSTANCE.getTextExtents(text, g.getFont());
		final int shrink = 2, space = 2; Rectangle r1 = CGUtils.scaleRect(r, -shrink);
		//
		if(selected && editable || selected && !editable) {
			g.setBackgroundColor(Colors.COLOR_LIGHT_BLUE);
			g.fillRoundRectangle(r1, arc, arc);
			g.setForegroundColor(Colors.COLOR_WHITE);
		} else if(!selected && editable) {
			if(enter && editable) {
				g.setBackgroundColor(Colors.COLOR_WIDGET_MOTION_ENTER);
				g.fillRoundRectangle(r1, arc, arc);
			}
		} else if(!selected && !editable) {
			g.setForegroundColor(Colors.COLOR_DARK_GRAY);
		}
		g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
		
		if(!g.getAdvanced()) g.setAntialias(SWT.ON);
		if(selected && now) {
			g.setBackgroundColor(Colors.COLOR_WHITE);
			g.fillOval(r1.x + r1.width - oval - space, r1.y + space, oval, oval);
		}
		else if(!selected && now) {
			g.setBackgroundColor(Colors.COLOR_LIGHT_BLUE);
			g.fillOval(r1.x + r1.width - oval - space, r1.y + space, oval, oval);
		}
		g.setAntialias(SWT.OFF);
	}
	
	@Override
	public void handleMouseReleased(MouseEvent event) {
		super.handleMouseReleased(event);
		final DatePanel dp = cast(getParent());
		final TextPanel tp = dp.getFactory().getTextPanel();
		//
		dp.getFactory().getEventBus()
		.submit(new Event(EventType.COMMON, DATE_DATE, this, dp, this))
		.submit(new Event(EventType.COMMON, TEXT_SHOW, this, tp, null));
	}
}