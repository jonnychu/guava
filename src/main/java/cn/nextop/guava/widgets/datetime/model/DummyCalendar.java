package cn.nextop.guava.widgets.datetime.model;

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
	private Calendar calendar;
	private int year, month, day;
	private DateFormatSymbols symbols;
	
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
		this.symbols = new DateFormatSymbols(Locale.ENGLISH);
		this.calendar = new GregorianCalendar(Locale.ENGLISH);
		calendar.setTime(new Date(date));
		this.day = calendar.get(Calendar.DATE);
		this.year = calendar.get(Calendar.YEAR); 
		this.month = calendar.get(Calendar.MONTH); 
	}
	
	public DummyCalendar(int year, int month, int day) {
		this.year = year; this.month = month; this.day = day;
	}
	
	/**
	 * 
	 */
	public int nextYear() {
		calendar.add(Calendar.YEAR, 1);
		return this.year = calendar.get(Calendar.YEAR);
	}

	public int prevYear() {
		calendar.add(Calendar.YEAR, -1);
		return this.year = calendar.get(Calendar.YEAR);
	}

	public String nextMonth() {
		calendar.add(Calendar.MONTH, 1);
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH);
		return getMonthSymbol();
	}

	public String prevMonth() {
		calendar.add(Calendar.MONTH, -1);
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH);
		return getMonthSymbol();
	}
	
	/**
	 * 
	 */
	public String[] getYearSymbols() {
		int y = this.year; 
		final String[] r = new String[12];
		for (int i = 0; i < r.length; i++, y++) {
			r[i] = String.valueOf(y);
		}
		return r;
	}
	/**
	 * 
	 */
	public String getMonthSymbol(int index) {
		return this.symbols.getShortMonths()[index];
	}
	
	public String getMonthSymbol() {
		return this.symbols.getShortMonths()[this.month];
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
		DummyModel[][] model = new DummyCalendar(2020, 0, 1).getCalendar();
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(model[i][j].getDay()); System.out.print("\t");
			}
			System.out.println();
		}
	}
}
