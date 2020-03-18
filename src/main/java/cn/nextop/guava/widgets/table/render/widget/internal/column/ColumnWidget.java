package cn.nextop.guava.widgets.table.render.widget.internal.column;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.table.model.basic.column.XTableColumn;

/**
 * @author jonny
 */
public class ColumnWidget extends AbstractColumnWidget {
	
	/**
	 * 
	 */
	public ColumnWidget(XTableColumn column) {
		this.column = column;
	}

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
