package cn.nextop.guava.widgets.table.support.formatter.impl;

import cn.nextop.guava.widgets.table.support.formatter.XTableFormatter;

/**
 * @author jonny
 */
public class XTableDefaultFormatter<T> implements XTableFormatter<T> {

	@Override
	public T parse(String value) {
		return null;
	}

	@Override
	public String valueOf(T value) {
		return value.toString();
	}
}
