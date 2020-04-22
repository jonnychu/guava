package cn.nextop.guava.widgets.table.render.panel.content;

import static cn.nextop.guava.support.Objects.cast;
import static cn.nextop.guava.support.swt.CGUtils.drawLine;
import static cn.nextop.guava.support.swt.Colors.COLOR_GRAY;
import static cn.nextop.guava.support.swt.Colors.COLOR_WHITE;

import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.support.swt.Colors;
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
		g.pushState();
		final Rectangle r = getBounds();
		final XTableModel model = factory.getModel();
		final XTableConfig config = model.getXTableConfig();
		List<Column<?>> cols = model.getColumns().getColumns();
		int cy = 0, idx = 0; while(cy < r.y + r.height) {
			if(idx++ % 2 == 0) g.setBackgroundColor(COLOR_WHITE);
			else g.setBackgroundColor(Colors.COLOR_LIGHT_GRAY);
			g.fillRectangle(r.x, cy, r.width, config.getItemHeight());
			cy = cy + config.getItemHeight();
		}
		int cx = 0; for (int i = 0; i < cols.size(); i++) {
			Column<?> column = cols.get(i);
			drawLine(g, cx, r.y, cx, r.y + r.height, COLOR_GRAY);
			cx = cx + r.x + column.getPixel();
			if(i == cols.size() - 1)
				drawLine(g, cx, r.y, cx, r.y + r.height, COLOR_GRAY);
				
		}
		g.popState();
		super.paintClientArea(g);
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
			cw.setBounds(new Rectangle(x, y + cy, w, h)); cy = cy + config.getItemHeight();
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
