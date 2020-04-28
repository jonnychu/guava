package cn.nextop.guava.widgets.table.model.row;

/**
 * @author jonny
 */
public class AbstractRow implements IRow {
	//
	private long rowId;
	private boolean selected;
	
	/**
	 * 
	 */
	public AbstractRow() {
		rowId = getId();
	}
	
	@Override
	public long getRowId() {
		return rowId;
	}
	
	@Override
	public void setRowId(long rowId) {
		this.rowId = rowId;
	}
	
	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public void setSelected(boolean select) {
		selected = select;
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((this == obj) || (this.getId() == ((IRow)obj).getId()));
	}
}
