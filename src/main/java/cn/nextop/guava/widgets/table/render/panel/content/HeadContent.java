package cn.nextop.guava.widgets.table.render.panel.content;

import static cn.nextop.guava.support.Objects.cast;
import static cn.nextop.guava.support.swt.CGUtils.drawLine;

import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

import cn.nextop.guava.support.swt.CGUtils;
import cn.nextop.guava.widgets.table.builder.internal.XTableFactory;
import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.model.column.Column;
import cn.nextop.guava.widgets.table.model.config.XTableConfig;
import cn.nextop.guava.widgets.table.render.AbstractXTableColumnWidget;
import cn.nextop.guava.widgets.table.render.AbstractXTablePanel;
import cn.nextop.guava.widgets.table.render.widget.DefaultColumnResizeWidget;
import cn.nextop.guava.widgets.table.render.widget.DefaultColumnWidget;

/**
 * @author jonny
 */
public class HeadContent extends AbstractXTablePanel {
	
	/**
	 * 
	 */
	public HeadContent(XTableFactory factory) {
		super("content.header", factory);
	}
	
	@Override
	protected void paintClientArea(Graphics g) {
		//
		final XTableModel model = factory.getModel();
		final XTableConfig cfg = model.getXTableConfig();
		List<Column<?>> cols = model.getColumns().getColumns();
		final Color c1 = cfg.getBG_WHITE(), c2 = cfg.getFG_GRAY();
		//
		final Rectangle r = getBounds(); g.pushState();
		final int x = r.x, y = r.y, w = r.width, h = r.height;
		//
		CGUtils.fillRect(g, r, c1);	int cx = 0; 
		for (int i = 0; i < cols.size(); i++) {
			Column<?> column = cols.get(i);
			drawLine(g, cx, y, cx, y + h, c2);
			cx = cx + x + column.getPixel(); // next x
			if(i == cols.size() - 1) drawLine(g, cx, y, cx, y + h, c2);
		}
		drawLine(g, x, y + h - 1, x + w, y + h - 1, c2);
		drawLine(g, x, y, x + w, y, c2); g.popState(); 
		super.paintClientArea(g);
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		HeadContent hc = cast(container);
		final XTableModel model = factory.getModel();
		List<Column<?>> cols = model.getColumns().getColumns();
		if(cols == null || cols.size() == 0) return;
		
		//
		final Rectangle r = hc.getClientArea();
		final int x = r.x, y = r.y, h = r.height;
		List<AbstractXTableColumnWidget> cws = cast(getChildren());
		int cx = 0, idx = 0; for (Column<?> column : cols) {
			final int px = column.getPixel();
			DefaultColumnWidget dcw = cast(cws.get(idx ++));
			DefaultColumnResizeWidget dcrw = cast(cws.get(idx ++));
			Rectangle r1 = new Rectangle(x + cx + margin, y, px - margin * 2, h);
			Rectangle r2 = new Rectangle(r1.x + r1.width, y, margin * 2, h);
			dcw.setBounds(r1); dcrw.setBounds(r2); cx = cx + px; // cw & resize
		}
	}
	
	@Override
	public Dimension calPreferredSize(IFigure container, int wHint, int hHint) {
		final XTableModel model = factory.getModel();
		final XTableConfig config = model.getXTableConfig();
		final List<Column<?>> cols = model.getColumns().toPixel(wHint);
		final int w1 = cols.stream().mapToInt(Column::getPixel).sum();
		return new Dimension(w1, config.getItemHeight());
	}
}
