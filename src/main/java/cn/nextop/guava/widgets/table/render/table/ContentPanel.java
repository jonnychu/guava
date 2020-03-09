package cn.nextop.guava.widgets.table.render.table;

import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
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
	protected void layoutManager(IFigure container) {
		ContentPanel parent = (ContentPanel) container;
		XTableModel model = parent.getXTablePanel().getXTable().getModel();
		final List<ColumnWidgets> columns = model.getColumns().getColumns();
		//
		parent.setBounds(tablePanel.getBounds());
		final Rectangle r = parent.getBounds();
		for (int i = 0; i < columns.size(); i++) {
			ColumnWidgets cw = columns.get(i); int w = cw.getWidth();
			Rectangle r1 = new Rectangle(r.x + i * w, r.y, w, 22); cw.setBounds(r1);
		}
	}
}
