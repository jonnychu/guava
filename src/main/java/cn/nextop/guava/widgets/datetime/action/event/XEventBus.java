package cn.nextop.guava.widgets.datetime.action.event;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import cn.nextop.guava.widgets.datetime.action.actor.ActorManager;

/**
 * @author jonny
 */
public class XEventBus {
	//
	private EventBus eventBus;
	private final String name = "datetime.eventbus";
	
	/**
	 * 
	 */
	public XEventBus() {
		this.eventBus = new EventBus(name);
		this.eventBus.register(new Reactor());
	}
	
	public XEventBus submit(Event event) {
		this.eventBus.post(event); return this;
	}
	
	/**
	 * 
	 */
	protected class Reactor {
		//
		private ActorManager manager;
		
		/**
		 * 
		 */
		public Reactor() {
			this.manager = new ActorManager();
		}
		
		@Subscribe
		public void reactor(Event e) {
			switch (e.getEventType()) {
			case MONTH:
				this.manager.onMonthAction(e.getType(), e.getContainer(), e.getWidget()); break;
			case YEAR:
				this.manager.onYearAction(e.getType(), e.getContainer(), e.getWidget()); break;
			case YEAR1:
				this.manager.onYearAction1(e.getType(), e.getContainer(), e.getWidget()); break;
			case YEAR2:
				this.manager.onYearAction2(e.getType(), e.getContainer(), e.getWidget()); break;
			case COMMON:
				this.manager.onAction(e.getActionType(), e.getContainer(), e.getWidget()); break;
			case SHOW_TIME:
				this.manager.onShowTimePanelAction(e.getActionType(), e.getContainer(), e.getWidget()); break;
			case SHOW_DATE:
				this.manager.onShowDatePanelAction(e.getActionType(), e.getContainer(), e.getWidget()); break;
			default:
				throw new RuntimeException("invalid event");
			}
		}
	}
}
