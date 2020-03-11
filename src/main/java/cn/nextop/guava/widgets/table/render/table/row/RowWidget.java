package cn.nextop.guava.widgets.table.render.table.row;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author jonny
 */
public class RowWidget extends AbstractRowWidget {
	
	@Override
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
		g.drawRectangle(getBounds());
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		Rectangle r = getBounds();
		g.drawText(this.text, r.x, r.y);
	}
}
