package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget;

import static java.lang.String.valueOf;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

import cn.nextop.guava.utils.CGUtils;
import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.render.AbstractTimeWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.action.DateAction;
import cn.nextop.guava.widgets.datetime.render.text.TextPanel;
import cn.nextop.guava.widgets.datetime.render.text.acton.ShowTextAction;
import cn.nextop.guava.widgets.datetime.support.tuil.Faster;

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
		DatePanel dp = (DatePanel)getParent(); TextPanel tp = Faster.getTextPanel(dp);
		new DateAction().onAction(getParent(), this); new ShowTextAction().onAction(tp, null);
	}
}