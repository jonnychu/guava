package cn.nextop.guava.widgets.datetime.render.popup.calendar;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.PopupPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;

/**
 * @author jonny
 */
public class CalendarPanel extends AbstractPanel {
	//
	private DatePanel datePanel;
	private MonthPanel monthPanel;
	private PopupPanel popupPanel;
	
	/**
	 * 
	 */
	public DatePanel getDatePanel() { return datePanel; }
	public PopupPanel getPopupPanel() { return popupPanel; }
	public MonthPanel getMonthPanel() { return monthPanel; }
	
	/**
	 * 
	 */
	public CalendarPanel(PopupPanel popupPanel) {
		this.popupPanel = popupPanel;
		add(datePanel = new DatePanel(this));
		add(monthPanel = new MonthPanel(this));
		monthPanel.setVisible(false);
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		datePanel.setBounds(container.getBounds());
		monthPanel.setBounds(container.getBounds());
		System.out.println(container.getBounds());
	}
}
