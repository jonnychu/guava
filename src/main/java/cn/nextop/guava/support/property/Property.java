package cn.nextop.guava.support.property;

import static cn.nextop.guava.support.reflection.ReflectionUtils.findField;
import static cn.nextop.guava.support.reflection.ReflectionUtils.getterMethod;
import static cn.nextop.guava.support.reflection.ReflectionUtils.setterMethod;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author jonny
 */
public class Property<T> {
	//
	protected static final Object[] NULL = new Object[]{};
	
	//
	protected final String name;
	protected final Field field;
	protected final Method getter;
	protected final Method setter;
	protected final Class<?> clazz;
	
	/**
	 * 
	 */
	public Property(Class<T> clazz, String name) {
		this.clazz = clazz; this.name = name;
		this.field = findField(clazz, name);
		this.getter = getterMethod(clazz, name);
		this.setter = setterMethod(clazz, name);
	}
	
	/**
	 * 
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 */
	public Class<?> getType() {
		return getter.getReturnType();
	}
	
	/**
	 * 
	 */
	public Object getValue(final T target) {
		try {
			return this.getter.invoke(target, NULL);
		} catch (Exception e) {
			throw new RuntimeException("failed to invoke: " + getter.getName() + ", target: " + target, e);
		}
	}
	
	public void setValue(T target, Object value) {
		try {
			this.setter.invoke(target, new Object[]{value});
		} catch (Exception e) {
			throw new RuntimeException("failed to invoke: " + setter.getName() + ", target: " + target + ", value: " + value, e);
		}
	}
}
