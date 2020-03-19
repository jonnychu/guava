package cn.nextop.guava.widgets.table.support.property.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Predicate;

import cn.nextop.guava.widgets.table.support.property.Property;

/**
 * @author jonny
 */
public class XProperty<T> implements Property<T> {
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
	public XProperty(Class<T> clazz, String name) {
		this.clazz = clazz; this.name = name;
		this.field = getField(clazz, name);
		this.getter = findPublicMethod(clazz, name);
		this.setter = findPublicMethod(clazz, name);
	}
	
	/**
	 * 
	 */
	@Override
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 */
	@Override
	public Class<?> getType() {
		return this.field.getType();
	}
	
	/**
	 * 
	 */
	@Override
	public <A  extends Annotation> A getAnnotation(Class<A> clazz) {
		A r1 = this.field.getAnnotation(clazz); if(r1 != null) return r1;
		A r2 = this.getter.getAnnotation(clazz); if(r2 != null) return r2;
		A r3 = this.setter.getAnnotation(clazz); if(r3 != null) return r3;
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public Object getValue(final T target) {
		try {
			return this.getter.invoke(target, NULL);
		} catch (Exception e) {
			throw new RuntimeException("failed to invoke: " + getter.getName() + ", target: " + target, e);
		}
	}
	
	@Override
	public void setValue(T target, Object value) {
		try {
			this.setter.invoke(target, new Object[]{value});
		} catch (Exception e) {
			throw new RuntimeException("failed to invoke: " + setter.getName() + ", target: " + target + ", value: " + value, e);
		}
	}
	
	/**
	 * 
	 */
	public Method findPublicMethod(Class<?> clazz, String name) {
		try { return clazz.getMethod(name); } catch (NoSuchMethodException e) { return null; }
	}
	
	public Field getField (final Class<?> clazz, final String name) {
		final Predicate<Field> t = v -> v.getName().equals(name);
		if(clazz != null) for(Class<?> v = clazz; v != null; v = v.getSuperclass()) {
			for(Field w : v.getDeclaredFields()) if(t.test(w)) { w.setAccessible(true); return w; }
		}
		return null;
	}
	

}
