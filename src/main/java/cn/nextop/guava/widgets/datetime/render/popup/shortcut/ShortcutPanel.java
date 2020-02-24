package cn.nextop.guava.widgets.datetime.render.popup.shortcut;

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.render.popup.PopupPanel;

public class ShortcutPanel extends Figure {
	//
	private Layout layout;
	private PopupPanel popupPanel;
	
	/**
	 * 
	 */
	public ShortcutPanel(PopupPanel popupPanel) {
		this.layout = new Layout();
		this.popupPanel = popupPanel;
		add(new Item("NOW")); add(new Item("SOD")); add(new Item("EOD"));
	}
	
	@Override
	protected void paintChildren(Graphics graphics) {
		super.paintChildren(graphics); layout.layout(this);
	}
	
	/**
	 * 
	 */
	protected class Layout {
		//
		@SuppressWarnings("unchecked")
		public void layout(ShortcutPanel parent) {
			Rectangle r = parent.getClientArea();
			//
			List<Item> items = parent.getChildren();
			int p = 0, h = 24; for (Item item : items) {
				item.setBounds(new Rectangle(r.x, p++ * h, r.width, h));
			}
		}
	}
	
	protected class Item extends Figure {
		//
		private String text = "";
		private boolean selected;
		
		public Item(String text) {
			this.text = text;
			addMouseMotionListener(new MouseMotionListener.Stub());
		}
		
		@Override
		protected void paintFigure(Graphics g) {
			super.paintFigure(g);
			Rectangle rect = getBounds();
			if(selected)
				g.setBackgroundColor(Colors.COLOR_DARK_CYAN);
			else
				g.setBackgroundColor(Colors.COLOR_WIDGET_BACKGROUND);
			g.fillRoundRectangle(rect, 5, 5);
			
			//
			Dimension d1 = TextUtilities.INSTANCE.getStringExtents(text, g.getFont());
			g.drawString(text, rect.x + 10, rect.y + (rect.height - d1.height) / 2);
		}
		
		@Override
		public void handleMouseEntered(MouseEvent event) {
			super.handleMouseEntered(event); selected = true; repaint();
		}
		
		@Override
		public void handleMouseExited(MouseEvent event) {
			super.handleMouseExited(event); selected = false; repaint();
		}
	}
}
