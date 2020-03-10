package cn.nextop.guava.widgets.datetime.render.popup.calendar;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.render.AbstractTimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.PopupPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.YearPanel;
import cn.nextop.guava.widgets.datetime.support.glossary.PanelType;

/**
 * @author jonny
 */
public class CalendarPanel extends AbstractTimePanel {
	//
	private TimePanel timePanel;
	private DatePanel datePanel;
	private YearPanel yearPanel;
	private MonthPanel monthPanel;
	private PopupPanel popupPanel;
	
	/**
	 * 
	 */
	public TimePanel getTimePanel() { return timePanel; }
	public DatePanel getDatePanel() { return datePanel; }
	public YearPanel getYearPanel() { return yearPanel; }
	public PopupPanel getPopupPanel() { return popupPanel; }
	public MonthPanel getMonthPanel() { return monthPanel; }
	/**
	 * 
	 */
	public CalendarPanel(PopupPanel popupPanel) {
		super("calendarpanel"); this.popupPanel = popupPanel;
		//
		add(datePanel = new DatePanel(this)); add(yearPanel = new YearPanel(this));
		add(monthPanel = new MonthPanel(this));	add(timePanel = new TimePanel(this)); 
		panel(PanelType.DATE); // switch first panel
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		timePanel.setBounds(container.getBounds());
		datePanel.setBounds(container.getBounds());
		yearPanel.setBounds(container.getBounds());
		monthPanel.setBounds(container.getBounds());
	}
	
	/**
	 * 
	 */
	public void panel(PanelType type) {
		timePanel.setVisible(false);
		datePanel.setVisible(false);
		yearPanel.setVisible(false);
		monthPanel.setVisible(false);
		switch (type) {
		case TIME:  timePanel.setVisible(true); break;
		case DATE:  datePanel.setVisible(true); break;
		case YEAR:  yearPanel.setVisible(true); break;
		case MONTH: monthPanel.setVisible(true); break;
		default: throw new RuntimeException("No Panel Type!");}
	}
}
