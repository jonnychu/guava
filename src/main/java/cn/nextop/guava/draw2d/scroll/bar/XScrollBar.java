package cn.nextop.guava.draw2d.scroll.bar;

import static com.patrikdufresne.fontawesome.FontAwesome.caret_down;
import static com.patrikdufresne.fontawesome.FontAwesome.caret_left;
import static com.patrikdufresne.fontawesome.FontAwesome.caret_right;
import static com.patrikdufresne.fontawesome.FontAwesome.caret_up;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.AbstractPanel;

/**
 * @author jonny
 */
public class XScrollBar extends AbstractPanel implements PropertyChangeListener, ActionListener {
	
	protected Thumb thumb;
	protected boolean horz;
	protected XRangeModel model;
	protected StepButton btnUp, btnDown;
	protected int stepIncrement = 10;
	protected int pageIncrement = 50;
	protected PageButton pageUp, pageDown;
	
	/**
	 * 
	 */
	public XScrollBar(String name, boolean horz) {
		super(name); this.horz = horz;
		String s1 = horz ? caret_left : caret_up;
		String s2 = horz ? caret_right : caret_down;
		//
		this.model = new XRangeModel();
		this.model.addPropListener(this);
		DragListener listener = new DragListener();
		//
		add(this.thumb = new Thumb());
		add(this.btnUp = new StepButton(s1, "up"));
		add(this.btnDown = new StepButton(s2, "down"));
		add(this.pageUp = new PageButton("pageup"));
		add(this.pageDown = new PageButton("pagedown"));
		// Listener
		this.btnUp.addActionListener(this);
		this.pageUp.addActionListener(this);
		this.btnDown.addActionListener(this);
		this.pageDown.addActionListener(this);
		this.thumb.addMouseListener(listener);
		this.thumb.addMouseMotionListener(listener);
	}
	
	/**
	 * 
	 */
	public boolean isHorz() {
		return this.horz;
	}
	
	public Thumb getThumb() {
		return this.thumb;
	}
	
	public int getMinimum() {
		return this.model.getMin();
	}

	public int getMaximum() {
		return this.model.getMax();
	}
	
	public int getExtent() {
		return this.model.getExtent();
	}
	
	public StepButton getButtonUp() {
		return this.btnUp;
	}
	
	public StepButton getButtonDown() {
		return this.btnDown;
	}
	
	/**
	 * 
	 */
	public int getValueRange() {
		return getMaximum() - getExtent() - getMinimum();
	}
	
	/**
	 * 
	 */
	public void setValue(int v) { this.model.setValue(v); }
	
	public int getValue() { return this.model.getValue(); }
	
	/**
	 * 
	 */
	public XRangeModel getModel() { return model; }

	public void setModel(XRangeModel model) {
		if(this.model != null) model.removePropListener(this);
		this.model = model;	 this.model.addPropListener(this);
	}
	
	/**
	 * 
	 */
	public int getStepIncrement() { return this.stepIncrement; }

	public void setStepIncrement(int v) { this.stepIncrement = v; }
	
	/**
	 * 
	 */
	public int getPageIncrement() { return this.pageIncrement; }

	public void setPageIncrement(int v) { this.pageIncrement = v; }
	
	/**
	 * 
	 */
	protected void stepUp() { setValue(getValue() - getStepIncrement()); }
	
	protected void stepDown() { setValue(getValue() + getStepIncrement());}
	
	protected void pageUp() { setValue(getValue() - getPageIncrement()); }
	
	protected void pageDown() { setValue(getValue() + getPageIncrement()); }
	
	/**
	 * 
	 */
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		g.setBackgroundColor(Colors.COLOR_WIDGET_BACKGROUND);
		g.fillRectangle(getBounds());
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		XScrollBar parent = (XScrollBar) container;
		final Rectangle r = parent.getClientArea();
		
		final int w = r.width > r.height ? r.height : r.width;
		{
			final Dimension btnSize = new Dimension(w , w);
			Point p1 = r.getBottomRight().getTranslated(-w, -w);
			btnUp.setBounds(new Rectangle(r.getTopLeft(), btnSize));
			btnDown.setBounds(new Rectangle(p1, btnSize));
		}
		
