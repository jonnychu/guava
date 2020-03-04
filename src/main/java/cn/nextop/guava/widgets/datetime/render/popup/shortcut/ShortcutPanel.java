package cn.nextop.guava.widgets.datetime.render.popup.shortcut;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.draw2d.ScrollEvent;
import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.AbstractScrollPanel;
import cn.nextop.guava.widgets.datetime.render.popup.PopupPanel;
import cn.nextop.guava.widgets.datetime.render.popup.shortcut.widget.Item;

/**
 * @author jonny
 */
public class ShortcutPanel extends AbstractScrollPanel {
	//
	private PopupPanel popupPanel;
	private final int itemHeight = 24;
	
	/**
	 * 
	 */
	public PopupPanel getPopupPanel() { return popupPanel; }

	/**
	 * 
	 */
	public ShortcutPanel(PopupPanel popupPanel) {
		this.popupPanel = popupPanel;
		setVerticalScrollStep(itemHeight);
		setHorizontalScrollBarVisibility(NEVER);
		setVerticalScrollBarVisibility(AUTOMATIC);
		//
		Item i1 = new Item("NOW"), i2 = new Item("SOD"), i3 =  new Item("EOD");
		Content content = new Content(new Item[] {i1, i2, i3}); setContents(content);
	}
	
	@Override
	public void handleMouseWheel(ScrollEvent event) {
		super.handleMouseWheel(event);
		if(event.count > 0) pageUp(true); else pageDown(true);
	}
	
	/**
	 * 
	 */
	protected class Content extends AbstractPanel {
		//
		private Item[] items;
		
		/**
		 * 
		 */
		public Content(Item[] items) {
			this.items = items;	for (Item item : items) { add(item); }
		}
		
		@Override
		protected boolean useLocalCoordinates() { return true; }
		
		@Override
		protected void layoutManager(IFigure container) {
			Content parent = (Content)container;
			Rectangle r = parent.getBounds();
			//
			int p = 0, h = itemHeight; for (Item item : this.items) {
				item.setBounds(new Rectangle(r.x, p++ * h, r.width, h));
			}
			parent.setBounds(new Rectangle(0, 0, r.width, p * h));
		}
	}
}
