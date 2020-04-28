package cn.nextop.guava.widgets.table.support.selection.impl;

import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import cn.nextop.guava.widgets.table.render.widget.DefaultCellWidget;
import cn.nextop.guava.widgets.table.support.selection.ISelection;

/**
 * @author jonny
 */
public class NopSelection implements ISelection {

	@Override
	public Collection<DefaultCellWidget> getSelection() {
		return Collections.emptyList();
	}

	@Override
	public void add(Long no, List<DefaultCellWidget> value) {
		// NOP
	}

	@Override
	public void addPropListener(PropertyChangeListener listener) {
	}

	@Override
	public void removePropListener(PropertyChangeListener listener) {
	}
}
