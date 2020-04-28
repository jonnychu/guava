package cn.nextop.guava.widgets.table.support.selection.impl;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import cn.nextop.guava.widgets.table.render.widget.DefaultCellWidget;
import cn.nextop.guava.widgets.table.support.selection.ISelection;

/**
 * @author jonny
 */
public class MultiRowSelection implements ISelection {
	//
	private final PropertyChangeSupport listeners;
	protected SetMultimap<Long, DefaultCellWidget> selections;
	
	/**
	 * 
	 */
	public MultiRowSelection() {
		selections = HashMultimap.create();
		this.listeners = new PropertyChangeSupport(this);
	}
	
	@Override
	public void add(Long no, List<DefaultCellWidget> value) {
		boolean has = selections.containsKey(no);
		if(has) {
			Set<DefaultCellWidget> set = selections.get(no);
			int size = set.size(); for(DefaultCellWidget cw : set) 
			{ cw.setSelected(false); } selections.removeAll(no); 
			fire(ISelection.PROPERTY_SELECT, size, 0); // always
		} else {
			int size = value.size();for (DefaultCellWidget cw : value) {
				cw.setSelected(true); selections.put(no, cw);
			}
			fire(ISelection.PROPERTY_SELECT, 0, size); // always
		}
	}
	
	@Override
	public Collection<DefaultCellWidget> getSelection() {
		return selections.values();
	}
	
	/**
	 * 
	 */
	private void fire(String string, int oldValue, int newValue) {
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
}
