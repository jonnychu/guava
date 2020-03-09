package cn.nextop.guava.widgets.datetime.render.util;

import cn.nextop.guava.widgets.datetime.XDateTime;
import cn.nextop.guava.widgets.datetime.XDateTimePopup;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.popup.PopupPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.YearPanel;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.ShortcutContent;
import cn.nextop.guava.widgets.datetime.render.text.TextPanel;

/**
 * @author jonny
 */
public class Faster {
	
	private static XDateTime getXDateTime(PopupPanel pp) {
		XDateTimePopup dtp = getXDateTimePopup(pp);
		return dtp.getDateTime();
	}
	
	private static XDateTimePopup getXDateTimePopup(PopupPanel pp) {
		return pp.getXDateTimePopup();
	}
	
	/**
	 * 
	 */
	public static TextPanel getTextPanel(DatePanel dp) {
		return getXDateTime(dp.getCalendarPanel().getPopupPanel()).getTextPanel();
	}
	
	public static TextPanel getTextPanel(TimePanel tp) {
		return getXDateTime(tp.getCalendarPanel().getPopupPanel()).getTextPanel();
	}
	
	/**
	 * 
	 */
	public static XDateTimeModel getXDateTimeModel(DatePanel dp) {
		return getXDateTime(dp.getCalendarPanel().getPopupPanel()).getModel();
	}
	
	public static XDateTimeModel getXDateTimeModel(TimePanel tp) {
		return getXDateTime(tp.getCalendarPanel().getPopupPanel()).getModel();
	}
	
	public static XDateTimeModel getXDateTimeModel(ShortcutContent scp) {
		return getXDateTime(scp.getShortcutPanel().getPopupPanel()).getModel();
	}
	
	/**
	 * 
	 */
	public static DummyCalendar getDummyCalendar(DatePanel dp) {
		return getXDateTimePopup(dp.getCalendarPanel().getPopupPanel()).getDummyCalendar();
	}
	
	public static DummyCalendar getDummyCalendar(YearPanel yp) {
		return getXDateTimePopup(yp.getCalendarPanel().getPopupPanel()).getDummyCalendar();
	}
	
	public static DummyCalendar getDummyCalendar(MonthPanel mp) {
		return getXDateTimePopup(mp.getCalendarPanel().getPopupPanel()).getDummyCalendar();
	}
	
	public static DummyCalendar getDummyCalendar(TimePanel tp) {
		return getXDateTimePopup(tp.getCalendarPanel().getPopupPanel()).getDummyCalendar();
	}
	
	public static DummyCalendar getDummyCalendar(ShortcutContent scp) {
		return getXDateTimePopup(scp.getShortcutPanel().getPopupPanel()).getDummyCalendar();
	}
}
