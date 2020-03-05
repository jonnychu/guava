package cn.nextop.guava.widgets.datetime.render.popup.calendar;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.XDateTime;
import cn.nextop.guava.widgets.datetime.XDateTimePopup;
import cn.nextop.guava.widgets.datetime.glossary.PanelType;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.PopupPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.YearPanel;

/**
 * @author jonny
 */
public class CalendarPanel extends AbstractPanel {
	//
	private XDateTime dateTime;
	private XDateTimeModel dateTimeModel;
	private XDateTimePopup dateTimePopup;
	private DatePanel datePanel;
	private YearPanel yearPanel;
	private MonthPanel monthPanel;
	private PopupPanel popupPanel;
	private DummyCalendar dummyCalendar;
	
	/**
	 * 
	 */
	public XDateTime getDateTime() { return dateTime; }
	public DatePanel getDatePanel() { return datePanel; }
	public YearPanel getYearPanel() { return yearPanel; }
	public PopupPanel getPopupPanel() { return popupPanel; }
	public MonthPanel getMonthPanel() { return monthPanel; }
	public DummyCalendar getDummyCalendar() { return dummyCalendar;	}
	public XDateTimeModel getDateTimeModel() { return dateTimeModel; }
	public XDateTimePopup getDateTimePopup() { return dateTimePopup; }
	/**
	 * 
	 */
	public CalendarPanel(PopupPanel popupPanel) {
		this.popupPanel = popupPanel;
		this.dateTimePopup = popupPanel.getPopup();
		this.dateTime = dateTimePopup.getDateTime();
		this.dateTimeModel = dateTime.getModel();
		this.dummyCalendar = new DummyCalendar(dateTimeModel.getTime1());
		//
		add(datePanel = new DatePanel(this)); add(yearPanel = new YearPanel(this));
		add(monthPanel = new MonthPanel(this));	panel(PanelType.DATE); // switch first panel
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		datePanel.setBounds(container.getBounds());
		yearPanel.setBounds(container.getBounds());
		monthPanel.setBounds(container.getBounds());
	}
	
	/**
	 * 
	 */
	public void panel(PanelType type) {
		datePanel.setVisible(false);
		yearPanel.setVisible(false);
		monthPanel.setVisible(false);
		switch (type) {
		case TIME:  datePanel.setVisible(true); break;
		case DATE:  datePanel.setVisible(true); break;
		case YEAR:  yearPanel.setVisible(true); break;
		case MONTH: monthPanel.setVisible(true); break;
		default: throw new RuntimeException("No Panel Type!");}
	}
}
