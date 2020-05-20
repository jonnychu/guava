package cn.nextop.guava;

import static cn.nextop.guava.support.swt.SwtUtils.creator;
import static cn.nextop.guava.support.swt.SwtUtils.dispatch;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.support.swt.SwtUtils;
import cn.nextop.guava.widgets.chart.XChart;
import cn.nextop.guava.widgets.chart.provider.ISample;
import cn.nextop.guava.widgets.chart.provider.Sample;
import net.miginfocom.swt.MigLayout;

/**
 * @author jonny
 */
public class ChartExample {
	public static void main(String[] args) {
		ChartExample exp = new ChartExample();
		final String name = "Chart Example";
		Shell shell = creator(500, 500, name);
		Composite cmp = SwtUtils.creator(shell);
		cmp.setLayout(new MigLayout("insets 0, gap 0 0","[fill,grow]","[fill,grow]"));
		XChart chart = new XChart(cmp); chart.setLayoutData("cell 0 0");
		
		// data
		List<ISample> r = new ArrayList<>();
		long start = exp.getTime(2020, 5, 20, 0, 0, 0, 0), om = 60 * 1000;
		for (int i = 0; i < 60 * 10; i++) {
			r.add(new Sample(start = start + om, exp.getValue(107100, 107300)));
		}
		
		chart.input(r);
		//
		shell.open(); dispatch(() -> shell.isDisposed());
	}
	
	private long getTime(int year, int month, int day, int hour, int minute, int second, int ms) {
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, day);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, ms);
		return calendar.getTimeInMillis();
	}
	
	private double getValue(int lower, int upper) {
		Random r = new Random();
		int v = r.nextInt(upper - lower + 1) + lower;
		return (double)v / 1000;
	}
}
