package cn.nextop.guava.widgets.datetime.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class XDateTimeModel {
	//
	private long time;
	private SimpleDateFormat formater;
	
	/**
	 * 
	 */
	public XDateTimeModel() {
		formater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	}
	
	/**
	 * 
	 */
	public long getTime() {
		return this.time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public String getTimeByPattern() {
		return formater.format(new Date(time));
	}
}
