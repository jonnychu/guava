package cn.nextop.guava.widgets.table.model.row;

import cn.nextop.guava.support.Sequence;

/**
 * @author jonny
 */
public interface IRow {
	
	boolean isSelected();

	void setSelected(boolean select);
	
	long getRowId();
	
	void setRowId(long rowId);
	
	default long getId() {
		return Sequence.nextLong();
	}
}