		{
			final Insets inset = this.horz ? 
					new Insets(0, w, 0, w) : new Insets(w, 0, w, 0);
			final Rectangle r1 = r.getShrinked(inset);
			int max = parent.getMaximum(), min = parent.getMinimum();
			int extent = parent.getExtent(), value = parent.getValue();
			final int totalRange = max - min; if (totalRange == 0) return;
			
			final int val = totalRange - extent;
			if((val < 1) || (!isEnabled())) return;
			final int v1 = this.horz ? r1.x : r1.y;
			final int v2 = horz ? r1.width : r1.height;
			final int thumbWH = v2 * extent / totalRange;
			final int thumbXY = v1 + (v2 - thumbWH) * (value - min) / val;
			Rectangle thumbBounds = this.horz ? 
					new Rectangle(thumbXY, r1.y, thumbWH, r1.height) 
					: new Rectangle(r1.x, thumbXY, r1.width, thumbWH);
			thumb.setBounds(thumbBounds); thumb.setVisible(v2 > thumbWH);
			//
			Rectangle pageUpBounds = this.horz ? 
					new Rectangle(r1.x, r1.y, thumbXY - r1.x, r1.height) 
					: new Rectangle(r1.x, r1.y, r1.width, thumbXY - r1.y);
			//
			final int p = thumbXY + thumbWH;
			Rectangle pageDownBounds = this.horz ? 
					new Rectangle(p, r1.y, r1.right() - p, r1.height)
					: new Rectangle(r1.x, p, r1.width, r1.bottom() - p);
			pageUp.setBounds(pageUpBounds); pageDown.setBounds(pageDownBounds);
		}
	}
	
	@Override
	public void revalidate() {
		invalidate();
		getUpdateManager().addInvalidFigure(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String name = event.getActionName();
		switch (name) {
		case "up": stepUp(); break;
		case "down": stepDown(); break;
		case "pageup": pageUp(); break;
		case "pagedown": pageDown(); break;
		default : throw new RuntimeException("No name, " + name);
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		setEnabled(this.model.isEnabled()); revalidate();
	}
	
	@Override
	protected Dimension calPreferredSize(IFigure container, int wHint, int hHint) {
		return this.horz ? new Dimension(wHint, 16) : new Dimension(16, hHint);
	}
	
	/**
	 * 
	 */
	protected class Thumb extends Figure {
		//
		private boolean enter, drag;
		
		@Override
		protected void paintFigure(Graphics g) {
			super.paintFigure(g);
			if(!enter) {
				g.setBackgroundColor(Colors.COLOR_WIDGET_THUMB);
			} else {
				if(drag) {
					g.setBackgroundColor(Colors.COLOR_WIDGET_PRESS);
				} else {
					g.setBackgroundColor(Colors.COLOR_WIDGET_THUMB_FOCUS);
				}
			}
			g.fillRectangle(getClientArea());
		}
		
		@Override
		public void handleMouseDragged(MouseEvent event) {
			super.handleMouseDragged(event); drag = true; repaint();
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
			super.handleMouseReleased(event); drag = false; repaint(); 
		}
	}
	
	protected class DragListener extends MouseMotionListener.Stub implements MouseListener {
		//
		protected Point start;
		protected boolean armed;
		protected int dragRange, revertValue;
		
		@Override
		public void mousePressed(MouseEvent me) {
			Rectangle r = getClientArea();
			start = me.getLocation(); armed = true;
			
			Dimension s1 = getThumb().getSize();
			Dimension s2 = getButtonUp().getSize();
			Dimension s3 = getButtonDown().getSize();
			final int w = s1.width + s2.width + s3.width;
			final int h = s1.height + s2.height + s3.height;
			dragRange = isHorz() ? r.width - w : r.height - h;
			revertValue = getValue(); me.consume();
		}

		@Override
		public void mouseDragged(MouseEvent me) {
			if (!armed) return;
			final int vr = getValueRange();
			final Point p = me.getLocation();
			final Dimension diff = p.getDifference(start);
			final int change = isHorz() ? diff.width : diff.height;
			setValue(revertValue + vr * change / dragRange); me.consume();
		}
		
		@Override
		public void mouseDoubleClicked(MouseEvent me) {}
		
		@Override
		public void mouseReleased(MouseEvent me) {
			if (!armed) return; armed = false; me.consume();
		}
	}
}
