package cn.nextop.guava.widgets.datetime.support.dispatcher;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTEventDispatcher;
import org.eclipse.swt.widgets.Event;

import cn.nextop.guava.widgets.datetime.render.AbstractTimeScrollPanel;
import cn.nextop.guava.widgets.datetime.support.dispatcher.event.ScrollEvent;

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
		if(parent instanceof AbstractTimeScrollPanel) {
			AbstractTimeScrollPanel scroll = (AbstractTimeScrollPanel)parent;
			scroll.handleMouseWheel(new ScrollEvent(this, parent, new org.eclipse.swt.events.MouseEvent(e)));
		}
	}
	
	/**
	 * 
	 */
	protected IFigure getScrollPanel(IFigure child) {
		if(child == null) return null;
		if(child instanceof AbstractTimeScrollPanel) return child;
		IFigure parent = child.getParent();	
		if(parent == null) return null;	return getScrollPanel(parent);
	}
}