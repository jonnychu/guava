package cn.nextop.guava.widgets.spinner.model;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author jonny
 */
public class XSpinnerModel<N> {
	//
	private N lower;
	private N upper;
	private N step;
	private N value;
	private Class<?> type;
	
	/**
	 * 
	 */
	public XSpinnerModel(Class<?> type) {
		this.type = type;
	}
	
	/**
	 * 
	 */
	public N getStep() { return this.step;}
	public void setStep(N step) {this.step = step;}
	
	public N getLower() {return this.lower;}
	public void setLower(N lower) {this.lower = lower;}
	
	public N getUpper() {return this.upper;}
	public void setUpper(N upper) {this.upper = upper;}
	
	public N getValue() {return this.value;}
	
	public void setValue(N val) {
		if(type == Long.class) {
			Long r = cast(val), l = cast(lower), u = cast(upper);
			if(r.compareTo(u) >= 0) val = upper; if(r.compareTo(l) <= 0) val = lower;
		} else if(type == Integer.class) {
			Integer r = cast(val), l = cast(lower), u = cast(upper);
			if(r.compareTo(u) >= 0) val = upper; if(r.compareTo(l) <= 0) val = lower;
		} else if(type == BigInteger.class) {
			BigInteger r = cast(val), l = cast(lower), u = cast(upper);
			if(r.compareTo(u) >= 0) val = upper; if(r.compareTo(l) <= 0) val = lower;
		} else if(type == BigDecimal.class) {
			BigDecimal r = cast(val), l = cast(lower), u = cast(upper);
			if(r.compareTo(u) >= 0) val = upper; if(r.compareTo(l) <= 0) val = lower;
		} 
		this.value = val;
	}
	
	public synchronized void increment() {
		if(type == Long.class) {
			Long r = cast(this.value), s = cast(step);
			setValue(cast(r + s));
		} else if(type == Integer.class) {
			Integer r = cast(this.value), s = cast(step);
			setValue(cast(r + s));
		} else if(type == BigInteger.class) {
			BigInteger r = cast(this.value), s = cast(step);
			setValue(cast(r.add(s)));
		} else if(type == BigDecimal.class) {
			BigDecimal r = cast(this.value), s = cast(step);
			setValue(cast(r.add(s)));
		} 
	}
	
	public synchronized void decrement() {
		if(type == Long.class) {
			Long r = cast(this.value), s = cast(step);
			setValue(cast(r - s));
		} else if(type == Integer.class) {
			Integer r = cast(this.value), s = cast(step);
			setValue(cast(r - s));
		} else if(type == BigInteger.class) {
			BigInteger r = cast(this.value), s = cast(step);
			setValue(cast(r.subtract(s)));
		} else if(type == BigDecimal.class) {
			BigDecimal r = cast(this.value), s = cast(step);
			setValue(cast(r.subtract(s)));
		} 
	}
	
	public void init(N lower, N upper, N step, N value) {
		setUpper(upper); setLower(lower); setStep(step); setValue(value);
	}
}
