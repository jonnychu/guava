package cn.nextop.guava.widgets.datetime.render.popup.shortcut;

import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.datetime.render.AbstractPanel;
import cn.nextop.guava.widgets.datetime.render.popup.PopupPanel;

/**
 * @author jonny
 */
public class ShortcutPanel extends AbstractPanel {
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
		add(new Item("NOW")); add(new Item("SOD")); add(new Item("EOD"));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void layoutManager(IFigure container) {
		ShortcutPanel parent = (ShortcutPanel)container;
		Rectangle r = parent.getBounds();
		//
		List<Item> items = parent.getChildren();
		int p = 0, h = 24; for (Item item : items) {
			item.setBounds(new Rectangle(r.x, p++ * h, r.width, h));
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
			g.fillRectangle(rect);
			
			//
			Dimension d1 = INSTANCE.getStringExtents(text, g.getFont());
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
