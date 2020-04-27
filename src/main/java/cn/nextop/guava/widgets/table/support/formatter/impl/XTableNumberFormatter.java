package cn.nextop.guava.widgets.table.support.formatter.impl;

import org.eclipse.nebula.widgets.formattedtext.NumberFormatter;

import cn.nextop.guava.widgets.table.support.formatter.XTableFormatter;

/**
 * @author jonny
 */
public class XTableNumberFormatter<T> implements XTableFormatter<T> {
	//
	private final NumberFormatter formatter;
	
	/**
	 * 
	 */
	public XTableNumberFormatter(String pattern) {
		this.formatter = new NumberFormatter(pattern);
	}
	
	@Override
	public T parse(String value) {
		return null;
	}

	@Override
	public String valueOf(T value) {
		formatter.setValue(value);
		return formatter.getDisplayString();
	}
}
