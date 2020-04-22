package cn.nextop.guava.widgets.table.render.panel.content;

import static cn.nextop.guava.support.Objects.cast;
import static cn.nextop.guava.support.swt.CGUtils.drawLine;

import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.support.swt.CGUtils;
import cn.nextop.guava.widgets.table.builder.internal.XTableFactory;
import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.model.column.Column;
import cn.nextop.guava.widgets.table.model.config.XTableConfig;
import cn.nextop.guava.widgets.table.render.AbstractXTableColumnWidget;
import cn.nextop.guava.widgets.table.render.AbstractXTablePanel;

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
		//
		final Rectangle r = getBounds(); g.pushState();
		final int x = r.x, y = r.y, w = r.width, h = r.height;
		CGUtils.fillRect(g, getBounds(), cfg.getBG_WHITE());
		int cx = 0; for (int i = 0; i < cols.size(); i++) {
			Column<?> column = cols.get(i);
			drawLine(g, cx, y, cx, y + h, cfg.getFG_GRAY());
			cx = cx + x + column.getPixel();
			if(i == cols.size() - 1)
				drawLine(g, cx, y, cx, y + h, cfg.getFG_GRAY());
		}
		drawLine(g, x, y, x + w, y, cfg.getFG_GRAY());
		drawLine(g, x, y + h - 1, x + w, y + h - 1, cfg.getFG_GRAY());
		g.popState(); super.paintClientArea(g);
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
		List<AbstractXTableColumnWidget> widgets = cast(getChildren());
		int cx = 0; for (AbstractXTableColumnWidget cw : widgets) {
			final int px = cw.getColumn().getPixel();
			cw.setBounds(new Rectangle(x + cx, y, px, h)); cx = cx + px;
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
