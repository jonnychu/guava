package cn.nextop.guava.draw2d.scroll;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.DefaultRangeModel;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RangeModel;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Translatable;

import cn.nextop.guava.widgets.datetime.render.AbstractPanel;

/**
 * @author jonny
 */
public class Viewport extends AbstractPanel implements PropertyChangeListener {

	private IFigure view;
	private RangeModel hRangeModel, vRangeModel;
	//
	public static final String PROPERTY_VIEW_LOCATION = "viewLocation";
	
	/**
	 * 
	 */
	public Viewport() {
		super("viewport");
		vRangeModel = new DefaultRangeModel();
		hRangeModel = new DefaultRangeModel();
		vRangeModel.addPropertyChangeListener(this);
		hRangeModel.addPropertyChangeListener(this);
	}
	
	
	public IFigure getContents() { return view; }
	public RangeModel getHorzRangeModel() { return hRangeModel; }
	public RangeModel getVertRangeModel() { return vRangeModel; }
	
	

	public Point getViewLocation() {
		return new Point(this.hRangeModel.getValue(), this.vRangeModel.getValue());
	}

	private void localRevalidate() {
		invalidate();
		if (getLayoutManager() != null)
			getLayoutManager().invalidate();
		getUpdateManager().addInvalidFigure(this);
	}
	
	protected void readjustScrollBars() {
		if(getContents() == null) return;
		this.hRangeModel.setAll(0, getClientArea().width, getContents().getBounds().width);
		this.vRangeModel.setAll(0, getClientArea().height,getContents().getBounds().height);
	}

	public void setContents(IFigure figure) {
		if (view == figure) return;
		if (view != null) remove(view);
		view = figure; if (view != null) add(figure);
	}

	public void setHorizontalLocation(int value) {
		setViewLocation(value, this.vRangeModel.getValue());
	}
	
	public void setVerticalLocation(int value) {
		setViewLocation(this.hRangeModel.getValue(), value);
	}

	public void setViewLocation(int x, int y) {
		if (this.hRangeModel.getValue() != x)
			this.hRangeModel.setValue(x);
		if (this.vRangeModel.getValue() != y)
			this.vRangeModel.setValue(y);
	}

	@Override
	public Rectangle getClientArea(Rectangle rect) {
		super.getClientArea(rect);
		rect.translate(getViewLocation()); return rect;
	}
	
	@Override
	protected void paintClientArea(Graphics g) {
		final Point p = getViewLocation();
		try {
			g.translate(-p.x, -p.y); g.pushState();
			super.paintClientArea(g); g.popState();
		} finally {
			g.translate(p.x, p.y);
		}
	}
	
	public void translateFromParent(Translatable t) {
		t.performTranslate(this.hRangeModel.getValue(), this.vRangeModel.getValue());
		super.translateFromParent(t);
	}

	public void translateToParent(Translatable t) {
		t.performTranslate(-this.vRangeModel.getValue(), -this.vRangeModel.getValue());
		super.translateToParent(t);
	}

	public void validate() { super.validate(); readjustScrollBars(); }

	@Override
	protected void layoutManager(IFigure container) {
		final Viewport viewport = (Viewport) container;
		final IFigure contents = viewport.getContents(); if(contents == null) return;
		
		//
		Point p = viewport.getClientArea().getLocation();
		p.translate(viewport.getViewLocation().getNegated());

		Rectangle r1 = viewport.getClientArea();
		Dimension min = contents.getMinimumSize(r1.width, r1.height);
		Dimension r2 = r1.getSize();
		r2.height = Math.max(r2.height, min.height);
		r2.width = Math.max(r2.width, min.width);

		contents.setBounds(new Rectangle(p, r2)); System.out.println("viewport layout " + contents.getBounds());
	}
	
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getSource() instanceof RangeModel) {
			if (RangeModel.PROPERTY_VALUE.equals(event.getPropertyName())) {
				localRevalidate(); repaint();
				fireFigureMoved(); fireCoordinateSystemChanged();
				firePropertyChange(PROPERTY_VIEW_LOCATION, event.getOldValue(), event.getNewValue());
			}
		}
	}
}
