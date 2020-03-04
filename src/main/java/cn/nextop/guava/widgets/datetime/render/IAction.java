package cn.nextop.guava.widgets.datetime.render;

import org.eclipse.draw2d.IFigure;

/**
 * @author jonny
 */
public interface IAction {
	
	void onAction(IFigure container, IFigure widget);
}
