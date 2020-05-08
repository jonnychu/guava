package cn.nextop.guava.widgets.table.support.selection.impl;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import cn.nextop.guava.widgets.table.support.selection.AbstractSelection;

/**
 * @author jonny
 */
public class NopSelection extends AbstractSelection {

	@Override
	public void add(Long rowId, Integer colIds) {
		// NOP
	}
	
	@Override
	public SetMultimap<Long, Integer> getSelection() {
		return HashMultimap.create();
	}


}
