package cn.nextop.guava.widgets.table.model.row;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author jonny
 */
public class Rows {
	//
	private List<IRow> rows;
	
	public Rows() {
		this.rows = new ArrayList<>();
	}

	public List<IRow> getRows() {
		return rows;
	}

	public void setRows(List<IRow> rows) {
		this.rows.clear();
		this.rows.addAll(rows);
	}
	
	public int size() {
		if(rows == null) return 0;
		else return rows.size();
	}
	
	public void defaultSort(boolean asc) {
		if(rows == null) return;
		if(asc) rows.stream().sorted(Comparator.comparing(IRow::getId));
		else rows.stream().sorted(Comparator.comparing(IRow::getId).reversed());
	}
}