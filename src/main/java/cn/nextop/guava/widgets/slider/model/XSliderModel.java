package cn.nextop.guava.widgets.slider.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @author jonny
 */
public class XSliderModel {
	//
	private double lower;
	private double upper;
	private double value;
	//
	private final PropertyChangeSupport listeners;
	public static final String PROPERTY_VALUE = "VALUE";
	
	/**
	 * 
	 */
	public XSliderModel() {
		this.lower = 0; this.upper = 100; this.value = 0;
		this.listeners = new PropertyChangeSupport(this);
	}
	
	/**
	 * 
	 */
	public double getLower() {
		return lower;
	}
	public void setLower(double lower) {
		this.lower = lower;
	}
	
	public double getUpper() {
		return upper;
	}
	public void setUpper(double upper) {
		this.upper = upper;
	}
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		double ov = this.value;
		this.value = value;
		if(this.value <= this.lower) this.value = this.lower;
		if(this.value >= this.upper) this.value = this.upper;
		fire(PROPERTY_VALUE, ov, getValue());
	}
	
	public void init(double lower, double upper, double value) {
		this.lower = lower; this.upper = upper; this.value = value;
	}
	
	/**
	 * 
	 */
	public void addPropListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}
	
	public void removePropListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}
	
	protected void fire(String string, double oldValue, double newValue) {
		listeners.firePropertyChange(string, oldValue, newValue);
	}
}
