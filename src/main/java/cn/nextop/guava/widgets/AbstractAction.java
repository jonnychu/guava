package cn.nextop.guava.widgets;

import org.eclipse.draw2d.IFigure;

/**
 * @author jonny
 */
public abstract class AbstractAction {
	//
	protected abstract void updateUI(IFigure container, IFigure widget);
	protected abstract boolean updateData(IFigure container, IFigure widget);
	
	/**
	 * 
	 */
	public void onAction(IFigure container, IFigure widget) {
		if(updateData(container, widget)) updateUI(container, widget);
	}
}
