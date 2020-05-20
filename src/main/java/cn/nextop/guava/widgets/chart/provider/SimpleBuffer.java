package cn.nextop.guava.widgets.chart.provider;

import java.util.AbstractCollection;
import java.util.Iterator;

/**
 * @author jonny
 */
public class SimpleBuffer<T> extends AbstractCollection<T>{
	//
	private int head;
	private int tail;
	private int count;
	private T[] buffer;
	private int bufferSize;
	
	@Override
	public Iterator<T> iterator() {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}
}
