package cn.nextop.guava.widgets.table.support.selection;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import cn.nextop.guava.widgets.table.model.XTableModel;

/**
 * 
 * @author jonny
 */
public abstract class AbstractSelection implements ISelection {
	//
	private XTableModel model;
	//
	private final PropertyChangeSupport listeners;
	protected SetMultimap<Long, Integer> selections;
	
	/**
	 * 
	 */
	public AbstractSelection() {
		this.selections = HashMultimap.create();
		this.listeners = new PropertyChangeSupport(this);
	}
	
	@Override
	public void clear() {
		this.selections.clear();
	}
	
	@Override
	public boolean isSelected(Long rowId, Integer colIds) {
		return selections.containsEntry(rowId, colIds);
	}
	
	/**
	 * 
	 */
	protected void fire(String string, int oldValue, int newValue) {
		listeners.firePropertyChange(string, oldValue, newValue);
	}
	
	@Override
	public void addPropListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}
	
	@Override
	public void removePropListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}
	
	/**
	 * 
	 */
	public XTableModel getXTableModel() {
		return model;
	}

	public void setXTableModel(XTableModel model) {
		this.model = model;
	}
}
