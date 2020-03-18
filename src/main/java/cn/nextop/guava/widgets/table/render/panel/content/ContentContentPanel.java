package cn.nextop.guava.widgets.table.render.panel.content;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.table.XTable;
import cn.nextop.guava.widgets.table.model.XTableColumns;
import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.model.basic.column.XTableColumn;
import cn.nextop.guava.widgets.table.model.basic.row.XTableRow;
import cn.nextop.guava.widgets.table.model.basic.row.XTableRows;
import cn.nextop.guava.widgets.table.render.AbstractTablePanel;
import cn.nextop.guava.widgets.table.render.panel.XTablePanel;
import cn.nextop.guava.widgets.table.support.util.Objects;

/**
 * @author jonny
 */
public class ContentContentPanel extends AbstractTablePanel {
	//
	protected ContentViewport headerPanel;
	
	/**
	 * 
	 */
	public ContentViewport getHeaderPanel() { return headerPanel; }
	
	/**
	 * 
	 */
	public ContentContentPanel(ContentViewport headerPanel) {
		super("table.header");
		this.headerPanel = headerPanel;
	}

	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		XTablePanel tablePanel = headerPanel.getTablePanel();
		final XTable table = tablePanel.getTable();
		final XTableModel<?> model = table.getModel();
		final XTableRows<?> tableRows = model.getRows();
		final XTableColumns tableColumns = model.getColumns();
		final List<XTableColumn> columns = tableColumns.getColumns();
		
		final Rectangle r = getClientArea(); 
		int height = 22, x = 0, y = 0;
		for (Iterator<?> iter = tableRows.iterator(); iter.hasNext();) {
			XTableRow<?> row = Objects.cast(iter.next());
			for (int i = 0; i < columns.size(); i++) {
				XTableColumn col = columns.get(i);
				Object text = row.getValue(col);
				g.drawText(text.toString(), x, y);
				x = x + col.getWidth(); y = y + height;
				g.drawLine(x, r.y, x, r.bottom());
				g.drawLine(r.x, y, r.right(), y);
			}
			x = 0;
		}
	}
	
	@Override
	public Dimension getMinimumSize(int wHint, int hHint) {
		XTableModel<?> model = getHeaderPanel().getTablePanel().getTable().getModel();
		final List<XTableColumn> columns = model.getColumns().getColumns();
		
		int total = 0;
		for (XTableColumn c : columns) {
			total = total + c.getWidth();
		}
		return new Dimension(total, columns.size() * 23);
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		ContentContentPanel content = (ContentContentPanel) container;
		XTableModel<?> model = getHeaderPanel().getTablePanel().getTable().getModel();
		final int size = model.getRows().size();
		//
		
		final Rectangle r = content.getClientArea();
		content.setSize(r.width, size * 22);
	}
}
