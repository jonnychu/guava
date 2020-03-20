package cn.nextop.guava.widgets.table.model.basic.row.impl;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


/**
 * @author jonny
 */
public class XTableDefaultRows<T> {
	protected final List<XTableDefaultRow<T>> rows = new ArrayList<>();
	
	/**
	 * 
	 */
	public int size() {
		return rows.size();
	}
	
	public void clear() {
		this.rows.clear();
	}
	
	public XTableDefaultRow<T> get(int index) {
		return this.rows.get(index);
	}
	
	public Iterator<XTableDefaultRow<T>> iterator() {
		return this.rows.iterator();
	}
	
	/**
	 * 
	 */
	public void reset(final List<T> rows) {
		this.rows.clear(); 
		this.rows.addAll(XTableDefaultRow.valueOf(rows));
	}
	
	public void append(final List<T> rows) {
		if(rows == null) return;
		this.rows.addAll(XTableDefaultRow.valueOf(rows));
	}
	
	/**
	 * 
	 */
	public final void sort(final Comparator<XTableDefaultRow<T>> cmp) {
		//NOP
	}
	
	/**
	 * 
	 */
	public int move(final int index, final int delta) {
		final int size = this.rows.size();
		if(index < 0 || index > size) return -1;
		final XTableDefaultRow<T> src = this.rows.get(index);
		final int dst = min(size - 1, max(0, index + delta));
		this.rows.remove(index); this.rows.add(dst, src); return dst;
	}
}
