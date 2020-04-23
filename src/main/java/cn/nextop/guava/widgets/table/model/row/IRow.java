package cn.nextop.guava.widgets.table.model.row;

import cn.nextop.guava.support.Sequence;

/**
 * @author jonny
 */
public interface IRow {
	
	String displayName();
	
	boolean isSelected();

	void setSelected(boolean select);
	
	default long getId() {
		return Sequence.next();
	}
}