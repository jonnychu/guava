package cn.nextop.guava.widgets.datetime.render;

import java.util.Iterator;

import org.eclipse.draw2d.ScrollBar;
import org.eclipse.draw2d.ScrollPane;

import cn.nextop.guava.draw2d.MouseWheelListener;
import cn.nextop.guava.draw2d.ScrollEvent;

/**
 * @author jonny
 */
public class AbstractScrollPanel extends ScrollPane {
	
	public AbstractScrollPanel() {
		addListener(MouseWheelListener.class, new MouseWheelListener.Stub());
	}
	
	/**
	 * 
	 */
	public void setVerticalScrollStep(int step) {
		ScrollBar vsb = getVerticalScrollBar();
		vsb.setStepIncrement(step);
	}
	
	public void setHorizontalScrollStep(int step) {
		ScrollBar vsb = getVerticalScrollBar();
		vsb.setStepIncrement(step);
	}
	
	/**
	 * 
	 */
	public void pageUp(boolean isVertical) {
		ScrollBar vsb = null;
		if(isVertical) vsb = getVerticalScrollBar(); 
		else vsb = getHorizontalScrollBar(); if(!vsb.isVisible()) return;
		vsb.setValue(vsb.getValue() - vsb.getStepIncrement());
	}
	
	public void pageDown(boolean isVertical) {
		ScrollBar vsb = null;
		if(isVertical) vsb = getVerticalScrollBar(); 
		else vsb = getHorizontalScrollBar(); if(!vsb.isVisible()) return;
		vsb.setValue(vsb.getValue() + vsb.getStepIncrement());
	}
	
	/**
	 * 
	 */
	public void handleMouseWheel(ScrollEvent event) {
		@SuppressWarnings("rawtypes")
		Iterator iter = getListeners(MouseWheelListener.class);
		while (iter.hasNext()) ((MouseWheelListener) iter.next()).mouseScrolled(event);
	}
}
