package cn.nextop.guava.widgets;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.widgets.Display;

import cn.nextop.guava.support.swt.SwtUtils;

/**
 * @author jonny
 */
public abstract class AbstractActor {
	//
	protected abstract void updateUI(IFigure container, IFigure widget);
	protected abstract boolean updateData(IFigure container, IFigure widget);
	
	/**
	 * 
	 */
	public void onAction(IFigure container, IFigure widget) {
		final Display display = SwtUtils.getDisplay();
		if(Thread.currentThread() == display.getThread()) {
			if(updateData(container, widget)) updateUI(container, widget);
		} else {
			SwtUtils.async(display, () -> {
				if(updateData(container, widget)) updateUI(container, widget);
			});
		}
	}
}
