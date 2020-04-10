package cn.nextop.guava.widgets.combo.support.property;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Predicate;

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
		this.field = getField(clazz, name);
		this.getter = findPublicMethod(clazz, name);
		this.setter = findPublicMethod(clazz, name);
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
