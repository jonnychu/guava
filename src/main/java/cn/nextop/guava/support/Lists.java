package cn.nextop.guava.support;

import java.util.List;

/**
 * 
 * @author jonny
 */
public final class Lists {
	
	public static boolean isEmpty(List<?> r) {
		return r == null || r.size() == 0;
	}
}
