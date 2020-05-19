package cn.nextop.guava;

import static cn.nextop.guava.support.swt.SwtUtils.creator;
import static cn.nextop.guava.support.swt.SwtUtils.dispatch;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.support.swt.SwtUtils;
import cn.nextop.guava.widgets.chart.XChart;
import net.miginfocom.swt.MigLayout;

/**
 * @author jonny
 */
public class ChartExample {
	public static void main(String[] args) {
//		ChartExample exp = new ChartExample();
		final String name = "Chart Example";
		Shell shell = creator(500, 500, name);
		Composite cmp = SwtUtils.creator(shell);
		cmp.setLayout(new MigLayout("insets 0, gap 0 0","[fill,grow]","[fill,grow]"));
		XChart chart = new XChart(cmp); chart.setLayoutData("cell 0 0");
		System.out.println(shell.getDisplay().getDPI());
		//
		shell.open(); dispatch(() -> shell.isDisposed());
	}
}
