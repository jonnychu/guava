package cn.nextop.guava.widgets.datetime.action.actor;

import static cn.nextop.guava.support.Objects.cast;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.Figure;

import cn.nextop.guava.widgets.AbstractActor;
import cn.nextop.guava.widgets.datetime.action.actor.date.impl.DateActor;
import cn.nextop.guava.widgets.datetime.action.actor.date.impl.MonthActor;
import cn.nextop.guava.widgets.datetime.action.actor.date.impl.ShowDateActor;
import cn.nextop.guava.widgets.datetime.action.actor.date.impl.YearActor;
import cn.nextop.guava.widgets.datetime.action.actor.month.impl.Month1Actor;
import cn.nextop.guava.widgets.datetime.action.actor.month.impl.ShowMonthActor;
import cn.nextop.guava.widgets.datetime.action.actor.month.impl.Year2Actor;
import cn.nextop.guava.widgets.datetime.action.actor.shortcut.impl.ShortcutActor;
import cn.nextop.guava.widgets.datetime.action.actor.text.impl.ShowTextActor;
import cn.nextop.guava.widgets.datetime.action.actor.time.impl.HourActor;
import cn.nextop.guava.widgets.datetime.action.actor.time.impl.MinuteActor;
import cn.nextop.guava.widgets.datetime.action.actor.time.impl.SecondActor;
import cn.nextop.guava.widgets.datetime.action.actor.time.impl.ShowTimeActor;
import cn.nextop.guava.widgets.datetime.action.actor.year.impl.ShowYearActor;
import cn.nextop.guava.widgets.datetime.action.actor.year.impl.Year3Actor;
import cn.nextop.guava.widgets.datetime.action.actor.year.impl.Year4Actor;
import cn.nextop.guava.widgets.datetime.render.AbstractTimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.CalendarPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.time.TimePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.YearPanel;
import cn.nextop.guava.widgets.datetime.support.glossary.PanelType;
import cn.nextop.guava.widgets.datetime.support.glossary.Type;

/**
 * @author jonny
 */
public class ActorManager {
	//
	private final Map<ActionType, AbstractActor> actions;
	
	//
	public enum ActionType {
		// text & shortcut
		TEXT_SHOW, SHORTCUT,
		// time
		TIME_HOUR, TIME_MINUTE, TIME_SECOND, TIME_SHOW,
		// year
		YEAR_YEAR2, YEAR_YEAR_SELECT, YEAR_YEAR_DOWN, YEAR_YEAR_UP, YEAR_SHOW,
		// month
		MONTH_MONTH, MONTH_SHOW, MONTH_YEAR_SELECT, MONTH_YEAR_DOWN, MONTH_YEAR_UP,
		// date
		DATE_DATE, DATE_MONTH_SELECT, DATE_MONTH_DOWN, DATE_MONTH_UP, DATE_SHOW, DATE_YEAR_SELECT, DATE_YEAR_DOWN, DATE_YEAR_UP
	}
	
	/**
	 * 
	 */
	public ActorManager() {
		actions = new HashMap<>();
		// Shortcut
		actions.put(ActionType.SHORTCUT, new ShortcutActor());
		// Text
		actions.put(ActionType.TEXT_SHOW, new ShowTextActor());
		// Time
		actions.put(ActionType.TIME_HOUR, new HourActor());
		actions.put(ActionType.TIME_MINUTE, new MinuteActor());
		actions.put(ActionType.TIME_SECOND, new SecondActor());
		actions.put(ActionType.TIME_SHOW, new ShowTimeActor());
		// Date
		actions.put(ActionType.DATE_DATE, new DateActor());
		actions.put(ActionType.DATE_SHOW, new ShowDateActor());
		actions.put(ActionType.DATE_YEAR_UP, new YearActor(Type.UP));
		actions.put(ActionType.DATE_MONTH_UP, new MonthActor(Type.UP));
		actions.put(ActionType.DATE_YEAR_DOWN, new YearActor(Type.DOWN));
		actions.put(ActionType.DATE_MONTH_DOWN, new MonthActor(Type.DOWN));
		actions.put(ActionType.DATE_YEAR_SELECT, new YearActor(Type.SELECT));
		actions.put(ActionType.DATE_MONTH_SELECT, new MonthActor(Type.SELECT));
		// Month
		actions.put(ActionType.MONTH_MONTH, new Month1Actor());
		actions.put(ActionType.MONTH_SHOW, new ShowMonthActor());
		actions.put(ActionType.MONTH_YEAR_UP, new Year2Actor(Type.UP));
		actions.put(ActionType.MONTH_YEAR_DOWN, new Year2Actor(Type.DOWN));
		actions.put(ActionType.MONTH_YEAR_SELECT, new Year2Actor(Type.SELECT));
		// Year
		actions.put(ActionType.YEAR_YEAR2, new Year4Actor());
		actions.put(ActionType.YEAR_SHOW, new ShowYearActor());
		actions.put(ActionType.YEAR_YEAR_UP, new Year3Actor(Type.UP));
		actions.put(ActionType.YEAR_YEAR_DOWN, new Year3Actor(Type.DOWN));
		actions.put(ActionType.YEAR_YEAR_SELECT, new Year3Actor(Type.SELECT));
	}
	
