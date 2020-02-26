package cn.nextop.guava.widgets.datetime.render.popup.calendar;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.PopupPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;

/**
 * @author jonny
 */
public class CalendarPanel extends AbstractPanel {
	//
	private DatePanel datePanel;
	private PopupPanel popupPanel;
	
	/**
	 * 
	 */
	public DatePanel getDatePanel() { return datePanel; }
	public PopupPanel getPopupPanel() { return popupPanel; }

	/**
	 * 
	 */
	public CalendarPanel(PopupPanel popupPanel) {
		this.popupPanel = popupPanel;
		add(datePanel = new DatePanel(this));
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		datePanel.setBounds(container.getBounds());
	}
}
