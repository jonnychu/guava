package cn.nextop.guava.support.debug;

import javax.annotation.Nullable;

/**
 * @author jonny
 */
public final class Assertion {
	
	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void notNull(@Nullable Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}
}
