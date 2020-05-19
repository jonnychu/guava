package cn.nextop.guava.widgets.chart.provider;

/**
 * @author jonny
 */
public class Sample implements ISample {
	private final long time;
	private final double value;

	/**
	 * 
	 */
	public Sample(long time, double value) {
		this.time = time;
		this.value = value;
	}

	@Override
	public long getTime() {
		return time;
	}

	@Override
	public double getValue() {
		return value;
	}
}
