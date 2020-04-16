package cn.nextop.guava.widgets.table.render.widget;

import static cn.nextop.guava.support.swt.CGUtils.fillRect;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

import cn.nextop.guava.support.swt.Colors;
import cn.nextop.guava.widgets.table.render.AbstractXTableCellWidget;

/**
 * @author jonny
 */
public class DefaultColumnWidget extends AbstractXTableCellWidget {
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		fillRect(g, getBounds(), Colors.COLOR_WHITE);
	}
	
	@Override
	protected void paintClientArea(Graphics g) {
		super.paintClientArea(g);
		final Rectangle r = getClientArea();
		int align = column.getColAlign(); this.text = column.getText();
		final Dimension d1 = INSTANCE.getStringExtents(text, g.getFont());
		if(align == SWT.LEFT) {
			g.drawText(this.text, r.x + margin, (r.height - d1.height) / 2);
		} else if(align == SWT.RIGHT) {
			g.drawText(this.text, r.x + r.width - d1.width - margin, (r.height - d1.height) / 2);
		} else {
			g.drawText(this.text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
		}
	}
}
