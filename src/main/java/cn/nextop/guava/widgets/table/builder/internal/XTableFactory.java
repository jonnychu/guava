package cn.nextop.guava.widgets.table.builder.internal;

import static cn.nextop.guava.support.Objects.cast;

import java.util.List;

import org.eclipse.swt.widgets.Canvas;

import cn.nextop.guava.support.Objects;
import cn.nextop.guava.widgets.AbstractBuilder;
import cn.nextop.guava.widgets.table.XTable;
import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.model.column.Column;
import cn.nextop.guava.widgets.table.model.row.IRow;
import cn.nextop.guava.widgets.table.render.AbstractXTableCellWidget;
import cn.nextop.guava.widgets.table.render.AbstractXTableColumnWidget;
import cn.nextop.guava.widgets.table.render.panel.RowPanel;
import cn.nextop.guava.widgets.table.render.panel.XTablePanel;
import cn.nextop.guava.widgets.table.render.panel.content.DataContent;
import cn.nextop.guava.widgets.table.render.panel.content.HeadContent;
import cn.nextop.guava.widgets.table.render.widget.DefaultCellRenderWidget;
import cn.nextop.guava.widgets.table.render.widget.DefaultColumnResizeWidget;
import cn.nextop.guava.widgets.table.render.widget.external.XTableWidget;

/**
 * @author jonny
 */
public class XTableFactory extends AbstractBuilder {
	//
	private XTable table;
	private DataContent dc;
	private HeadContent hc;
	private XTablePanel tablePanel;
	
	
	@Override
	public XTablePanel build(Canvas parent) {
		this.table = cast(parent);
		this.tablePanel = new XTablePanel(this);
		this.tablePanel.setDataContents(this.dc = new DataContent(this));
		this.tablePanel.setHeadContents(this.hc = new HeadContent(this));
		return tablePanel;
	}
	
	public void buildData() {
		final XTableModel model = this.table.getModel();
		final List<IRow> rows = model.getRows().getRows();
		final List<Column<?>> cols = model.getColumns().getColumns();
		if(rows == null || rows.size() == 0 || cols == null || cols.size() == 0) return; 
		int size = cols.size(); for (IRow row : rows) {
			final RowPanel rp = new RowPanel(this);
			AbstractXTableCellWidget[] cw = new AbstractXTableCellWidget[size];
			for (int i = 0; i < size; i++) {
				try {
					Column<?> column = cols.get(i);
					cw[i] = cast(column.getCellWidget().newInstance());
					cw[i].setColumn(column); cw[i].setRow(row); rp.add(cw[i]);
					
					if(cw[i] instanceof DefaultCellRenderWidget) {
						XTableWidget[] tws = column.getCellRenderWidgets();
						if(tws == null || tws.length == 0) continue;
						for (int j = 0; j < tws.length; j++) {
							XTableWidget tw = tws[j].getClass().newInstance();
							tw.text(tws[j].getText()).action(tws[j].getAction()).setRow(row);
							cw[i].add(tw);
						}
					}
				} catch (InstantiationException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
			this.dc.add(rp);
		}
	}
	
	public void buildHeader() {
		if(this.hc == null) return; this.hc.removeAll();
		final XTableModel model = this.table.getModel();
		final List<Column<?>> cols = model.getColumns().getColumns();
		if(cols == null || cols.size() == 0) return; int size = cols.size(); 
		AbstractXTableColumnWidget[] cw1 = new AbstractXTableColumnWidget[size];
		AbstractXTableColumnWidget[] cw2 = new AbstractXTableColumnWidget[size];
		for (int i = 0; i < size; i++) {
			try {
				Column<?> column = cols.get(i);
				//
				Class<?> clazz = column.getColumnwidget();
				cw1[i] = Objects.cast(clazz.newInstance());
				cw1[i].setColumn(column); this.hc.add(cw1[i]);
				// resize
				cw2[i] = new DefaultColumnResizeWidget();
				cw2[i].setColumn(column); this.hc.add(cw2[i]);
				//
				column.addPropListener(tablePanel);// add column resize
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * 
	 */
	public XTable getTable() {
		return this.table;
	}
	
	public XTableModel getModel() {
		return this.table.getModel();
	}
	
	public XTablePanel getTablePanel() {
		return this.tablePanel;
	}
	
	public DataContent getDataContent() {
		return this.dc;
	}
	
	public HeadContent getHeadContent() {
		return this.hc;
	}
}
