package cn.nextop.guava.widgets.table.support.selection;

import java.util.Collection;

import cn.nextop.guava.widgets.table.render.widget.DefaultCellWidget;

/**
 * @author jonny
 */
public interface ISelection {
	
	Collection<DefaultCellWidget> getSelection();
	
	void add(int no, DefaultCellWidget value);
}
