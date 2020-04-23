package cn.nextop.guava.widgets.table.render.panel;

import static cn.nextop.guava.support.Objects.cast;
import static java.lang.Math.max;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.support.draw2d.scroll.bar.XRangeModel;
import cn.nextop.guava.support.draw2d.scroll.bar.XScrollBar;
import cn.nextop.guava.widgets.table.builder.internal.XTableFactory;
import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.model.column.Column;
import cn.nextop.guava.widgets.table.model.config.XTableConfig;
import cn.nextop.guava.widgets.table.render.AbstractXTablePanel;
import cn.nextop.guava.widgets.table.render.viewport.XViewport;
import cn.nextop.guava.widgets.table.render.viewport.YViewport;

/**
 * @author jonny
 */
public class XTablePanel extends AbstractXTablePanel implements PropertyChangeListener {
	//
	protected XViewport head;
	protected YViewport data;
	protected XScrollBar hBar, vBar;
	protected XRangeModel hRangeModel, vRangeModel;
	
	/**
	 * 
	 */
	public XTablePanel(XTableFactory factory) {
		super("table.panel", factory);
		this.hBar = new XScrollBar("hz", true ); 
		this.vBar = new XScrollBar("vt", false);
		this.hRangeModel = this.hBar.getModel();
		this.vRangeModel = this.vBar.getModel();
		this.head = new XViewport("vh", hRangeModel, null);
		this.data = new YViewport("vd", hRangeModel, vRangeModel);
		
		XTableModel model = factory.getModel();
		XTableConfig config = model.getXTableConfig();
		this.hBar.setStepIncrement(config.getStepIncrement());
		this.hBar.setPageIncrement(config.getPageIncrement());
		this.vBar.setStepIncrement(config.getStepIncrement());
		this.vBar.setPageIncrement(config.getPageIncrement());
		
		add(this.head); add(this.data); add(this.hBar); add(this.vBar);
	}
	
	/**
	 * 
	 */
	public void setHeadContents(IFigure figure) {
		this.head.setContents(figure);
	}
	
	public void setDataContents(IFigure figure) {
		this.data.setContents(figure);
	}
	
	/**
	 * 
	 */
	public void pageUp() {
		if(!this.vBar.isVisible()) return;
		vBar.setValue(vBar.getValue() - vBar.getStepIncrement());
	}
	
	public void pageDown() {
		if(!this.vBar.isVisible()) return;
		vBar.setValue(vBar.getValue() + vBar.getStepIncrement());
	}
	
	private void localRevalidate() {
		invalidateTree();
		if (getLayoutManager() != null)
			getLayoutManager().invalidate();
		getUpdateManager().addInvalidFigure(this);
	}
	
	@Override
	public void validate() {
		super.validate(); this.hBar.validate(); this.vBar.validate();
	}
	
	/**
	 * 
	 */
	@Override
	protected void layoutManager(IFigure container) {
		XTablePanel parent = cast(container);
		XTableModel model = parent.getFactory().getModel();
		final XTableConfig config = model.getXTableConfig();
		
		//
		final Rectangle cArea = parent.getClientArea();
		final int right = vBar.getPreferredSize().width;
		final int bottom = hBar.getPreferredSize().height;
		final Insets i1 = new Insets(0, 0, bottom, right);
		
		Dimension d1 = cArea.getSize();
		Dimension d2 = d1.getShrinked(i1.right, i1.bottom);
		d2.width = max(d2.width, 0); d2.height = max(d2.height, 0);
		int wHint = d2.width, hHint = d2.height;
		
		Dimension dp = data.getContents().getPreferredSize(wHint, hHint).getCopy();
		Dimension hp = head.getContents().getPreferredSize(wHint, hHint).getCopy();
		boolean showV = hp.height + dp.height > d1.height, showH = hp.width > d1.width;
		vBar.setVisible(showV); hBar.setVisible(showH);
		if (!showV) i1.right = 0; if (!showH) i1.bottom = 0;
		final Rectangle r1 = cArea.getShrinked(i1);
		Rectangle r2 = new Rectangle(r1.x, r1.y, r1.width, config.getHeaderHeight()); head.setBounds(r2);
		Rectangle r3 = new Rectangle(r1.x, r2.bottom(), r1.width, r1.height - r2.height); data.setBounds(r3);
		
		// bar
		if (showV) vBar.setBounds(new Rectangle(r1.right(), r1.y, i1.right, r1.height));
		if (showH) hBar.setBounds(new Rectangle(r1.x, r1.bottom(), r1.width, i1.bottom));
		
		final int vStepInc = vBar.getStepIncrement();
		int vPageInc = vBar.getModel().getExtent() - vStepInc;
		if (vPageInc < vStepInc) vPageInc = vStepInc; vBar.setPageIncrement(vPageInc);

		final int hStepInc = hBar.getStepIncrement();
		int hPageInc = hBar.getModel().getExtent() - hStepInc;
		if (hPageInc < hStepInc) hPageInc = hStepInc; hBar.setPageIncrement(hPageInc);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getSource() instanceof Column) {
			if (Column.PROPERTY_RESIZE.equals(event.getPropertyName())) {
				localRevalidate(); repaint(); fireFigureMoved(); fireCoordinateSystemChanged();
			}
		}
	}
}
