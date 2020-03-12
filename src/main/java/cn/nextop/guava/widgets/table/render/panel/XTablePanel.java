package cn.nextop.guava.widgets.table.render.panel;

import static cn.nextop.guava.draw2d.scroll.support.glossary.Type.AUTO;
import static cn.nextop.guava.widgets.table.render.panel.common.Resolver.solve;

import org.eclipse.draw2d.DefaultRangeModel;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RangeModel;
import org.eclipse.draw2d.ScrollBar;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.CGUtils;
import cn.nextop.guava.widgets.table.XTable;
import cn.nextop.guava.widgets.table.render.AbstractTablePanel;
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
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
		g.drawRectangle(CGUtils.getBorderRect(getClientArea()));
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		XTablePanel parent = (XTablePanel) container;
		//
		final Rectangle r = parent.getBounds();
		final ScrollBar hBar = parent.getHorzBar();
		final ScrollBar vBar = parent.getVertBar();
		final Viewport header = parent.getHeader();
		final Viewport content = parent.getContent();
		{
			final int x = r.x, y = r.y, w = r.width, h = r.height;
			Rectangle r1 = new Rectangle(x, y, w, 22); header.setBounds(r1);
			Rectangle r2 = new Rectangle(x, y + r1.height, w, h - r1.height); content.setBounds(r2);
		}
		{
			final Rectangle hr = header.getClientArea();
			final Rectangle cr = content.getClientArea();
			final int vbw = vBar.getPreferredSize().width;
			final int hbh = hBar.getPreferredSize().height;
			Result rst = solve(cr, content, AUTO, AUTO, vbw, hbh);
			//
			boolean showV = rst.showV, showH = rst.showH;
			vBar.setVisible(showV); hBar.setVisible(showH);
			//
			final Insets i2 = rst.insets;
			final Insets i1 = new Insets(0, 0, 0, i2.right);
			final Insets i3 = new Insets(0, 0, i2.bottom, 0);
			Rectangle r1 = hr.getShrinked(i1); header.setBounds(r1);
			Rectangle r2 = cr.getShrinked(i2); content.setBounds(r2);
			if (showV) vBar.setBounds(new Rectangle(r2.right(), r1.y, i2.right, r1.height));
			if (showH) hBar.setBounds(new Rectangle(r1.x, r2.bottom(), r2.width, i2.bottom));
		}

		final int vStepInc = vBar.getStepIncrement();
		int vPageInc = vBar.getRangeModel().getExtent() - vStepInc;
		if (vPageInc < vStepInc) vPageInc = vStepInc; vBar.setPageIncrement(vPageInc);

		final int hStepInc = hBar.getStepIncrement();
		int hPageInc = hBar.getRangeModel().getExtent() - hStepInc;
		if (hPageInc < hStepInc) hPageInc = hStepInc; hBar.setPageIncrement(hPageInc);
	}
}
