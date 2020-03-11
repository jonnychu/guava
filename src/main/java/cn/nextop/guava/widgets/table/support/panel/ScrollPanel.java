package cn.nextop.guava.widgets.table.support.panel;

import static cn.nextop.guava.draw2d.scroll.support.glossary.Type.NEVER;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScrollBar;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.draw2d.scroll.support.glossary.Type;
import cn.nextop.guava.widgets.AbstractPanel;

/**
 * @author jonny
 */
public class ScrollPanel extends AbstractPanel {
	//
	protected Viewport viewport;
	protected ScrollBar hBar, vBar;
	protected Type horzType, vertType;
	
	/**
	 * 
	 */
	public ScrollPanel() {
		this(Type.AUTO, Type.AUTO);
	}
	
	public ScrollPanel(Type horzType, Type vertType) {
		super("scroll.panel");
		this.horzType = horzType;
		this.vertType = vertType;
		this.hBar = new ScrollBar(); 
		this.vBar = new ScrollBar();
		this.viewport = new Viewport();
		this.hBar.setHorizontal(true );
		this.vBar.setHorizontal(false);
		hBar.setRangeModel(viewport.getHorzRangeModel());
		vBar.setRangeModel(viewport.getVertRangeModel());
		add(this.viewport); add(this.hBar); add(this.vBar);
	}
	
	/**
	 * 
	 */
	public Type getHorzType() { return this.horzType; }
	public Type getVertType() { return this.vertType; }
	public ScrollBar getHorzBar() { return this.hBar; }
	public ScrollBar getVertBar() { return this.vBar; }
	public Viewport getViewport() { return this.viewport; }
	
	/**
	 * 
	 */
	public void scrollTo(Point location) {
		scrollHorizontalTo(location.x);
		scrollVerticalTo(location.y);
	}
	
	public void scrollVerticalTo(int y) {
		this.viewport.setVerticalLocation(y);
	}
	
	public void scrollHorizontalTo(int x) {
		this.viewport.setHorizontalLocation(x);
	}
	
	public void setContents(IFigure figure) {
		this.viewport.setContents(figure);
	}
	
	/**
	 * 
	 */
	@Override
	public boolean isOpaque() { return true; }
	
	@Override
	public void validate() {
		super.validate(); this.hBar.validate(); this.vBar.validate();
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		ScrollPanel scrollpane = (ScrollPanel) container;
		final Type hVis = scrollpane.getHorzType();
		final Type vVis = scrollpane.getVertType();
		final ScrollBar hBar = scrollpane.getHorzBar();
		final ScrollBar vBar = scrollpane.getVertBar();
		final Viewport viewport = scrollpane.getViewport();

		
		//
		final Rectangle r = scrollpane.getClientArea();
		final int right = vBar.getPreferredSize().width;
		final int bottom = hBar.getPreferredSize().height;
		final Insets i1 = new Insets(0, 0, bottom, right);

		Dimension d1 = r.getSize();
		int s1 = (vVis == NEVER) ? 0 : i1.right;
		int s2 = (hVis == NEVER) ? 0 : i1.bottom;
		Dimension d2 = d1.getCopy().shrink(s1, s2);
		d2.width = Math.max(d2.width, 0);
		d2.height = Math.max(d2.height, 0);
		int wHint = d2.width, hHint = d2.height;

		final Insets i2 = viewport.getInsets();
		Dimension minSize = new Dimension(i2.getWidth(), i2.getHeight());
		if (viewport.getContents() != null) {
			if (wHint > -1) wHint = Math.max(0, wHint - i2.getWidth());
			if (hHint > -1) hHint = Math.max(0, hHint - i2.getHeight());
			minSize.expand(viewport.getContents().getMinimumSize(wHint, hHint));
		}
		Dimension preferred = viewport.getPreferredSize(wHint, hHint).getCopy();
		preferred.height = minSize.height;
		preferred.width = minSize.width;

		boolean none = d1.contains(preferred);
		boolean both = !none && preferred.containsProper(d2);
		boolean sv = both || preferred.height > d1.height;
		boolean sh = both || preferred.width > d1.width;

		// Adjust for visibility override flags
		boolean showV = vVis != Type.NEVER && (sv || vVis == Type.ALWAYS);
		boolean showH = hVis != Type.NEVER && (sh || hVis == Type.ALWAYS);
		
		vBar.setVisible(showV); hBar.setVisible(showH);
		if (!showV) i1.right = 0; if (!showH) i1.bottom = 0;
		Rectangle r1 = r.getShrinked(i1); viewport.setBounds(r1);
		if (showV) vBar.setBounds(new Rectangle(r1.right(), r1.y, i1.right, r1.height));
		if (showH) hBar.setBounds(new Rectangle(r1.x, r1.bottom(), r1.width, i1.bottom));
		
		

		final int vStepInc = vBar.getStepIncrement();
		int vPageInc = vBar.getRangeModel().getExtent() - vStepInc;
		if (vPageInc < vStepInc) vPageInc = vStepInc; vBar.setPageIncrement(vPageInc);

		final int hStepInc = hBar.getStepIncrement();
		int hPageInc = hBar.getRangeModel().getExtent() - hStepInc;
		if (hPageInc < hStepInc) hPageInc = hStepInc; hBar.setPageIncrement(hPageInc);
	}
}
