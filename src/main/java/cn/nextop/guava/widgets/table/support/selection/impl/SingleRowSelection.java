package cn.nextop.guava.widgets.table.support.selection.impl;

import java.util.List;

import com.google.common.collect.SetMultimap;

import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.support.selection.AbstractSelection;
import cn.nextop.guava.widgets.table.support.selection.ISelection;

/**
 * @author jonny
 */
public class SingleRowSelection extends AbstractSelection {
	
	@Override
	public void add(Long rowId, Integer colId) {
		selections.clear();XTableModel model = getXTableModel();
		List<Integer> ids = model.getColumns().getAllColumnId();
		for(Integer tColId : ids) selections.put(rowId, tColId);
		fire(ISelection.PROPERTY_SELECT, 0, ids.size()); // always
	}
	
	@Override
	public SetMultimap<Long, Integer> getSelection() {
		return selections;
	}
}
