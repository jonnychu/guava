package cn.nextop.guava.widgets.table.render.table;

import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.render.AbstractPanel;
import cn.nextop.guava.widgets.table.render.table.widget.ColumnWidgets;

/**
 * @author jonny
 */
public class ContentPanel extends AbstractPanel {
	//
	protected XTablePanel tablePanel;
	
	/**
	 * 
	 */
	public XTablePanel getXTablePanel() { return tablePanel; }

	/**
	 * @param name
	 */
	public ContentPanel(XTablePanel tablePanel) {
		super("table.header");
		this.tablePanel = tablePanel;
	}

	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		g.setBackgroundColor(Colors.COLOR_WHITE); 
		g.fillRectangle(getBounds());
	}
	
	@Override
	public Dimension getMinimumSize(int wHint, int hHint) {
		XTableModel model = getXTablePanel().getXTable().getModel();
		final List<ColumnWidgets> columns = model.getColumns().getColumns();
		
		int total = 0;
		for (ColumnWidgets c : columns) {
			total = total + c.getWidth();
		}
		return new Dimension(total, hHint);
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		ContentPanel content = (ContentPanel) container;
		XTableModel model = content.getXTablePanel().getXTable().getModel();
		final List<ColumnWidgets> columns = model.getColumns().getColumns();
		//
		final Rectangle r = content.getBounds();
		int x = r.x, y = r.y;
		for (int i = 0; i < columns.size(); i++) {
			ColumnWidgets cw = columns.get(i); int w = cw.getWidth();
			Rectangle r1 = new Rectangle(x, y, w, 22); cw.setBounds(r1);
			x = x + w;
		}
		System.out.println("content layout" + r);
	}
}
