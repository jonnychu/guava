package cn.nextop.guava.widgets.table.render.panel;

import static cn.nextop.guava.support.Objects.cast;

import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.support.swt.CGUtils;
import cn.nextop.guava.support.swt.Colors;
import cn.nextop.guava.widgets.table.builder.internal.XTableFactory;
import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.model.column.Column;
import cn.nextop.guava.widgets.table.model.config.XTableConfig;
import cn.nextop.guava.widgets.table.render.AbstractXTableCellWidget;
import cn.nextop.guava.widgets.table.render.AbstractXTablePanel;

/**
 * @author jonny
 */
public class RowPanel extends AbstractXTablePanel {
	//
	protected boolean press;
	
	/**
	 * 
	 */
	public RowPanel(XTableFactory factory) {
		super("row.panel"); this.factory = factory;
		addMouseListener(new MouseListener.Stub());
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		if(press) {
			CGUtils.fillRect(g, getBounds(), Colors.COLOR_WIDGET_SELECTED);
		}
		super.paintFigure(g);
	}
	
	@Override
	protected void paintClientArea(Graphics g) {
		super.paintClientArea(g);
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		final RowPanel parent = cast(container);
		final XTableModel model = factory.getModel();
		XTableConfig config = model.getXTableConfig();
		List<Column<?>> cols = model.getColumns().getColumns();
		if(cols == null || cols.size() == 0) return;
		
		//
		Rectangle r = parent.getClientArea();
		final int x = r.x, y = r.y, h = config.getItemHeight();
		List<AbstractXTableCellWidget> widgets = cast(getChildren());
		int cx = 0; for (AbstractXTableCellWidget cw : widgets) {
			final int px = cw.getColumn().getPixel();
			cw.setBounds(new Rectangle(x + cx, y, px, h)); cx = cx + px;
		}
	}
	
	@Override
	public void handleMousePressed(MouseEvent event) {
		super.handleMousePressed(event); press = true; repaint();
	}
	
	@Override
	public void handleMouseReleased(MouseEvent event) {
		super.handleMouseReleased(event); press = false; repaint();
	}
}
