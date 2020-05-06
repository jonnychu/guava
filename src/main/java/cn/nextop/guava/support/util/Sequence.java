package cn.nextop.guava.support.util;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jonny
 */
public class Sequence {
	// int
	private static final AtomicInteger SEQUENCE_INT = new AtomicInteger(1);
	public static int nextInt() { return SEQUENCE_INT.getAndIncrement(); }
	// long
	private static final AtomicLong SEQUENCE_LONG = new AtomicLong(1);
	public static long nextLong() { return SEQUENCE_LONG.getAndIncrement(); }
}
