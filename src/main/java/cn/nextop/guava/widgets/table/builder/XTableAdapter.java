package cn.nextop.guava.widgets.table.builder;

import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.widgets.table.XTable;

/**
 * @author jonny
 */
public abstract class XTableAdapter<T> {
	
	public abstract XTable build(Composite cmp);
}
