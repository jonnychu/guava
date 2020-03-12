package cn.nextop.guava.widgets.table.render.panel;

import org.eclipse.draw2d.DefaultRangeModel;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RangeModel;
import org.eclipse.draw2d.ScrollBar;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.draw2d.scroll.support.glossary.Type;
import cn.nextop.guava.utils.Debug;
import cn.nextop.guava.widgets.table.XTable;
import cn.nextop.guava.widgets.table.render.AbstractTablePanel;
import cn.nextop.guava.widgets.table.render.panel.common.Resolver;
import cn.nextop.guava.widgets.table.render.panel.common.Resolver.Result;
import cn.nextop.guava.widgets.table.render.panel.common.Viewport;
import cn.nextop.guava.widgets.table.render.panel.content.ContentViewport;
import cn.nextop.guava.widgets.table.render.panel.header.HeaderViewport;

/**
 * @author jonny
 */
public class XTablePanel extends AbstractTablePanel {
	//
	protected XTable table;
	protected ScrollBar hBar, vBar;
	protected RangeModel horzRangeModel;
	protected RangeModel vertRangeModel;
	protected HeaderViewport header;
	protected ContentViewport content;
	
	/**
	 * 
	 */
	public XTable getTable() { return table; }
	public ScrollBar getHorzBar() { return hBar; }
	public ScrollBar getVertBar() { return vBar; }
	public HeaderViewport getHeader() { return header; }
	public ContentViewport getContent() { return content; }
	/**
	 * 
	 */
	public XTablePanel(XTable table) {
		super("table.panel"); this.table = table;
		this.horzRangeModel = new DefaultRangeModel();
		this.vertRangeModel = new DefaultRangeModel();
		this.hBar = new ScrollBar(); this.vBar = new ScrollBar();
		this.hBar.setHorizontal(true); this.vBar.setHorizontal(false);
		hBar.setRangeModel(horzRangeModel); vBar.setRangeModel(vertRangeModel);
		header = new HeaderViewport(this, horzRangeModel, vertRangeModel);
		content = new ContentViewport(this, horzRangeModel, vertRangeModel);
		add(content);
		add(header);
		add(hBar);
		add(vBar);
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		XTablePanel parent = (XTablePanel) container;
		{
			final Rectangle r = parent.getBounds();
			final int x = r.x, y = r.y, w = r.width, h = r.height;
			Rectangle r1 = new Rectangle(x, y, w, 22); header.setBounds(r1);
			Rectangle r2 = new Rectangle(x, y + r1.height, w, h - r1.height); content.setBounds(r2);
//			Debug.println("XTablePanel : "+r);
//			Debug.println("HeaderScrollPanel : "+r1);
//			Debug.println("ContentScrollPanel : "+r2);
		}
		
		

		Viewport header = parent.getHeader();
		Viewport content = parent.getContent();
		final ScrollBar hBar = parent.getHorzBar();
		final ScrollBar vBar = parent.getVertBar();
		
		{
			final Rectangle r = content.getClientArea();
			final int vBarWidth = vBar.getPreferredSize().width;
			final int hBarHeight = hBar.getPreferredSize().height;
			Result result = Resolver.solve(r, content, Type.AUTO, Type.AUTO, vBarWidth, hBarHeight);
			Insets i1 = result.insets;
			boolean showV = result.showV, showH = result.showH;
			vBar.setVisible(showV); hBar.setVisible(showH);
			if (!showV) i1.right = 0; if (!showH) i1.bottom = 0;
			Rectangle r1 = r.getShrinked(i1); content.setBounds(r1);
			if (showV) vBar.setBounds(new Rectangle(r1.right(), r1.y, i1.right, r1.height));
			if (showH) hBar.setBounds(new Rectangle(r1.x, r1.bottom(), r1.width, i1.bottom));
		}
		
		

		final int vStepInc = vBar.getStepIncrement();
		int vPageInc = vBar.getRangeModel().getExtent() - vStepInc;
		if (vPageInc < vStepInc) vPageInc = vStepInc; vBar.setPageIncrement(vPageInc);

		final int hStepInc = hBar.getStepIncrement();
		int hPageInc = hBar.getRangeModel().getExtent() - hStepInc;
		if (hPageInc < hStepInc) hPageInc = hStepInc; hBar.setPageIncrement(hPageInc);
	}
}
