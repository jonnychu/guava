package cn.nextop.guava.widgets.combo.model.colum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonny
 */
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
