package cn.nextop.guava.widgets.datetime.render.popup.calendar.year.widget;

import static cn.nextop.guava.support.Objects.cast;
import static cn.nextop.guava.widgets.datetime.action.ActionFactory.ActionType.MONTH_SHOW;
import static cn.nextop.guava.widgets.datetime.action.ActionFactory.ActionType.YEAR_YEAR2;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.support.swt.CGUtils;
import cn.nextop.guava.support.swt.Colors;
import cn.nextop.guava.widgets.datetime.render.AbstractTimeWidget;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.YearPanel;

/**
 * @author jonny
 */
public class YearItemWidget extends AbstractTimeWidget {
	//
	private int year;
	
	/**
	 * 
	 */
	public int getYear() { return year; }

	/**
	 * 
	 */
	public YearItemWidget(int year, String name, boolean selected) {
		this.text = name; this.year = year; this.selected = selected;
	}
	
	public void setValue(int year, String name, boolean selected) {
		this.text = name; this.year = year; this.selected = selected;
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		final Rectangle r = getBounds();
		final Rectangle r1 = CGUtils.scaleRect(r, -5);
		Dimension d1 = TextUtilities.INSTANCE.getTextExtents(text, g.getFont());
		if(selected) {
			g.setForegroundColor(Colors.COLOR_WHITE);
			g.setBackgroundColor(Colors.COLOR_LIGHT_BLUE);
			g.fillRoundRectangle(r1, arc, arc);
		} else {
			if(enter) {
				g.setBackgroundColor(Colors.COLOR_WIDGET_MOTION_ENTER);
				g.fillRoundRectangle(r1, arc, arc);
			}
		}
		g.drawString(text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
	}
	
	@Override
	public void handleMouseReleased(MouseEvent event) {
		super.handleMouseReleased(event); 
		final YearPanel yp = cast(getParent());
		final MonthPanel mp = yp.getBuilder().getMonthPanel();
		yp.getBuilder().getActionFactory().onAction(YEAR_YEAR2, yp, this, MONTH_SHOW, mp, null);
	}
}
