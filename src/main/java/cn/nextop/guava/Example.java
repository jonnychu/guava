package cn.nextop.guava;

import static cn.nextop.guava.utils.SwtUtils.creator;
import static cn.nextop.guava.utils.SwtUtils.dispatch;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.utils.SwtUtils;
import cn.nextop.guava.widgets.combo.Combo;
import cn.nextop.guava.widgets.combo.model.ComboItem;
import net.miginfocom.swt.MigLayout;


public class Example {
	public static void main(String[] args) {
		final String name = "Widget Example";
		Shell shell = creator(200, 400, name);
		Composite cmp = SwtUtils.creator(shell);
		cmp.setLayout(new MigLayout("insets 5, gap 0 0","[fill,grow]","[fill,grow]"));
		Combo combo = new Combo(cmp, SWT.NONE); combo.setLayoutData("cell 0 0, width 10:150:,height 23!");
		
		List<ComboItem> r = new ArrayList<>();
		r.add(new ComboItem(true, "hello1"));
		r.add(new ComboItem(false,"hello2"));
		r.add(new ComboItem(false,"hello3"));
		r.add(new ComboItem(false,"hello4"));
		r.add(new ComboItem(false,"hello5"));
		r.add(new ComboItem(false,"hello6"));
		r.add(new ComboItem(false,"hello7"));
		combo.setInput(r);
		
		shell.open(); dispatch(() -> shell.isDisposed());
	}
}
