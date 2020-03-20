package cn.nextop.guava.widgets.table.model.basic.row.impl;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import cn.nextop.guava.widgets.table.model.basic.column.XTableColumn;
import cn.nextop.guava.widgets.table.model.basic.row.XTableRow;


/**
 * @author jonny
 */
public class XTableDefaultRow<T> implements XTableRow {
	//
	protected Object id;
	protected T princiapl;
	//
	private static final AtomicLong SEQUENCE = new AtomicLong(1024);
	
	/**
	 * 
	 */
	public XTableDefaultRow(T principal) {
		this.princiapl = principal;
		this.id = SEQUENCE.getAndIncrement();
	}
	
	/**
	 * 
	 */
	public final Object getId() {
		return this.id;
	}
	
	public final T getPrincipal() {
		return this.princiapl;
	}
	
	public T replace(T principal) {
		final T r = this.princiapl;
		this.princiapl = principal;
		return r;
	}
	
	/**
	 * 
	 */
	public <V> V getValue(final XTableColumn column) {
		Object v = column.getProperty().getValue(cast(this.princiapl));
		return cast(v);
	}

	public void setValue(XTableColumn column, Object next) {
		//NOP
	}
	
	/**
	 * 
	 */
	public static <T> List<XTableDefaultRow<T>> valueOf(List<T> values) {
		if (values == null) return new ArrayList<>(0);
		final List<XTableDefaultRow<T>> r = new ArrayList<>(values.size());
		for (T value : values) r.add(new XTableDefaultRow<>(value)); return r;
	}
}
