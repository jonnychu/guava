package cn.nextop.guava.widgets.datetime.render;

import java.util.Iterator;

import org.eclipse.draw2d.ScrollBar;
import org.eclipse.draw2d.ScrollPane;

import cn.nextop.guava.draw2d.scroll.MouseWheelListener;
import cn.nextop.guava.draw2d.scroll.ScrollEvent;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.HourPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min.MinPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec.SecPanel;

/**
 * @author jonny
 */
public class AbstractScrollPanel extends ScrollPane {
	//
	private final String name;
	
	//
	public String getName() { return name; }

	/**
	 * 
	 */
	public AbstractScrollPanel(String name) {
		this.name = name;
		addListener(MouseWheelListener.class, new MouseWheelListener.Stub());
	}
	
	/**
	 * 
	 */
	public void pageUp(boolean isVertical) {
		ScrollBar vsb = null;if(isVertical) vsb = getVerticalScrollBar(); 
		else vsb = getHorizontalScrollBar(); if(!vsb.isVisible()) return;
		vsb.setValue(vsb.getValue() - vsb.getStepIncrement());
	}
	
	public void pageDown(boolean isVertical) {
		ScrollBar vsb = null;if(isVertical) vsb = getVerticalScrollBar(); 
		else vsb = getHorizontalScrollBar(); if(!vsb.isVisible()) return;
		vsb.setValue(vsb.getValue() + vsb.getStepIncrement());
	}
	
	/**
	 * 
	 */
	public void setVerticalScrollStep(int step) {
		ScrollBar vsb = getVerticalScrollBar(); vsb.setStepIncrement(step);
	}
	
	public void setHorizontalScrollStep(int step) {
		ScrollBar vsb = getVerticalScrollBar(); vsb.setStepIncrement(step);
	}
	
	/**
	 * 
	 */
	public void handleMouseWheel(ScrollEvent event) {
		@SuppressWarnings("rawtypes")
		Iterator iter = getListeners(MouseWheelListener.class);
		while (iter.hasNext()) ((MouseWheelListener) iter.next()).mouseScrolled(event);
	}
	
	/**
	 * Faster Helper
	 */
	public DummyCalendar getDummyCalendarFromSecond() {
		if(this instanceof SecPanel) 
			return ((SecPanel)this).getTimePanel().getCalendarPanel().getPopupPanel().getXDateTimePopup().getDummyCalendar();
		return null;
	}
	
	public DummyCalendar getDummyCalendarFromMinute() {
		if(this instanceof MinPanel) 
			return ((MinPanel)this).getTimePanel().getCalendarPanel().getPopupPanel().getXDateTimePopup().getDummyCalendar();
		return null;
	}
	
	public DummyCalendar getDummyCalendarFromHour() {
		if(this instanceof HourPanel) 
			return ((HourPanel)this).getTimePanel().getCalendarPanel().getPopupPanel().getXDateTimePopup().getDummyCalendar();
		return null;
	}
}
