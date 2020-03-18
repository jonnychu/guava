package cn.nextop.guava.scroll;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * @author jonny
 */
public class XRangeModel {
	//
	private int min, max, value, extent;
	
	/**
	 * 
	 */
	public XRangeModel() {
		this.min = 0; this.max = 100;
		this.value = 0; this.extent = 20;
	}

	public int getMin() {
		return min;
	}
	
	public int getMax() {
		return max;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getExtent() {
		return extent;
	}
	
	public void setMin(int min) {
		this.min = min;
		setValue(getValue());
	}
	
	public void setMax(int max) {
		this.max = max;
		setValue(getValue());
	}
	
	public void setExtent(int extent) {
		this.extent = extent;
		setValue(getValue());
	}
	
	public void setValue(int val) {
		int m1 = max - extent;
		int m2 = min(m1, val);
		this.value = max(min, m2);
	}
	
	public void setAll(int min, int ext, int max) {
		this.min = min; this.extent = ext; this.max = max;
		setValue(getValue());
	}
}
