package cn.nextop.guava.widgets.datetime.render.popup.shortcut;

import cn.nextop.guava.draw2d.ScrollEvent;
import cn.nextop.guava.widgets.datetime.XDateTime;
import cn.nextop.guava.widgets.datetime.XDateTimePopup;
import cn.nextop.guava.widgets.datetime.glossary.Shortcut;
import cn.nextop.guava.widgets.datetime.model.DummyCalendar;
import cn.nextop.guava.widgets.datetime.model.XDateTimeModel;
import cn.nextop.guava.widgets.datetime.render.AbstractScrollPanel;
import cn.nextop.guava.widgets.datetime.render.popup.PopupPanel;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.widget.Item;

/**
 * @author jonny
 */
public class ShortcutPanel extends AbstractScrollPanel {
	//
	public static final int itemHeight = 24;
	//
	private XDateTime dateTime;
	private PopupPanel popupPanel;
	private DummyCalendar dummyCalendar;
	private XDateTimeModel dateTimeModel;
	private XDateTimePopup dateTimePopup;
	
	/**
	 * 
	 */
	public XDateTime getDateTime() { return dateTime; }
	public PopupPanel getPopupPanel() { return popupPanel; }
	public DummyCalendar getDummyCalendar() { return dummyCalendar; }
	public XDateTimeModel getDateTimeModel() { return dateTimeModel; }
	public XDateTimePopup getDateTimePopup() { return dateTimePopup; }

	/**
	 * 
	 */
	public ShortcutPanel(PopupPanel popupPanel) {
		this.popupPanel = popupPanel;
		this.dateTimePopup = popupPanel.getPopup();
		this.dateTime = dateTimePopup.getDateTime();
		this.dateTimeModel = this.dateTime.getModel();
		this.dummyCalendar = popupPanel.getDummyCalendar();
		
		setVerticalScrollStep(itemHeight);
		setHorizontalScrollBarVisibility(NEVER);
		setVerticalScrollBarVisibility(AUTOMATIC);
		//
		Shortcut[] scs = Shortcut.values();
		Item[] items = new Item[scs.length];
		for (int i = 0; i < scs.length; i++) {
			items[i] = new Item(scs[i]);
		}
		Content content = new Content(items); setContents(content);
	}
	
	@Override
	public void handleMouseWheel(ScrollEvent event) {
		super.handleMouseWheel(event);
		if(event.count > 0) pageUp(true); else pageDown(true);
	}
}
