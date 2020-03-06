package cn.nextop.guava.widgets.datetime.model;

import static java.lang.String.valueOf;
import static java.lang.System.currentTimeMillis;
import static java.util.Arrays.stream;
import static java.util.Calendar.DATE;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.text.DateFormatSymbols;
import java.util.Arrays;
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
	private DateFormatSymbols symbols;
	private Calendar now, dummy, select;
	private Locale locale = Locale.ENGLISH;
	
	
	/**
	 * 
	 */
	public DummyCalendar(long date) {
		this.now = new GregorianCalendar(locale);
		this.dummy = new GregorianCalendar(locale);
		this.select = new GregorianCalendar(locale);
		this.symbols = new DateFormatSymbols(locale);
		//
		this.dummy.setTime(new Date(date));
		this.select.setTime(new Date(date));
		this.now.setTime(new Date(currentTimeMillis()));
	}
	
	/**
	 * 
	 */
	public int nextYear() {
		dummy.add(YEAR, 1);
		return dummy.get(YEAR);
	}
	
	public int prevYear() {
		dummy.add(YEAR, -1);
		return dummy.get(YEAR);
	}
	
	public int next12Year() {
		dummy.add(YEAR, 12);
		return dummy.get(YEAR);
	}
	
	public int prev12Year() {
		dummy.add(YEAR, -12);
		return dummy.get(YEAR);
	}
	
	public String nextMonth() {
		dummy.add(MONTH, 1);
		return getMonthSymbol();
	}

	public String prevMonth() {
		dummy.add(MONTH, -1);
		return getMonthSymbol();
	}
	
	/**
	 * 
	 */
	public String getYearSymbol() {
		return valueOf(dummy.get(YEAR));
	}
	
	/**
	 * 
	 */
	public String getMonthSymbol(int index) {
		return this.symbols.getShortMonths()[index];
	}
	
	public String getMonthSymbol() {
		int month = dummy.get(MONTH);
		return this.symbols.getShortMonths()[month];
	}
	
	public String[] getWeekSymbols() {
		return stream(this.symbols.getShortWeekdays())
				.filter((e) -> !e.equals(""))
				.collect(Collectors.toList()).toArray(new String[] {});
	}
	
	/**
	 * 
	 */
	public String[] getYears() {
		int nYear = now.get(Calendar.YEAR);
		int dYear = dummy.get(Calendar.YEAR);
		
		int v0 = dYear - nYear;
		int v1 = nYear + (v0 / 12) * 12;
		if(v0 < 0) v1 = v1 - 12; // prev
		
		final String[] r = new String[12];
		for (int i = 0; i < r.length; i++) {
			r[i] = valueOf(v1++);
		}
		return r;
	}
	
	public DummyModel[][] getCalendar() {
		final int dYear = this.dummy.get(YEAR);
		final int dMonth = this.dummy.get(MONTH);
		final DummyModel[][] r = new DummyModel[6][7];
		final Calendar c = new GregorianCalendar(locale);
		c.set(DATE, 1); c.set(MONTH, dMonth); c.set(YEAR, dYear);
		//
		final int firstDayOfWeek = c.get(DAY_OF_WEEK);
		int prevDayOfMonth = firstDayOfWeek == 1 ? 0 : (firstDayOfWeek - 1);
		// first day
		if(prevDayOfMonth == 0) c.set(DATE, 1); else c.add(DATE, -prevDayOfMonth);
		// iterator
		for (int i = 0; i < r.length; i++) {
			for (int j = 0; j < r[i].length; j++) {
				int day = c.get(DATE), month = c.get(MONTH), year = c.get(YEAR);
				r[i][j] = new DummyModel(year, month, day); c.add(DATE, 1); // roll next day
			}
		}
		return r;
	}
	
	/**
	 * 
	 */
	public int getDay() { return this.dummy.get(DATE); }
	
	public int getYear() { return this.dummy.get(YEAR); }
	
	public int getMonth() {	return this.dummy.get(MONTH); }
	
	/**
	 * 
	 */
	public void select(long now) {
		this.now.setTimeInMillis(now);
		this.dummy.setTimeInMillis(now);
		this.select.setTimeInMillis(now);
	}
	
	public void select(int year) {
		this.dummy.set(YEAR, year);
	}
	
	public void select(int year, int month) {
		this.dummy.set(YEAR, year); this.dummy.set(MONTH, month);
	}
	
	public void select(int year, int month, int day) {
		this.select.set(DATE, day);	this.select.set(YEAR, year);
		this.select.set(MONTH, month);this.dummy.set(DATE, day);
		this.dummy.set(YEAR, year);this.dummy.set(MONTH, month);
	}
	
	/**
	 * 
	 */
	public long getSelectedTime() {
		return select.getTimeInMillis();
	}
	
	public int getSelectedSecond() {
		return select.get(Calendar.SECOND);
	}
	
	public int getSelectedMintue() {
		return select.get(Calendar.MINUTE);
	}
	
	public int getSelectedHour() {
		return select.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 
	 */
	public boolean isCurMonth(int year, int month) {
		final int y = this.dummy.get(YEAR);
		final int m = this.dummy.get(MONTH);
		return (y == year && m == month);
	}
	
	public boolean isNow(int year, int month, int day) {
		final int d = this.now.get(DATE);
		final int y = this.now.get(YEAR);
		final int m = this.now.get(MONTH);
		return (y == year && m == month && d == day);
	}
	
	/**
	 * 
	 */
	public boolean isSelectedYear(int year) {
		return (this.select.get(YEAR) == year);
	}
	
	public boolean isSelectedMonth(int year, int month) {
		final int y = this.select.get(YEAR);
		final int m = this.select.get(MONTH);
		return (y == year && m == month);
	}
	
	public boolean isSelectedDate(int year, int month, int day) {
		final int d = this.select.get(DATE);
		final int y = this.select.get(YEAR);
		final int m = this.select.get(MONTH);
		return (y == year && m == month && d == day);
	}
	
	/**
	 * 
	 */
	public static void main(String[] args) {
		{
			Calendar c = new GregorianCalendar(Locale.ENGLISH);
			c.set(Calendar.DATE, 1); c.set(Calendar.MONTH, 1); c.set(Calendar.YEAR, 2023);
			DummyCalendar dc = new DummyCalendar(c.getTimeInMillis());
			Arrays.stream(dc.getYears()).forEach(e->{System.out.print(e + " ,");});
			System.out.println();
		}
		
		{
			Calendar c = new GregorianCalendar(Locale.ENGLISH);
			c.set(Calendar.DATE, 1); c.set(Calendar.MONTH, 1); c.set(Calendar.YEAR, 2033);
			DummyCalendar dc = new DummyCalendar(c.getTimeInMillis());
			Arrays.stream(dc.getYears()).forEach(e->{System.out.print(e + " ,");});
			System.out.println();
		}
		
		{
			Calendar c = new GregorianCalendar(Locale.ENGLISH);
			c.set(Calendar.DATE, 1); c.set(Calendar.MONTH, 1); c.set(Calendar.YEAR, 2045);
			DummyCalendar dc = new DummyCalendar(c.getTimeInMillis());
			Arrays.stream(dc.getYears()).forEach(e->{System.out.print(e + " ,");});
			System.out.println();
		}
		
		{
			Calendar c = new GregorianCalendar(Locale.ENGLISH);
			c.set(Calendar.DATE, 1); c.set(Calendar.MONTH, 1); c.set(Calendar.YEAR, 2007);
			DummyCalendar dc = new DummyCalendar(c.getTimeInMillis());
			Arrays.stream(dc.getYears()).forEach(e->{System.out.print(e + " ,");});
			System.out.println();
		}
	}
}
