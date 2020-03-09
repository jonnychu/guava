package cn.nextop.guava.widgets.combo.render;

import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Font;

import com.patrikdufresne.fontawesome.FontAwesome;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.utils.SwtUtils;
import cn.nextop.guava.widgets.combo.ComboPopup;
import cn.nextop.guava.widgets.combo.model.ComboItem;
import cn.nextop.guava.widgets.combo.model.ComboModel;

/**
 * @author jonny
 */
public class ComboPopupPanel extends Figure {
	//
	private List<Item> all;
	private ComboPopup popup;
	//
	@Override protected boolean useLocalCoordinates() { return true; }
	
	/**
	 * 
	 */
	public ComboPopupPanel(ComboPopup popup) {
		this.popup = popup;
		this.all = new ArrayList<>();
		FlowLayout layout = new FlowLayout(false);
		layout.setMajorSpacing(20); layout.setMinorSpacing(20);
		setLayoutManager(new FlowLayout(false));
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		Rectangle rect = getClientArea();
		g.setBackgroundColor(Colors.COLOR_WHITE);
		g.fillRectangle(rect.x, rect.y, rect.width, rect.height);
	}
	
	/**
	 * 
	 */
	public void addItem(ComboItem cItem) {
		SwtUtils.async(SwtUtils.getDisplay(), () -> {
			final Rectangle rect = getClientArea();
			final int h = 22, w = rect.width;
			Item item = new Item(cItem); item.setSize(w, h);
			item.addMouseMotionListener(new MouseMotionListener.Stub());
			item.addMouseListener(new MouseListener.Stub()); add(item); all.add(item);
		});
	}
	
	/**
	 * 
	 */
	protected class Item extends Figure {
		private String value;
		private boolean select;
		private boolean enter = false;
		private ComboItem cItem;
		
		/**
		 * 
		 */
		public Item(ComboItem item) {
			this.cItem = item; this.select = item.isSelect(); this.value = item.getValue();
		}
		
		@Override
		protected void paintFigure(Graphics g) {
			super.paintFigure(g);
			Font font = g.getFont(); String icon = FontAwesome.check;
			Rectangle rect = getBounds(); g.setFont(FontAwesome.getFont(12));
			final Dimension d = INSTANCE.getStringExtents(icon, g.getFont());
			if(enter) {
				g.setBackgroundColor(Colors.COLOR_WIDGET_MOTION_ENTER);
			} else {
				g.setBackgroundColor(Colors.COLOR_WHITE);
			}
			g.fillRectangle(rect.x, rect.y, rect.width, rect.height);
			//
			if(select) {
				g.drawString(icon, rect.x + 8, rect.y + (rect.height - d.height) / 2);
			}
			g.setFont(font); g.drawString(value, rect.x + 8 + d.width + 10, rect.y + (rect.height - d.height) / 2);
		}
		
		@Override
		public void handleMouseEntered(MouseEvent event) {
			super.handleMouseEntered(event); enter = true; repaint();
		}
		
		@Override
		public void handleMouseExited(MouseEvent event) {
			super.handleMouseExited(event); enter = false; repaint();
		}
		
		@Override
		public void handleMouseReleased(MouseEvent event) {
			super.handleMouseReleased(event);
			for (Item item : all) {
				if(item == this) {
					ComboModel model =popup.getCombo().getModel();
					ComboPanel panel = popup.getCombo().getPanel();
					model.setSelection(cItem); panel.setText(item.value); 
					select = true; repaint();
				} else {
					item.select = false; item.repaint();
				}
			}
		}
	}
}
