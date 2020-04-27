package cn.nextop.guava.widgets.table.support.formatter.impl;

import java.util.Date;

import org.eclipse.nebula.widgets.formattedtext.DateTimeFormatter;

import cn.nextop.guava.support.Objects;
import cn.nextop.guava.widgets.table.support.formatter.XTableFormatter;

/**
 * @author jonny
 */
public class XTableDatetimeFormatter<T> implements XTableFormatter<T> {
	//
	private DateTimeFormatter formatter;
	
	/**
	 * 
	 */
	public XTableDatetimeFormatter(String pattern) {
		formatter = new DateTimeFormatter(pattern);
	}
	
	@Override
	public T parse(String text) {
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
