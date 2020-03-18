package cn.nextop.guava.scroll;

import static com.patrikdufresne.fontawesome.FontAwesome.caret_down;
import static com.patrikdufresne.fontawesome.FontAwesome.caret_left;
import static com.patrikdufresne.fontawesome.FontAwesome.caret_right;
import static com.patrikdufresne.fontawesome.FontAwesome.caret_up;

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
public class XScrollBar extends AbstractPanel {
	
	protected Thumb thumb;
	protected boolean horz;
	protected XRangeModel model;
	protected XButton btnUp, btnDown;
	
	/**
	 * 
	 */
	public XScrollBar(String name, boolean horz) {
		super(name); this.horz = horz;
		this.model = new XRangeModel();
		String s1 = horz ? caret_left : caret_up;
		String s2 = horz ? caret_right : caret_down;
		//
		add(this.thumb = new Thumb());
		add(this.btnUp = new XButton(s1));
		add(this.btnDown = new XButton(s2));
		//
		Listener listener = new Listener();
		this.thumb.addMouseListener(listener);
		this.thumb.addMouseMotionListener(listener);
	}
	
	/**
	 * setter
	 */
	public void setValue(int v) {
		this.model.setValue(v); revalidate();
	}
	
	/**
	 * getter
	 */
	private boolean isHorz() {
		return this.horz;
	}
	
	private Thumb getThumb() {
		return this.thumb;
	}
	
	private int getMinimum() {
		return this.model.getMin();
	}

	private int getMaximum() {
		return this.model.getMax();
	}
	
	private int getValue() {
		return this.model.getValue();
	}
	
	private int getExtent() {
		return this.model.getExtent();
	}
	
	private XButton getButtonUp() {
		return this.btnUp;
	}
	
	private XButton getButtonDown() {
		return this.btnDown;
	}
	
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
			final int v1 = this.horz ? r1.x : r1.y;
			final int v2 = horz ? r1.width : r1.height;
			final int thumbWH = v2 * extent / totalRange;
			final int thumbXY = v1 + (v2 - thumbWH) * (value - min) / val;
			Rectangle thumbBounds = this.horz ? 
					new Rectangle(thumbXY, r1.y, thumbWH, r1.height) 
					: new Rectangle(r1.x, thumbXY, r1.width, thumbWH);
			thumb.setBounds(thumbBounds); thumb.setVisible(v2 > thumbWH);
		}
	}
	
	@Override
	protected Dimension calPreferredSize(IFigure container, int wHint, int hHint) {
		return this.horz ? new Dimension(wHint, 16) : new Dimension(16, hHint);
	}
	
	@Override
	public void revalidate() {
		invalidate();
		getUpdateManager().addInvalidFigure(this);
	}
	/**
	 * 
	 */
	protected class Thumb extends Figure {
		@Override
		protected void paintFigure(Graphics g) {
			super.paintFigure(g); Rectangle r = getClientArea();
			g.setBackgroundColor(Colors.COLOR_CYAN);
			g.fillRectangle(r);
		}
	}
	
	protected class Listener extends MouseMotionListener.Stub implements MouseListener {
		protected Point start;
		protected boolean armed;
		protected int dragRange;
		protected int revertValue;
		
		@Override
		public void mousePressed(MouseEvent me) {
			Rectangle r = getClientArea();
			start = me.getLocation(); armed = true;
			
			Dimension thumbSize = getThumb().getSize();
			if(isHorz()) {
				r.width -= getButtonUp().getSize().width;
				r.width -= getButtonDown().getSize().width;
				dragRange = r.width - thumbSize.width;
			} else {
				r.height -= getButtonUp().getSize().height;
				r.height -= getButtonDown().getSize().height;
				dragRange = r.height - thumbSize.height;
			}
			revertValue = getValue(); me.consume();
		}

		@Override
		public void mouseReleased(MouseEvent me) {
			if (!armed) return; armed = false; me.consume();
		}

		@Override
		public void mouseDragged(MouseEvent me) {
			if (!armed) return;
			Point p1 = me.getLocation(); me.consume();
			Dimension diff = p1.getDifference(start);
			if(isHorz()) {
				setValue(revertValue + getValue() * diff.width / dragRange);
			} else {
				setValue(revertValue + getValue() * diff.height / dragRange);
			}
		}

		@Override
		public void mouseDoubleClicked(MouseEvent me) {}
	}
}
