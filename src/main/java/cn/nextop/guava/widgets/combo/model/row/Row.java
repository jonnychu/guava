package cn.nextop.guava.widgets.combo.model.row;

import java.util.ArrayList;
import java.util.List;

import cn.nextop.guava.widgets.combo.model.cell.Cell;

public class Row {
	//
	private List<Cell> cells;
	
	public Row() {
		this.cells = new ArrayList<>();
	}
	
	public void add(Cell item) {
		this.cells.add(item);
	}
}
