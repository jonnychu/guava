package cn.nextop.guava.widgets.table.render.panel;

import org.eclipse.draw2d.DefaultRangeModel;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RangeModel;

import cn.nextop.guava.widgets.table.XTable;
import cn.nextop.guava.widgets.table.model.XTableColumns;
import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.render.AbstractTablePanel;
import cn.nextop.guava.widgets.table.render.panel.content.ContentScrollPanel;
import cn.nextop.guava.widgets.table.render.panel.header.HeaderScrollPanel;
import cn.nextop.guava.widgets.table.render.widget.internal.column.ColumnWidget;

/**
 * @author jonny
 */
public class XTablePanel extends AbstractTablePanel {
	//
	protected XTable table;
	protected HeaderScrollPanel headerPanel;
	protected ContentScrollPanel contentPanel;
	
	/**
	 * 
	 */
	public XTable getTable() { return table; }
	public HeaderScrollPanel getHeaderPanel() { return headerPanel; }
	public ContentScrollPanel getContentPanel() { return contentPanel; }

	/**
	 * 
	 */
	public XTablePanel(XTable table) {
		super("table.panel"); this.table = table;
		RangeModel horzRangeModel = new DefaultRangeModel();
		RangeModel vertRangeModel = new DefaultRangeModel();
		HeaderScrollPanel header = new HeaderScrollPanel(this.table, horzRangeModel, vertRangeModel);
		ContentScrollPanel content = new ContentScrollPanel(this.table, horzRangeModel, vertRangeModel);
		add(content); add(header);
	}

	@Override
	protected void layoutManager(IFigure container) {
		XTablePanel parent = (XTablePanel) container;
		XTableModel model = parent.getTable().getModel();
		final XTableColumns columns = model.getColumns();
		
		int width = 0;
		for (ColumnWidget cw : columns.getColumns()) {
			width =+ cw.getWidth();
		}
	}
}
