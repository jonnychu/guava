package cn.nextop.guava.widgets.spinner.model;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jonny
 */
public class XSpinnerModel {
	//
	private long lower;
	private long upper;
	private long step = 1;
	private AtomicLong value = new AtomicLong(0);
	
	/**
	 * 
	 */
	public long getStep() {
		return step;
	}

	public void setStep(long step) {
		this.step = step;
	}
	
	public long getLower() {
		return lower;
	}
	
	public void setLower(long lower) {
		this.lower = lower;
	}
	
	public long getUpper() {
		return upper;
	}
	
	public void setUpper(long upper) {
		this.upper = upper;
	}
	
	public long getValue() {
		return value.get();
	}
	
	public void setValue(long val) {
		if(val >= upper) val = upper;
		if(val <= lower) val = lower;
		this.value.set(val);
	}
	
	public void increment() {
		long val = value.addAndGet(step);
		if(val > upper) this.value.set(upper);
		if(val < lower) this.value.set(lower);
	}
	
	public void decrement() {
		long val = value.addAndGet(-step);
		if(val > upper) this.value.set(upper);
		if(val < lower) this.value.set(lower);
	}
	
	public void init(long upper, long lower, long step, long value) {
		setUpper(upper); setLower(lower); setStep(step); setValue(value);
	}
}
