package cn.nextop.guava.widgets.progress.circle.model;

/**
 * @author jonny
 */
public class XCircleProgressModel {
	//
	private float min;
	private float max;
	private float value;
	
	public float getMin() {
		return min;
	}
	public void setMin(float min) {
		this.min = min;
	}
	
	public float getMax() {
		return max;
	}
	public void setMax(float max) {
		this.max = max;
	}
	
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		if(value >= max) this.value = max;
		if(value <= min) this.value = min;
		this.value = value;
	}
	
	public void init(float min, float max, float value) {
		setMin(min); setMax(max); setValue(value);
	}
}
