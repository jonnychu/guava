package cn.nextop.guava.support;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author jonny
 */
public final class Lists {
	
	public static boolean isEmpty(List<?> r) {
		return r == null || r.size() == 0;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> toList (final T... values) {
		if (values == null) { return new ArrayList<>(0); }
		final List<T> r = new ArrayList<T>(values.length);
		for (T value : values) { r.add(value); } return r;
	}
}
