package cn.nextop.guava.widgets.table.support.property;

import java.lang.annotation.Annotation;

/**
 * @author jonny
 */
public interface Property<T> {
	
	/**
	 * 
	 */
	String getName();
	
	/**
	 * 
	 */
	Object getValue(T target);
	
	void setValue(T target, Object value);
	
	/**
	 * 
	 */
	Class<?> getType();
	
	<A  extends Annotation> A getAnnotation(Class<A> clazz);
}
