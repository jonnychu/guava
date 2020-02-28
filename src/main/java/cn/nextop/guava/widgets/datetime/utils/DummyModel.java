package cn.nextop.guava.widgets.datetime.utils;

/**
 * @author jonny
 */
public class DummyModel {
	//
	private boolean editable;
	private int year, month, day;

	public DummyModel(int year, int month, int day, boolean editable) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.editable = editable;
	}
	
	/**
	 * 
	 */
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
}
