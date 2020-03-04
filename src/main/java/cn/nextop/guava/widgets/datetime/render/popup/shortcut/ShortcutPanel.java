package cn.nextop.guava.widgets.datetime.render.popup.shortcut;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScrollBar;
import org.eclipse.draw2d.Viewport;
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
	
	/**
	 * 
	 */
	public PopupPanel getPopupPanel() { return popupPanel; }

	/**
	 * 
	 */
	public ShortcutPanel(PopupPanel popupPanel) {
		this.popupPanel = popupPanel;
		setVerticalScrollBarVisibility(ALWAYS);
		setHorizontalScrollBarVisibility(NEVER);
		Viewport viewport = getViewport();
		viewport.setContentsTracksWidth(true);
		viewport.setContentsTracksHeight(true);
		
		Item[] r = new Item[] {new Item("NOW"), new Item("SOD"), new Item("EOD")
				,new Item("NOW"), new Item("SOD"), new Item("EOD1")
				,new Item("NOW"), new Item("SOD"), new Item("EOD2")
				,new Item("NOW"), new Item("SOD"), new Item("EOD3")
				,new Item("NOW"), new Item("SOD"), new Item("EOD4")
				,new Item("NOW"), new Item("SOD"), new Item("EOD5")
				,new Item("NOW"), new Item("SOD"), new Item("EOD6")
				,new Item("NOW"), new Item("SOD"), new Item("EOD7")
				,new Item("NOW"), new Item("SOD"), new Item("EOD8")};
		Content content = new Content(r); setContents(content);
	}
	
	@Override
	public void handleMouseWheel(ScrollEvent event) {
		super.handleMouseWheel(event);
		ScrollBar vsb = getVerticalScrollBar(); if(!vsb.isVisible()) return;
		if(event.count > 0) vsb.setValue(vsb.getValue() - vsb.getPageIncrement());
		else vsb.setValue(vsb.getValue() + vsb.getPageIncrement());
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
			this.items = items;
			for (Item item : items) { add(item); }
		}
		
		@Override
		protected void layoutManager(IFigure container) {
			Content parent = (Content)container;
			Rectangle r = parent.getBounds();
			//
			int p = 0, h = 24; for (Item item : this.items) {
				item.setBounds(new Rectangle(r.x, p++ * h, r.width, h));
			}
			parent.setBounds(new Rectangle(0, 0, r.width, p * h));
		}
	}
}
