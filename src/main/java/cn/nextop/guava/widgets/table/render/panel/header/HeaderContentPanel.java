package cn.nextop.guava.widgets.table.render.panel.header;

import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.render.AbstractTablePanel;
import cn.nextop.guava.widgets.table.render.widget.internal.column.ColumnWidget;

/**
 * @author jonny
 */
public class HeaderContentPanel extends AbstractTablePanel {
	//
	protected HeaderViewport headerPanel;
	
	/**
	 * 
	 */
	public HeaderViewport getHeaderPanel() { return headerPanel; }
	
	/**
	 * 
	 */
	public HeaderContentPanel(HeaderViewport headerPanel) {
		super("table.header");
		this.headerPanel = headerPanel;
	}

	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		g.setBackgroundColor(Colors.COLOR_WHITE); 
		g.fillRectangle(getBounds());
	}
	
	@Override
	public Dimension getMinimumSize(int wHint, int hHint) {
		XTableModel model = getHeaderPanel().getTablePanel().getTable().getModel();
		final List<ColumnWidget> columns = model.getColumns().getColumns();
		
		int total = 0;
		for (ColumnWidget c : columns) {
			total = total + c.getWidth();
		}
		return new Dimension(total, 22);
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		HeaderContentPanel content = (HeaderContentPanel) container;
		XTableModel model = getHeaderPanel().getTablePanel().getTable().getModel();
		final List<ColumnWidget> columns = model.getColumns().getColumns();
		//
		final Rectangle r = content.getBounds();
		int x = r.x, y = r.y;
		for (int i = 0; i < columns.size(); i++) {
			ColumnWidget cw = columns.get(i); int w = cw.getWidth();
			Rectangle r1 = new Rectangle(x, y, w, 22); cw.setBounds(r1);
			x = x + w;
		}
	}
}
