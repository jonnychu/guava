package cn.nextop.guava.widgets.combo.model.row;

/**
 * @author jonny
 */
public interface IRow {
	//
	Object getId();
	
	String displayName();
	
	boolean isSelected();

	void setSelected(boolean select);
}
