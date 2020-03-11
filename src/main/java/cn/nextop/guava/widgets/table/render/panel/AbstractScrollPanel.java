package cn.nextop.guava.widgets.table.render.panel;

import java.util.Iterator;

import org.eclipse.draw2d.RangeModel;
import org.eclipse.draw2d.ScrollBar;

import cn.nextop.guava.draw2d.scroll.support.event.MouseWheelListener;
import cn.nextop.guava.draw2d.scroll.support.event.ScrollEvent;
import cn.nextop.guava.draw2d.scroll.support.glossary.Type;
import cn.nextop.guava.widgets.table.render.panel.common.ScrollPanel;

/**
 * @author jonny
 */
public class AbstractScrollPanel extends ScrollPanel {
	/**
	 * 
	 */
	public AbstractScrollPanel(String name, Type horzType, Type vertType, RangeModel horzRangModel, RangeModel vertRangeModel) {
		super(name, horzType, vertType, horzRangModel, vertRangeModel);
		addListener(MouseWheelListener.class, new MouseWheelListener.Stub());
	}
	
	/**
	 * 
	 */
	public void pageUp(boolean isVertical) {
		ScrollBar vsb = null;if(isVertical) vsb = getVertBar();
		else vsb = getHorzBar(); if(!vsb.isVisible()) return;
		vsb.setValue(vsb.getValue() - vsb.getStepIncrement());
	}
	
	public void pageDown(boolean isVertical) {
		ScrollBar vsb = null;if(isVertical) vsb = getVertBar(); 
		else vsb = getHorzBar(); if(!vsb.isVisible()) return;
		vsb.setValue(vsb.getValue() + vsb.getStepIncrement());
	}
	
	/**
	 * 
	 */
	public void setVerticalScrollStep(int step) {
		ScrollBar vsb = getVertBar(); vsb.setStepIncrement(step);
	}
	
	public void setHorizontalScrollStep(int step) {
		ScrollBar vsb = getHorzBar(); vsb.setStepIncrement(step);
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
