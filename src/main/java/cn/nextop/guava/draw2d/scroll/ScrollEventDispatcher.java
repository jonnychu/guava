package cn.nextop.guava.draw2d.scroll;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTEventDispatcher;
import org.eclipse.swt.widgets.Event;

import cn.nextop.guava.widgets.datetime.AbstractScrollPanel;

/**
 * add scroll mouse event
 */
public final class ScrollEventDispatcher extends SWTEventDispatcher {
	
	/**
	 * 
	 */
	public void dispatchMouseWheelScrolled(Event e) {
		IFigure mouseTarget = getMouseTarget();
		IFigure parent = getScrollPanel(mouseTarget);
		if(parent instanceof AbstractScrollPanel) {
			AbstractScrollPanel scroll = (AbstractScrollPanel)parent;
			scroll.handleMouseWheel(new ScrollEvent(this, parent, new org.eclipse.swt.events.MouseEvent(e)));
		}
	}
	
	/**
	 * 
	 */
	protected IFigure getScrollPanel(IFigure child) {
		if(child == null) return null;
		if(child instanceof AbstractScrollPanel) return child;
		IFigure parent = child.getParent();	
		if(parent == null) return null;	return getScrollPanel(parent);
	}
}