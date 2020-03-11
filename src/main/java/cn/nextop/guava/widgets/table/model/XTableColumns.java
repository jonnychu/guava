package cn.nextop.guava.widgets.table.model;

import java.util.ArrayList;
import java.util.List;

import cn.nextop.guava.widgets.table.render.widget.internal.column.ColumnWidget;

/**
 * @author jonny
 */
public class XTableColumns {
	//
	private List<ColumnWidget> columns;

	public List<ColumnWidget> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnWidget> columns) {
		this.columns = columns;
	}
	
	public void addColumns(ColumnWidget col) {
		if(columns == null) columns = new ArrayList<>();
		this.columns.add(col);
	}
}
