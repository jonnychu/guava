package cn.nextop.guava.widgets.table.support.selection.impl;

import java.util.List;

import com.google.common.collect.SetMultimap;

import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.support.selection.AbstractSelection;
import cn.nextop.guava.widgets.table.support.selection.ISelection;

/**
 * @author jonny
 */
public class MultiRowSelection extends AbstractSelection {
	
	@Override
	public void add(Long rowId, Integer colId) {
		final XTableModel model = getXTableModel();
		boolean has = selections.containsKey(rowId);
		List<Integer> ids = model.getColumns().getAllColumnId();
		if(has) {
			this.selections.removeAll(rowId);
			fire(ISelection.PROPERTY_SELECT, ids.size(), 0); // always
		} else {
			for (Integer cid : ids) selections.put(rowId, cid);
			fire(ISelection.PROPERTY_SELECT, 0, ids.size()); // always
		}
	}
	
	@Override
	public SetMultimap<Long, Integer> getSelection() {
		return null;
	}
}
