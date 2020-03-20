package cn.nextop.guava.widgets.table.model.basic.column;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonny
 */
public class XTableColumns {
	//
	private int height = 22;
	private List<XTableColumn> columns;

	/**
	 * 
	 */
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public List<XTableColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<XTableColumn> columns) {
		this.columns = columns;
	}
	
	public void addColumns(XTableColumn col) {
		if(columns == null) columns = new ArrayList<>();
		this.columns.add(col);
	}
}
