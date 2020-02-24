package cn.nextop.guava.widgets.list;

import static cn.nextop.guava.utils.SwtUtils.async;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.Panel;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.patrikdufresne.fontawesome.FontAwesome;

import cn.nextop.guava.utils.CGUtils;
import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.list.model.XListItem;
import cn.nextop.guava.widgets.list.model.XListModel;

/**
 * @author jonny
 */
public class XList extends Canvas {
	//
	private XPanel panel;
	private XContent content;
	private XListModel model;
	private LightweightSystem lws;
	
	public XList(Composite parent, int style) {
		super(parent, style);
		this.model = new XListModel();
		this.lws = new LightweightSystem(this);
		this.lws.setContents(panel = new XPanel());
		this.panel.setContents(content = new XContent());
	}
	
	public void setInput(List<XListItem> inputs) {
		//
		this.model.setInput(inputs);
		//
		async(getDisplay(), () -> {
			content.removeAll();
			Rectangle r1 = content.getClientArea();
			for (XListItem item : inputs) {
				Item i = new Item(item); content.add(i);
				i.setSize(r1.width, 23); i.repaint();
				i.addMouseListener(new MouseListener.Stub());
				i.addMouseMotionListener(new MouseMotionListener.Stub());
			}
			content.repaint();
		});
	}
	
	/**
	 * 
	 */
	protected class XPanel extends ScrollPane {
		
		public XPanel() {
			setHorizontalScrollBarVisibility(NEVER);
			setVerticalScrollBarVisibility(AUTOMATIC);
		}
		
		@Override
		protected void paintBorder(Graphics g) {
			super.paintBorder(g);
			g.setForegroundColor(Colors.COLOR_DARK_GRAY);
			g.drawRectangle(CGUtils.getBorderRect(getBounds()));
		}
	}
	
	/**
	 * 
	 */
	protected class XContent extends Panel {
		//
		@Override protected boolean useLocalCoordinates() { return true; }
		
		/**
		 * 
		 */
		public XContent() {
			FlowLayout layout = new FlowLayout(false);
			layout.setMajorSpacing(0); layout.setMinorSpacing(0); setLayoutManager(layout);
		}
	}
	
	/**
	 * 
	 */
	protected class Item extends Figure {
		//
		private String value;
		private boolean select;
		private boolean enter;
		
		/**
		 * 
		 */
		public Item(XListItem item) {
			this.value = item.getValue(); 
			this.select = item.isSelect();
		}
		
		@Override
		protected void paintFigure(Graphics g) {
			super.paintFigure(g);
			Font font = g.getFont(); String icon = FontAwesome.check;
			Rectangle rect = getBounds(); g.setFont(FontAwesome.getFont(12));
			final Dimension d = INSTANCE.getStringExtents(icon, g.getFont());
			if(enter) {
				g.setBackgroundColor(Colors.COLOR_CYAN);
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
			@SuppressWarnings("unchecked")
			List<Item> allItems = content.getChildren();
			for (Item i : allItems) {
				if(i.value.equals(value)) {
					select = true; repaint();
				} else {
					i.select = false; i.repaint();
				}
			}
		}
	}
}
