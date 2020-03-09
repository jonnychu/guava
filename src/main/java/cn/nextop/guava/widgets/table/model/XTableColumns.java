package cn.nextop.guava.widgets.table.model;

import java.util.ArrayList;
import java.util.List;

import cn.nextop.guava.widgets.table.render.table.widget.ColumnWidgets;

/**
 * @author jonny
 */
public class XTableColumns {
	//
	private List<ColumnWidgets> columns;

	public List<ColumnWidgets> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnWidgets> columns) {
		this.columns = columns;
	}
	
	public void addColumns(ColumnWidgets col) {
		if(columns == null) columns = new ArrayList<>();
		this.columns.add(col);
	}
}
