package cn.nextop.guava;

import static cn.nextop.guava.utils.SwtUtils.creator;
import static cn.nextop.guava.utils.SwtUtils.dispatch;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.utils.SwtUtils;
import cn.nextop.guava.widgets.datetime.XDateTime;
import net.miginfocom.swt.MigLayout;


public class Example {
	public static void main(String[] args) {
		final String name = "Widget Example";
		Shell shell = creator(500, 400, name);
		Composite cmp = SwtUtils.creator(shell);
		cmp.setLayout(new MigLayout("insets 5, gap 0 0","[fill,grow]","[fill,grow][fill,grow][fill,grow]"));
		XDateTime date = new XDateTime(cmp); date.setLayoutData("cell 0 0, width 10:150:,height 23!");
		
		// date time
		date.setInput(System.currentTimeMillis());
		
		
		shell.open(); dispatch(() -> shell.isDisposed());
	}
}
