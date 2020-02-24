package cn.nextop.guava.widgets.datetime.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class XDateTimeModel {
	//
	private long time;
	private SimpleDateFormat formater_1;
	
	/**
	 * 
	 */
	public XDateTimeModel() {
		formater_1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	}
	
	/**
	 * 
	 */
	public String getTime() {
		return formater_1.format(new Date(time));
	}

	public void setTime(long time) {
		this.time = time;
	}
}
