package cn.nextop.guava.widgets.table.render.table.widget;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author jonny
 */
public class ColumnWidgets extends AbstractColumnWidget {
	
	@Override
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
		g.drawRectangle(getBounds());
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		Rectangle r = getBounds();
		g.drawText(this.text, r.x + margin, r.y);
	}
}
