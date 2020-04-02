package cn.nextop.guava.widgets.datetime.action;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.Figure;

import cn.nextop.guava.widgets.AbstractAction;
import cn.nextop.guava.widgets.datetime.action.date.DateAction;
import cn.nextop.guava.widgets.datetime.action.date.MonthAction;
import cn.nextop.guava.widgets.datetime.action.date.ShowDateAction;
import cn.nextop.guava.widgets.datetime.action.date.YearAction;
import cn.nextop.guava.widgets.datetime.action.month.ShowMonthAction;
import cn.nextop.guava.widgets.datetime.action.text.ShowTextAction;
import cn.nextop.guava.widgets.datetime.action.time.HourAction;
import cn.nextop.guava.widgets.datetime.action.time.MinuteAction;
import cn.nextop.guava.widgets.datetime.action.time.SecondAction;
import cn.nextop.guava.widgets.datetime.action.time.ShowTimeAction;
import cn.nextop.guava.widgets.datetime.action.year.ShowYearAction;
import cn.nextop.guava.widgets.datetime.action.year.Year2Action;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.date.DatePanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.month.MonthPanel;
import cn.nextop.guava.widgets.datetime.render.popup.calendar.year.YearPanel;
import cn.nextop.guava.widgets.datetime.support.glossary.Type;

public class ActionFactory {
	//
	private Map<ActionType, AbstractAction> actions;
	
	//
	public enum ActionType {
		// text
		TEXT_SHOW,
		// time
		TIME_HOUR, TIME_MINUTE, TIME_SECOND, TIME_SHOW,
		// year
		YEAR_YEAR2, YEAR_YEAR_SELECT, YEAR_YEAR_DOWN, YEAR_YEAR_UP, YEAR_SHOW,
		// month
		MONTH_MONTH, MONTH_SHOW, MONTH_YEAR_SELECT, MONTH_YEAR_DOWN, MONTH_YEAR_UP,
		// date
		DATE_DATE, DATE_MONTH_SELECT, DATE_MONTH_DOWN, DATE_MONTH_UP, DATE_SHOW, DATE_YEAR_SELECT, DATE_YEAR_DOWN, DATE_YEAR_UP
	}
	
	
	
	public ActionFactory() {
		actions = new HashMap<ActionFactory.ActionType, AbstractAction>();
		//
		actions.put(ActionType.TEXT_SHOW, new ShowTextAction());
		// Date
		actions.put(ActionType.DATE_DATE, new DateAction());
		actions.put(ActionType.DATE_MONTH_SELECT, new MonthAction(Type.SELECT));
		actions.put(ActionType.DATE_MONTH_DOWN, new MonthAction(Type.DOWN));
		actions.put(ActionType.DATE_MONTH_UP, new MonthAction(Type.UP));
		actions.put(ActionType.DATE_SHOW, new ShowDateAction());
		actions.put(ActionType.DATE_YEAR_SELECT, new YearAction(Type.SELECT));
		actions.put(ActionType.DATE_YEAR_DOWN, new YearAction(Type.DOWN));
		actions.put(ActionType.DATE_YEAR_UP, new YearAction(Type.UP));
		// Month
		actions.put(ActionType.MONTH_MONTH, new cn.nextop.guava.widgets.datetime.action.month.MonthAction());
		actions.put(ActionType.MONTH_SHOW, new ShowMonthAction());
		actions.put(ActionType.MONTH_YEAR_SELECT, new cn.nextop.guava.widgets.datetime.action.month.YearAction(Type.SELECT));
		actions.put(ActionType.MONTH_YEAR_DOWN, new cn.nextop.guava.widgets.datetime.action.month.YearAction(Type.DOWN));
		actions.put(ActionType.MONTH_YEAR_UP, new cn.nextop.guava.widgets.datetime.action.month.YearAction(Type.UP));
		// Year
		actions.put(ActionType.YEAR_YEAR2, new Year2Action());
		actions.put(ActionType.YEAR_YEAR_SELECT, new cn.nextop.guava.widgets.datetime.action.year.YearAction(Type.SELECT));
		actions.put(ActionType.YEAR_YEAR_DOWN, new cn.nextop.guava.widgets.datetime.action.year.YearAction(Type.DOWN));
		actions.put(ActionType.YEAR_YEAR_UP, new cn.nextop.guava.widgets.datetime.action.year.YearAction(Type.UP));
		actions.put(ActionType.YEAR_SHOW, new ShowYearAction());
		// Time
		actions.put(ActionType.TIME_HOUR, new HourAction());
		actions.put(ActionType.TIME_MINUTE, new MinuteAction());
		actions.put(ActionType.TIME_SECOND, new SecondAction());
		actions.put(ActionType.TIME_SHOW, new ShowTimeAction());
	}
	
	/**
	 * 
	 */
	public void onAction(ActionType type, Figure container, Figure widget) {
		actions.get(type).onAction(container, widget);
	}
	
	public void onAction(ActionType type1, Figure container1, Figure widget1, 
			ActionType type2, Figure container2, Figure widget2) {
		actions.get(type1).onAction(container1, widget1); 
		actions.get(type2).onAction(container2, widget2);
	}
	
	public void onAction(ActionType type1, Figure container1, Figure widget1, 
			ActionType type2, Figure container2, Figure widget2, 
			ActionType type3, Figure container3, Figure widget3) {
		actions.get(type1).onAction(container1, widget1); 
		actions.get(type2).onAction(container2, widget2);
		actions.get(type3).onAction(container3, widget3);
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
		final MonthPanel mp = dp.getBuilder().getMonthPanel();
		actions.get(type1).onAction(container, widget);
		if(type == Type.SELECT) actions.get(ActionType.MONTH_SHOW).onAction(mp, null); 
	}
	
	public void onYearAction(Type type, Figure container, Figure widget) {
		ActionType type1 = null;
		if(Type.UP == type) { type1 = ActionType.DATE_YEAR_UP; }
		else if(Type.DOWN == type) { type1 = ActionType.DATE_YEAR_DOWN; }
		else if(Type.SELECT == type) { type1 = ActionType.DATE_YEAR_SELECT; }
		
		final DatePanel dp = cast(container); 
		YearPanel yp = dp.getBuilder().getYearPanel();
		actions.get(type1).onAction(container, widget);
		if(type == Type.SELECT) actions.get(ActionType.YEAR_SHOW).onAction(yp, null); 
	}
	
	public void onYearAction1(Type type, Figure container, Figure widget) {
		ActionType type1 = null;
		if(Type.UP == type) { type1 = ActionType.MONTH_YEAR_UP; }
		else if(Type.DOWN == type) { type1 = ActionType.MONTH_YEAR_DOWN; }
		else if(Type.SELECT == type) { type1 = ActionType.MONTH_YEAR_SELECT; }
		
		final MonthPanel mp = cast(container); 
		YearPanel yp = mp.getBuilder().getYearPanel();
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
}
