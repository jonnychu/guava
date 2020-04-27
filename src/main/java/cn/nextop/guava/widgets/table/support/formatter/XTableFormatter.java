package cn.nextop.guava.widgets.table.support.formatter;

/**
 * @author jonny
 */
public interface XTableFormatter<T> {
	
	T parse(String value);
	
	String valueOf(T value);
}
