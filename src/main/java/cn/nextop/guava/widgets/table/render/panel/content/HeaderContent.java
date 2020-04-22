package cn.nextop.guava.widgets.table.render.panel.content;

import static cn.nextop.guava.support.Objects.cast;
import static cn.nextop.guava.support.swt.CGUtils.drawLine;
import static cn.nextop.guava.support.swt.CGUtils.fillRect;
import static cn.nextop.guava.support.swt.Colors.COLOR_GRAY;

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
import cn.nextop.guava.widgets.table.render.AbstractXTableColumnWidget;
import cn.nextop.guava.widgets.table.render.AbstractXTablePanel;

/**
 * @author jonny
 */
public class HeaderContent extends AbstractXTablePanel {
	
	/**
	 * 
	 */
	public HeaderContent(XTableFactory factory) {
		super("content.header", factory);
	}
	
	@Override
	protected void paintClientArea(Graphics g) {
		Rectangle r = getBounds(); g.pushState();
		fillRect(g, getBounds(), Colors.COLOR_WHITE);
		final XTableModel model = factory.getModel();
		List<Column<?>> cols = model.getColumns().getColumns();
		int cx = 0; for (int i = 0; i < cols.size(); i++) {
			Column<?> column = cols.get(i);
			drawLine(g, cx, r.y, cx, r.y + r.height, COLOR_GRAY);
			cx = cx + r.x + column.getPixel();
			if(i == cols.size() - 1)
				drawLine(g, cx, r.y, cx, r.y + r.height, COLOR_GRAY);
				
		}
		drawLine(g, r.x, r.y, r.x + r.width, r.y, COLOR_GRAY);
		drawLine(g, r.x, r.y + r.height - 1, r.x + r.width, r.y + r.height - 1, COLOR_GRAY);
		g.popState(); super.paintClientArea(g);
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		HeaderContent hc = cast(container);
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
