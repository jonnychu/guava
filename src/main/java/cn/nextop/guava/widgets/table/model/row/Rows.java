package cn.nextop.guava.widgets.table.model.row;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonny
 */
public class Rows {
	private List<IRow> rows;
	
	public Rows(List<IRow> rows) {
		this.rows = new ArrayList<>(rows);
	}

	public List<IRow> getRows() {
		return rows;
	}

	public void setRows(List<IRow> rows) {
		this.rows.clear();
		this.rows.addAll(rows);
	}
}