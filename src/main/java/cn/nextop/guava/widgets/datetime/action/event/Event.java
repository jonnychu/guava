package cn.nextop.guava.widgets.datetime.action.event;

import org.eclipse.draw2d.Figure;

import cn.nextop.guava.widgets.datetime.action.actor.ActorManager.ActionType;
import cn.nextop.guava.widgets.datetime.support.glossary.Type;

/**
 * @author jonny
 */
public class Event {
	//
	private Type type;
	private EventType eventType;
	private ActionType actionType;
	private Figure source, container, widget;
	//
	public enum EventType{ COMMON, MONTH, YEAR, YEAR1, YEAR2, SHOW_TIME, SHOW_DATE };
	
	/**
	 * 
	 * @param eventType
	 * @param actionType
	 * @param type
	 * @param source
	 * @param container
	 * @param widget
	 */
	public Event(EventType eventType, Type type, Figure source, Figure container,
			Figure widget) {
		this.type = type;
		this.source = source;
		this.widget = widget;
		this.eventType = eventType;
		this.container = container;
	}
	
	public Event(EventType eventType, ActionType actionType, Figure source, Figure container,
			Figure widget) {
		this.source = source;
		this.widget = widget;
		this.eventType = eventType;
		this.container = container;
		this.actionType = actionType;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public Figure getSource() {
		return source;
	}

	public void setSource(Figure source) {
		this.source = source;
	}

	public Figure getContainer() {
		return container;
	}

	public void setContainer(Figure container) {
		this.container = container;
	}

	public Figure getWidget() {
		return widget;
	}

	public void setWidget(Figure widget) {
		this.widget = widget;
	}
}
