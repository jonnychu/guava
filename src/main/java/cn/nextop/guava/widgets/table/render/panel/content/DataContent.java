package cn.nextop.guava.widgets.table.render.panel.content;

import static cn.nextop.guava.support.Objects.cast;
import static cn.nextop.guava.support.swt.CGUtils.drawLine;

import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.table.builder.internal.XTableFactory;
import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.model.column.Column;
import cn.nextop.guava.widgets.table.model.config.XTableConfig;
import cn.nextop.guava.widgets.table.model.row.IRow;
import cn.nextop.guava.widgets.table.render.AbstractXTablePanel;
import cn.nextop.guava.widgets.table.render.panel.RowPanel;

/**
 * @author jonny
 */
public class DataContent extends AbstractXTablePanel {
	
	/**
	 * 
	 */
	public DataContent(XTableFactory factory) {
		super("content.data", factory);
	}
	
	@Override
	protected void paintClientArea(Graphics g) {
		final XTableModel model = factory.getModel();
		final XTableConfig cfg = model.getXTableConfig();
		List<Column<?>> cols = model.getColumns().getColumns();
		//
		Rectangle r = getBounds(); g.pushState();
		final int x = r.x, y = r.y, w = r.width, h = r.height;
		int cy = 0, idx = 0; while(cy < y + h) {
			if(idx++ % 2 == 0) {
				g.setBackgroundColor(cfg.getBG_WHITE());
			} else {
				g.setBackgroundColor(cfg.getBG_GRAY_L());
			}
			g.fillRectangle(x, cy, w, cfg.getItemHeight());
			cy = cy + cfg.getItemHeight();
		}
		int cx = 0, s1 = cols.size(); for (int i = 0; i < s1; i++) {
			drawLine(g, cx, y, cx, y + h, cfg.getFG_GRAY());
			Column<?> column = cols.get(i); cx = cx + x + column.getPixel();
			if(i == s1 - 1)	drawLine(g, cx, y, cx, y + h, cfg.getFG_GRAY());
				
		}
		g.popState(); super.paintClientArea(g);
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		DataContent dc = cast(container);
		final XTableModel model = factory.getModel();
		XTableConfig config = model.getXTableConfig();
		final List<IRow> rows = model.getRows().getRows();
		if(rows == null || rows.size() == 0) return;
		
		final Rectangle r = dc.getClientArea();
		final int x = r.x, y = r.y, w = r.width, h = r.height;
		List<RowPanel> widgets = cast(getChildren());
		int cy = 0; for (RowPanel cw : widgets) {
			cw.setBounds(new Rectangle(x, y + cy, w, h)); 
			cy = cy + config.getItemHeight(); // move next y
		}
	}
	
	@Override
	public Dimension calPreferredSize(IFigure container, int wHint, int hHint) {
		XTableModel model = factory.getModel();
		final int size = model.getRows().size();
		final XTableConfig config = model.getXTableConfig();
		final List<Column<?>> cols = model.getColumns().getColumns();
		final int w1 = cols.stream().mapToInt(Column::getPixel).sum();
		return new Dimension(w1, size * config.getItemHeight());
	}
}
