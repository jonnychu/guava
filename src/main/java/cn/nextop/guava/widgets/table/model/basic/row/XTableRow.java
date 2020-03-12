package cn.nextop.guava.widgets.table.model.basic.row;

import java.util.List;

/**
 * @author jonny
 */
public class XTableRow {
	protected List<RowCell> cells;
	
	/**
	 * 
	 */
	public XTableRow() {
		// TODO Auto-generated constructor stub
	}

	public List<RowCell> getCells() {
		return cells;
	}

	public void setCells(List<RowCell> cells) {
		this.cells = cells;
	}
}
