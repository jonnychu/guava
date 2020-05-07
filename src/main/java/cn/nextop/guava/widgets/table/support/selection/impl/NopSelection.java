package cn.nextop.guava.widgets.table.support.selection.impl;

import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

import cn.nextop.guava.widgets.table.support.selection.AbstractSelection;

/**
 * @author jonny
 */
public class NopSelection extends AbstractSelection {

	@Override
	public SetMultimap<Long, Integer> getSelection() {
		return HashMultimap.create();
	}

	@Override
	public void add(Long rowId, List<Integer> colIds) {
	}
}
