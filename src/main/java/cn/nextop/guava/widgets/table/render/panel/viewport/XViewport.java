package cn.nextop.guava.widgets.table.render.panel.viewport;

import static java.lang.Math.max;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Translatable;

import cn.nextop.guava.draw2d.scroll.bar.XRangeModel;
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
	public XViewport(String name, XRangeModel hRangeModel, XRangeModel vRangeModel) {
		super(name);
		this.vRangeModel = vRangeModel; this.hRangeModel = hRangeModel;
		if(this.vRangeModel != null) this.vRangeModel.addPropListener(this);
		if(this.hRangeModel != null) this.hRangeModel.addPropListener(this);
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
		return new Point(getHorzValue(), getVertValue());
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
		if(this.hRangeModel != null) 
			this.hRangeModel.setAll(0, extent1, max1);
		if(this.vRangeModel != null)
			this.vRangeModel.setAll(0, extent2, max2);
	}

	public void setContents(IFigure figure) {
		if (view == figure) return;
		if (view != null) remove(view);
		view = figure; if(view != null) add(figure);
	}
	
	public void setViewLocation(int x, int y) {
		if (this.hRangeModel != null
				&& this.hRangeModel.getValue() != x)
			this.hRangeModel.setValue(x);
		if (this.vRangeModel != null
				&& this.vRangeModel.getValue() != y)
			this.vRangeModel.setValue(y);
	}
	
	public void setHorizontalLocation(int value) {
		setViewLocation(value, getVertValue());
	}
	
	public void setVerticalLocation(int value) {
		setViewLocation(getHorzValue(), value);
	}
	
	protected int getHorzValue() {
		return hRangeModel == null ? 0 : hRangeModel.getValue();
	}
	
	protected int getVertValue() {
		return vRangeModel == null ? 0 : vRangeModel.getValue();
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
		t.performTranslate(getHorzValue(), getVertValue());
		super.translateFromParent(t);
	}
	
	@Override
	public void translateToParent(Translatable t) {
		t.performTranslate(-getHorzValue(), -getVertValue());
		super.translateToParent(t);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getSource() instanceof XRangeModel) {
			if (XRangeModel.PROPERTY_VALUE.equals(event.getPropertyName())) {
				localRevalidate(); repaint();
				fireFigureMoved(); fireCoordinateSystemChanged();
			}
		}
	}
}