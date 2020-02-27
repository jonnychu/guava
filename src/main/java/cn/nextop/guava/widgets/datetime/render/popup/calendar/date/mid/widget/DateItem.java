package cn.nextop.guava.widgets.datetime.render.popup.calendar.date.mid.widget;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.CGUtils;
import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.render.AbstractWidget;

public class DateItem extends AbstractWidget {
	//
	protected int day;
	
	/**
	 * 
	 */
	public int getDay() { return day; }
	public void setDay(int day) { this.day = day; }

	/**
	 * 
	 */
	public DateItem(int day, boolean editable) {
		this.text = String.valueOf(day);
		this.day = day; this.editable = editable;
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		Rectangle r = getBounds();
		if(!editable) g.setForegroundColor(Colors.COLOR_DARK_GRAY);
		Dimension d1 = TextUtilities.INSTANCE.getTextExtents(text, g.getFont());
		if(selected && editable) {
			g.setBackgroundColor(Colors.COLOR_CYAN);
			g.fillRoundRectangle(CGUtils.scaleRect(r, -2), arc, arc);
		}
		g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
	}
	
	@Override
	public void handleMouseEntered(MouseEvent event) { super.handleMouseEntered(event); this.selected = true; repaint(); }
	
	@Override
	public void handleMouseExited(MouseEvent event) { super.handleMouseExited(event); this.selected = false; repaint(); }
}