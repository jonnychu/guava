package cn.nextop.guava.widgets.datetime.render.utils;

import static java.util.Arrays.stream;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author jonny
 */
public class DummyCalendar {
	//
	private int year, month, day;
	//
	private DateFormatSymbols symbols = new DateFormatSymbols(Locale.ENGLISH);
	
	/**
	 * 
	 */
	public int getYear() { return year; }
	public void setYear(int year) {	this.year = year; }
	public int getMonth() { return month; }
	public void setMonth(int month) { this.month = month; }
	public int getDay() { return day; }
	public void setDay(int day) { this.day = day; }

	/**
	 * 
	 */
	public DummyCalendar(long date) {
		Calendar c = new GregorianCalendar(Locale.ENGLISH);
		c.setTime(new Date(date));
		this.year = c.get(Calendar.YEAR); 
		this.month = c.get(Calendar.MONTH); 
		this.day = c.get(Calendar.DATE);
	}
	
	public DummyCalendar(int year, int month, int day) {
		this.year = year; this.month = month; this.day = day;
	}
	
	/**
	 * 
	 */
	public String getMonthSymbol() {
		return this.symbols.getShortMonths()[this.month];
	}
	
	public String getMonthSymbol(int index) {
		return this.symbols.getShortMonths()[index];
	}
	
	public String[] getWeekSymbols() {
		return stream(this.symbols.getShortWeekdays())
				.filter((e) -> !e.equals(""))
				.collect(Collectors.toList()).toArray(new String[] {});
	}
	
	/**
	 * 
	 */
	public DummyModel[][] getCalendar() {
		final DummyModel[][] r = new DummyModel[6][7];
		Calendar c = new GregorianCalendar(Locale.ENGLISH);
		c.set(Calendar.DATE, 1);
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		int firstDayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		int prevDayOfMonth = firstDayOfWeek == 1 ? 0 : (firstDayOfWeek - 1);
		// first day
		if(prevDayOfMonth == 0) { c.set(Calendar.DATE, 1); } 
		else { c.add(Calendar.DATE, -prevDayOfMonth); }
		// iterator
		for (int i = 0; i < r.length; i++) {
			for (int j = 0; j < r[i].length; j++) {
				int day = c.get(Calendar.DATE);
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH);
				boolean editable = month == this.month ? true : false;
				r[i][j] = new DummyModel(year, month, day, editable); 
				c.add(Calendar.DATE, 1); // roll next day
			}
		}
		return r;
	}
	
	public static void main(String[] args) {
		new DummyCalendar(2020, 1, 1).getCalendar();
	}
}
