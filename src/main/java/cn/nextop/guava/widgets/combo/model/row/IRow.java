package cn.nextop.guava.widgets.combo.model.row;

import cn.nextop.guava.support.Sequence;

/**
 * @author jonny
 */
public interface IRow {
	
	String displayName();
	
	boolean isSelected();

	void setSelected(boolean select);
	
	default Object getId() {
		return Sequence.next();
	}
}