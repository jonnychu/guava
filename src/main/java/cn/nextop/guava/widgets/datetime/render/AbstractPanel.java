package cn.nextop.guava.widgets.datetime.render;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;

/**
 * @author jonny
 */
public abstract class AbstractPanel extends Figure {
	//
	protected abstract void layout(Figure parent);
	
	@Override
	protected void paintChildren(Graphics g) {
		super.paintChildren(g); layout(this);
	}
}
