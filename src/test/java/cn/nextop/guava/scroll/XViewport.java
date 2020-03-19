package cn.nextop.guava.scroll;

import static java.lang.Math.max;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RangeModel;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Translatable;

import cn.nextop.guava.scroll.bar.XRangeModel;
import cn.nextop.guava.widgets.AbstractPanel;

/**
 * @author jonny
 */
public class XViewport extends AbstractPanel implements PropertyChangeListener {

	private IFigure view;
	private XRangeModel hRangeModel, vRangeModel;
	
	/**
	 * 
	 */
	public XViewport() {
		super("viewport");
		vRangeModel = new XRangeModel();
		hRangeModel = new XRangeModel();
		vRangeModel.addPropListener(this);
		hRangeModel.addPropListener(this);
	}
	
	/**
	 * 
	 */
	public IFigure getContents() { return view; }
	public XRangeModel getHorzRangeModel() { return hRangeModel; }
	public XRangeModel getVertRangeModel() { return vRangeModel; }
	
	/**
	 * 
	 */
	public Point getViewLocation() {
		int w = this.hRangeModel.getValue();
		int h = this.vRangeModel.getValue();
		return new Point(w, h);
	}

	private void localRevalidate() {
		invalidate();
		if (getLayoutManager() != null)
			getLayoutManager().invalidate();
		getUpdateManager().addInvalidFigure(this);
	}
	
	protected void readjustScrollBars() {
		if(this.view == null) return;
		int extent1 = getClientArea().width;
		int extent2 = getClientArea().height;
		int max1 = this.view.getBounds().width;
		int max2 = this.view.getBounds().height;
		this.hRangeModel.setAll(0, extent1, max1);
		this.vRangeModel.setAll(0, extent2, max2);
	}

	public void setContents(IFigure figure) {
		if (view == figure) return;
		if (view != null) remove(view);
		view = figure; if(view != null) add(figure);
	}
	
	public void setViewLocation(int x, int y) {
		if (this.hRangeModel.getValue() != x)
			this.hRangeModel.setValue(x);
		if (this.vRangeModel.getValue() != y)
			this.vRangeModel.setValue(y);
	}
	
	public void setHorizontalLocation(int value) {
		setViewLocation(value, vRangeModel.getValue());
	}
	
	public void setVerticalLocation(int value) {
		setViewLocation(hRangeModel.getValue(), value);
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
	
	@Override
	protected void layoutManager(IFigure container) {
		final XViewport viewport = (XViewport) container;
		final IFigure contents = viewport.getContents(); 
		if(contents == null) return; // no contents add
		
		//
		Point p = viewport.getClientArea().getLocation();
		p.translate(viewport.getViewLocation().getNegated());
		//
		Rectangle r1 = viewport.getClientArea();
		final int w = r1.width, h = r1.height;
		Dimension min = contents.getMinimumSize(w, h);
		//
		Dimension r2 = r1.getSize();
		r2.height = max(r2.height, min.height);
		r2.width = Math.max(r2.width, min.width);
		contents.setBounds(new Rectangle(p, r2));
	}
	
	@Override
	public void validate() { super.validate(); readjustScrollBars(); }
	
	@Override
	public void translateFromParent(Translatable t) {
		t.performTranslate(this.hRangeModel.getValue(), this.vRangeModel.getValue());
		super.translateFromParent(t);
	}
	
	@Override
	public void translateToParent(Translatable t) {
		t.performTranslate(-this.vRangeModel.getValue(), -this.vRangeModel.getValue());
		super.translateToParent(t);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getSource() instanceof RangeModel) {
			if (XRangeModel.PROPERTY_VALUE.equals(event.getPropertyName())) {
				localRevalidate(); repaint();
				fireFigureMoved(); fireCoordinateSystemChanged();
			}
		}
	}
}