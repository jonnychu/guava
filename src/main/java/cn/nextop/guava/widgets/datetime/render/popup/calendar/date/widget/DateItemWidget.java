package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.widget;

import static java.lang.String.valueOf;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.CGUtils;
import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.render.AbstractWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.action.DayAction;

public class DateItemWidget extends AbstractWidget {
	//
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
	public DateItemWidget(int year, int month, int day, boolean editable) {
		this.text = valueOf(day); this.editable = editable;
		this.year = year; this.month = month; this.day = day; 
	}
	
	public void setValue(int year, int month, int day, boolean editable) {
		this.text = valueOf(day); this.editable = editable;
		this.year = year; this.month = month; this.day = day; 
	}
	
	@Override	
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		Rectangle r = getBounds();
		if(!editable) g.setForegroundColor(Colors.COLOR_DARK_GRAY);
		Dimension d1 = TextUtilities.INSTANCE.getTextExtents(text, g.getFont());
		if(enter && editable) {
			g.setBackgroundColor(Colors.COLOR_CYAN);
			g.fillRoundRectangle(CGUtils.scaleRect(r, -2), arc, arc);
		}
		g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
	}
	
	@Override
	public void handleMouseReleased(MouseEvent event) {
		super.handleMouseReleased(event); new DayAction().onAction(getParent(), this);
	}
}