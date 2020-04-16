package cn.nextop.guava.widgets.table.model.column;

import java.util.ArrayList;
import java.util.List;

public class Columns {
	protected List<Column<?>> colums;
	
	public Columns() {
		this.colums = new ArrayList<>();
	}
	
	public void add(Column<?> colum) {
		this.colums.add(colum);
	}

	public List<Column<?>> getColums() {
		return colums;
	}
}
