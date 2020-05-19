package cn.nextop.guava.widgets.chart.model;

/**
 * @author jonny
 */
public class Tick {
	//
	private String text;
	private double value;
	private int position;
	private double tPosition;

	/**
	 * 
	 */
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public double gettPosition() {
		return tPosition;
	}

	public void settPosition(double tPosition) {
		this.tPosition = tPosition;
	}
}