	/**
	 * 
	 */
	public ActorManager onAction(ActionType type, Figure container, Figure widget) {
		actions.get(type).onAction(container, widget); return this;
	}
	
	public void onAction(ActionType type1, Figure container1, Figure widget1, 
			ActionType type2, Figure container2, Figure widget2) {
		actions.get(type1).onAction(container1, widget1); 
		actions.get(type2).onAction(container2, widget2);
	}
	
	/**
	 * 
	 */
	public void onMonthAction(Type type, Figure container, Figure widget) {
		ActionType type1 = null;
		if(Type.UP == type) { type1 = ActionType.DATE_MONTH_UP; }
		else if(Type.DOWN == type) { type1 = ActionType.DATE_MONTH_DOWN; }
		else if(Type.SELECT == type) { type1 = ActionType.DATE_MONTH_SELECT; }
		
		final DatePanel dp = cast(container); 
		final MonthPanel mp = dp.getFactory().getMonthPanel();
		actions.get(type1).onAction(container, widget);
		if(type == Type.SELECT) actions.get(ActionType.MONTH_SHOW).onAction(mp, null); 
	}
	
	public void onYearAction(Type type, Figure container, Figure widget) {
		ActionType type1 = null;
		if(Type.UP == type) { type1 = ActionType.DATE_YEAR_UP; }
		else if(Type.DOWN == type) { type1 = ActionType.DATE_YEAR_DOWN; }
		else if(Type.SELECT == type) { type1 = ActionType.DATE_YEAR_SELECT; }
		
		final DatePanel dp = cast(container); 
		YearPanel yp = dp.getFactory().getYearPanel();
		actions.get(type1).onAction(container, widget);
		if(type == Type.SELECT) actions.get(ActionType.YEAR_SHOW).onAction(yp, null); 
	}
	
	public void onYearAction1(Type type, Figure container, Figure widget) {
		ActionType type1 = null;
		if(Type.UP == type) { type1 = ActionType.MONTH_YEAR_UP; }
		else if(Type.DOWN == type) { type1 = ActionType.MONTH_YEAR_DOWN; }
		else if(Type.SELECT == type) { type1 = ActionType.MONTH_YEAR_SELECT; }
		
		final MonthPanel mp = cast(container); 
		YearPanel yp = mp.getFactory().getYearPanel();
		onAction(type1, mp, widget, ActionType.YEAR_SHOW, yp, null);
	}
	
	public void onYearAction2(Type type, Figure container, Figure widget) {
		ActionType type1 = null;
		if(Type.UP == type) { type1 = ActionType.YEAR_YEAR_UP; }
		else if(Type.DOWN == type) { type1 = ActionType.YEAR_YEAR_DOWN; }
		else if(Type.SELECT == type) { type1 = ActionType.YEAR_YEAR_SELECT; }
		
		final YearPanel yp = cast(container); 
		onAction(type1, yp, widget, ActionType.YEAR_SHOW, yp, null);
	}
	
	public void onShowDatePanelAction(ActionType type, Figure container, Figure widget) {
		final TimePanel tp = cast(container);
		DatePanel dp = tp.getFactory().getDatePanel();
		CalendarPanel cp = tp.getFactory().getCalendarPanel();
		cp.panel(PanelType.DATE); actions.get(type).onAction(dp, null);
	}
	
	public void onShowTimePanelAction(ActionType type, Figure container, Figure widget) {
		AbstractTimePanel panel = cast(container);
		TimePanel tp = panel.getFactory().getTimePanel();
		CalendarPanel cp = panel.getFactory().getCalendarPanel();
		cp.panel(PanelType.TIME); actions.get(type).onAction(tp, null);
	}
}
