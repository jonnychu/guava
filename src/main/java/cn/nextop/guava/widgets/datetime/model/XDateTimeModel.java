package cn.nextop.guava.widgets.datetime.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.nextop.guava.widgets.datetime.layout.ShellLayout;

public class XDateTimeModel {
	//
	private ShellLayout layout;
	//
	private long time;
	private SimpleDateFormat formater_1;
	
	/**
	 * 
	 */
	public XDateTimeModel() {
		layout = new ShellLayout();
		formater_1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	}
	
	/**
	 * 
	 */
	public ShellLayout getLayout() {
		return layout;
	}

	public void setLayout(ShellLayout layout) {
		this.layout = layout;
	}
	
	public String getTime() {
		return formater_1.format(new Date(time));
	}

	public void setTime(long time) {
		this.time = time;
	}
}
