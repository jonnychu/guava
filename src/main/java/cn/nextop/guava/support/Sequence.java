package cn.nextop.guava.support;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jonny
 */
public class Sequence {
	//
	private static final AtomicLong SEQUENCE = new AtomicLong(10000000);
	
	//
	public static long next() { return SEQUENCE.getAndIncrement(); }
}
