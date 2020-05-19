package cn.nextop.guava.widgets.chart.model;

/**
 * @author jonny
 */
public class Range {
	//
	private double upper;
	private double lower;

	/**
	 * 
	 */
	public Range(double lower, double upper) {
		this.lower = lower;
		this.upper = upper;
	}

	/**
	 * 
	 */
	public double getUpper() {
		return upper;
	}

	public void setUpper(double upper) {
		this.upper = upper;
	}

	public double getLower() {
		return lower;
	}

	public void setLower(double lower) {
		this.lower = lower;
	}
}
