package cn.nextop.guava.widgets.datetime.render;

import java.util.List;

import org.eclipse.draw2d.AbstractHintLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;

import cn.nextop.guava.widgets.datetime.XDateTimePopup;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.YearPanel;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.ShortcutContent;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.ShortcutPanel;
import cn.nextop.guava.widgets.datetime.render.text.TextPanel;

/**
 * @author jonny
 */
public abstract class AbstractPanel extends Figure {
	//
	private final String name;
	protected final int margin = 8, arc = 5;
	
	//
	public String getName() { return name; }
	
	//
	protected abstract void layoutManager(IFigure container);
	protected Dimension calPreferredSize(IFigure container, int wHint, int hHint) { return null; }
	
	/**
	 * 
	 */
	public AbstractPanel(String name) {
		this.name = name;
		if(getLayoutManager() == null) setLayoutManager(new CustomLayout());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<IFigure> getChildren() { return super.getChildren(); }
	
	/**
	 * 
	 */
	protected class CustomLayout extends AbstractHintLayout {

		@Override public void layout(IFigure container) { layoutManager(container); }

		@Override
		protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {
			return calPreferredSize(container, wHint, hHint);
		}
	}
	
	/**
	 * Faster helper
	 */
	public TextPanel getTextPanelFromDate() {
		final CalendarPanel cp = ((DatePanel)this).getCalendarPanel();
		final XDateTimePopup dp = cp.getPopupPanel().getXDateTimePopup();
		return dp.getDateTime().getTextPanel();
	}
	
	public TextPanel getTextPanelFromTime() {
		final CalendarPanel cp = ((TimePanel)this).getCalendarPanel();
		final XDateTimePopup dp = cp.getPopupPanel().getXDateTimePopup();
		return dp.getDateTime().getTextPanel();
	}
	
	public XDateTimeModel getXDateTimeModelFromDate() {
		final CalendarPanel cp = ((DatePanel)this).getCalendarPanel();
		final XDateTimePopup dp = cp.getPopupPanel().getXDateTimePopup();
		return dp.getDateTime().getModel();
	}
	
	public XDateTimeModel getXDateTimeModelFromTime() {
		final CalendarPanel cp = ((TimePanel)this).getCalendarPanel();
		final XDateTimePopup dp = cp.getPopupPanel().getXDateTimePopup();
		return dp.getDateTime().getModel();
	}
	
	public XDateTimeModel getXDateTimeModelFromShortcut() {
		final ShortcutPanel cp = ((ShortcutContent)this).getShortcutPanel();
		final XDateTimePopup dp = cp.getPopupPanel().getXDateTimePopup();
		return dp.getDateTime().getModel();
	}
	
	public DummyCalendar getDummyCalendarFromDate() {
		if(this instanceof DatePanel) 
			return ((DatePanel)this).getCalendarPanel().getPopupPanel().getXDateTimePopup().getDummyCalendar();
		return null;
	}
	
	public DummyCalendar getDummyCalendarFromYear() {
		if(this instanceof YearPanel) 
			return ((YearPanel)this).getCalendarPanel().getPopupPanel().getXDateTimePopup().getDummyCalendar();
		return null;
	}
	
	public DummyCalendar getDummyCalendarFromMonth() {
		if(this instanceof MonthPanel) 
			return ((MonthPanel)this).getCalendarPanel().getPopupPanel().getXDateTimePopup().getDummyCalendar();
		return null;
	}
	
	public DummyCalendar getDummyCalendarFromTime() {
		if(this instanceof TimePanel) 
			return ((TimePanel)this).getCalendarPanel().getPopupPanel().getXDateTimePopup().getDummyCalendar();
		return null;
	}
	
	public DummyCalendar getDummyCalendarFromShortCut() {
		if(this instanceof ShortcutContent) 
			return ((ShortcutContent)this).getShortcutPanel().getPopupPanel().getXDateTimePopup().getDummyCalendar();
		return null;
	}
	
}
