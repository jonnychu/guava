package cn.nextop.guava.widgets.table.support.selection.impl;

import java.util.Collection;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

import cn.nextop.guava.widgets.table.render.widget.DefaultCellWidget;
import cn.nextop.guava.widgets.table.support.selection.ISelection;

/**
 * @author jonny
 */
public class RowSelection implements ISelection {
	//
	protected Multimap<Integer, DefaultCellWidget> selections;
	
	/**
	 * 
	 */
	public RowSelection() {
		selections = LinkedListMultimap.create(16);
	}
	
	@Override
	public void add(int no, DefaultCellWidget value) {
		selections.put(no, value);
	}
	
	@Override
	public Collection<DefaultCellWidget> getSelection() {
		return selections.values();
	}
}
