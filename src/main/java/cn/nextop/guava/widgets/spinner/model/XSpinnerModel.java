package cn.nextop.guava.widgets.spinner.model;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jonny
 */
public class XSpinnerModel {
	private int lower;
	private int upper;
	private AtomicLong value = new AtomicLong(0);
	
	/**
	 * 
	 */
	public int getLower() {
		return lower;
	}
	
	public void setLower(int lower) {
		this.lower = lower;
	}
	
	public int getUpper() {
		return upper;
	}
	
	public void setUpper(int upper) {
		this.upper = upper;
	}
	
	public long getValue() {
		return value.get();
	}
	
	public void setValue(long val) {
		this.value.set(val);
	}
	
	public void increment() {
		this.value.getAndIncrement();
	}
	
	public void decrement() {
		this.value.getAndDecrement();
	}
}
