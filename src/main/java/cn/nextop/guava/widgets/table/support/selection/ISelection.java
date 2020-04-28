package cn.nextop.guava.widgets.table.support.selection;

import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.List;

import cn.nextop.guava.widgets.table.render.widget.DefaultCellWidget;

/**
 * @author jonny
 */
public interface ISelection {
	
	final String PROPERTY_SELECT = "SELECTION";
	
	Collection<DefaultCellWidget> getSelection();
	
	void add(Long no, List<DefaultCellWidget> value);
	
	/**
	 * 
	 */
	void addPropListener(PropertyChangeListener listener);
	
	void removePropListener(PropertyChangeListener listener);
}
