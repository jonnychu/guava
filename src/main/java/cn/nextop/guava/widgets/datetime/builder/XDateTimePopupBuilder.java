package cn.nextop.guava.widgets.datetime.builder;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;

import org.eclipse.swt.widgets.Canvas;

import cn.nextop.guava.widgets.AbstractBuilder;
import cn.nextop.guava.widgets.datetime.XDateTimePopup;
import cn.nextop.guava.widgets.datetime.action.ActionFactory;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.render.popup.PopupPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.HourContent;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.hour.HourPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min.MinContent;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.min.MinPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec.SecContent;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.sec.SecPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.YearPanel;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.ShortcutContent;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.ShortcutPanel;
import cn.nextop.guava.widgets.datetime.render.text.TextPanel;
import cn.nextop.guava.widgets.datetime.support.glossary.PanelType;

/**
 * @author jonny
 */
public class XDateTimePopupBuilder extends AbstractBuilder {
	//
	private SecPanel secPanel;
	private MinPanel minPanel;
	private TimePanel timePanel;
	private DatePanel datePanel;
	private YearPanel yearPanel;
	private HourPanel hourPanel;
	private MonthPanel monthPanel;
	private PopupPanel popupPanel;
	private CalendarPanel calendarPanel;
	private ShortcutPanel shortcutPanel;
	private XDateTimePopup dateTimePopup;
	
	@Override
	public PopupPanel build(Canvas parent) {
		this.dateTimePopup = cast(parent);
		this.popupPanel = new PopupPanel("popup.panel", this);
		// shortcut
		this.popupPanel.add(this.shortcutPanel = buildShortcut());
		// calendar
		this.popupPanel.add(this.calendarPanel = buildCalendar());
		return this.popupPanel;
	}
	
	private ShortcutPanel buildShortcut() {
		ShortcutPanel panel = new ShortcutPanel("shortcut.panel");
		panel.setContents(new ShortcutContent("sc.content.panel", this));
		return panel;
	}
	
	private CalendarPanel buildCalendar() {
		CalendarPanel panel = new CalendarPanel("calendar.panel", this);
		// date
		panel.add(this.datePanel = new DatePanel("data.panel", this));
		// year
		panel.add(this.yearPanel = new YearPanel("year.panel", this));
		// month
		panel.add(this.monthPanel = new MonthPanel("month.panel", this));
		// time
		this.timePanel = new TimePanel("time.panel", this);
		{
			this.secPanel = new SecPanel(this.timePanel);
			this.secPanel.setContents(new SecContent("sec.content.panel", this));
			
			this.minPanel = new MinPanel(this.timePanel);
			this.minPanel.setContents(new MinContent("min.content.panel", this));
			
			this.hourPanel = new HourPanel(this.timePanel);
			this.hourPanel.setContents(new HourContent("hour.content.panel", this));
		}
		
		//
		this.timePanel.add(this.secPanel); 
		this.timePanel.add(this.minPanel);
		this.timePanel.add(this.hourPanel); 
		
		//
		panel.add(this.timePanel); panel.panel(PanelType.DATE); return panel;
	}
	
	/**
	 * 
	 */
	public SecPanel getSecPanel() {
		return secPanel;
	}

	public MinPanel getMinPanel() {
		return minPanel;
	}

	public HourPanel getHourPanel() {
		return hourPanel;
	}
	
	public TimePanel getTimePanel() {
		return timePanel;
	}

	public DatePanel getDatePanel() {
		return datePanel;
	}

	public YearPanel getYearPanel() {
		return yearPanel;
	}

	public MonthPanel getMonthPanel() {
		return monthPanel;
	}

	public PopupPanel getPopupPanel() {
		return popupPanel;
	}

	public CalendarPanel getCalendarPanel() {
		return calendarPanel;
	}

	public ShortcutPanel getShortcutPanel() {
		return shortcutPanel;
	}

	public XDateTimePopup getDateTimePopup() {
		return dateTimePopup;
	}
	
	/**
	 * Faster 
	 */
	public ActionFactory getActionFactory() {
		return getDateTimePopup().getActionFactory();
	}
	
	public DummyCalendar getDummyCalendar() {
		return getDateTimePopup().getDummyCalendar();
	}
	
	public TextPanel getTextPanel() {
		return getDateTimePopup().getDateTime().getTextPanel();
	}
}
