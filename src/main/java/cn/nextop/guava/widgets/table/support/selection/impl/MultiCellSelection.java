package cn.nextop.guava.widgets.table.support.selection.impl;

import com.google.common.collect.SetMultimap;

import cn.nextop.guava.widgets.table.support.selection.AbstractSelection;
import cn.nextop.guava.widgets.table.support.selection.ISelection;

/**
 * 
 * @author jonny
 */
public class MultiCellSelection extends AbstractSelection {
	@Override
	public void add(Long rowId, Integer colId) {
		boolean has = selections.containsEntry(rowId, colId);
		if(has) {
			selections.remove(rowId, colId);
			fire(ISelection.PROPERTY_SELECT, colId, 0); // always
		} else {
			selections.put(rowId, colId);
			fire(ISelection.PROPERTY_SELECT, 0, colId); // always
		}
	}
	
	@Override
	public SetMultimap<Long, Integer> getSelection() {
		return selections;
	}
}
