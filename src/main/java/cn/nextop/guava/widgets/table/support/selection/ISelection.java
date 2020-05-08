package cn.nextop.guava.widgets.table.support.selection;

import java.beans.PropertyChangeListener;

import com.google.common.collect.SetMultimap;

import cn.nextop.guava.widgets.table.model.XTableModel;

/**
 * @author jonny
 */
public interface ISelection {
	
	final String PROPERTY_SELECT = "SELECTION";
	
	void clear();
	
	void setXTableModel(XTableModel model);
	
	SetMultimap<Long, Integer> getSelection();
	
	void add(Long rowId, Integer colId);
	
	boolean isSelected(Long rowId, Integer colId);
	
	/**
	 * 
	 */
	void addPropListener(PropertyChangeListener listener);
	
	void removePropListener(PropertyChangeListener listener);
}
