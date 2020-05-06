package cn.nextop.guava.widgets.table.support.formatter.impl;

import java.util.Date;

import org.eclipse.nebula.widgets.formattedtext.DateFormatter;

import cn.nextop.guava.support.util.Objects;
import cn.nextop.guava.widgets.table.support.formatter.XTableFormatter;

/**
 * @author jonny
 */
public class XTableDateFormatter<T> implements XTableFormatter<T> {
	//
	private DateFormatter formatter;
	
	/**
	 * 
	 */
	public XTableDateFormatter(String pattern) {
		formatter = new DateFormatter(pattern);
	}
	
	@Override
	public T parse(String value) {
		return null;
	}

	@Override
	public String valueOf(T value) {
		Date date = null;
		if(value instanceof Date) {
			date = Objects.cast(value);
		} else {
			date = new Date((Long)value);
		}
		formatter.setValue(date);
		return formatter.getDisplayString();
	}
}
