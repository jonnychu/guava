package cn.nextop.guava.widgets;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.widgets.Canvas;

/**
 * @author jonny
 */
public abstract class AbstractBuilder {
	
	public abstract IFigure build(Canvas parent);
}
