package cn.nextop.guava.widgets.datetime.render.popup.calendar;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;

import cn.nextop.guava.widgets.datetime.render.popup.PopupPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;

public class CalendarPanel extends Figure {
	//
	private DatePanel datePanel;
	private PopupPanel popupPanel;
	
	/**
	 * 
	 */
	public CalendarPanel(PopupPanel popupPanel) {
		this.popupPanel = popupPanel;
		add(datePanel = new DatePanel(this));
	}
	
	@Override
	protected void paintChildren(Graphics g) {
		super.paintChildren(g); datePanel.setBounds(getBounds());
	}
	
	@Override
	public boolean isOpaque() {
		return true;
	}
}
