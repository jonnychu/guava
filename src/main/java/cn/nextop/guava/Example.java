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
import cn.nextop.guava.widgets.datetime.XDateTime;
import cn.nextop.guava.widgets.list.XList;
import cn.nextop.guava.widgets.list.model.XListItem;
import net.miginfocom.swt.MigLayout;


public class Example {
	public static void main(String[] args) {
		final String name = "Widget Example";
		Shell shell = creator(500, 400, name);
		Composite cmp = SwtUtils.creator(shell);
		cmp.setLayout(new MigLayout("insets 5, gap 0 0","[fill,grow]","[fill,grow][fill,grow][fill,grow]"));
		Combo combo = new Combo(cmp, SWT.NONE); combo.setLayoutData("cell 0 0, width 10:150:,height 23!");
		XList list = new XList(cmp, SWT.NONE); list.setLayoutData("cell 0 1, width 10:150:,height 100!");
		XDateTime date = new XDateTime(cmp); date.setLayoutData("cell 0 2, width 10:150:,height 23!");
		
		// combo
		List<ComboItem> r1 = new ArrayList<>();
		r1.add(new ComboItem(true, "hello1"));
		r1.add(new ComboItem(false,"hello2"));
		r1.add(new ComboItem(false,"hello3"));
		r1.add(new ComboItem(false,"hello4"));
		r1.add(new ComboItem(false,"hello5"));
		r1.add(new ComboItem(false,"hello6"));
		r1.add(new ComboItem(false,"hello7"));
		combo.setInput(r1);
		
		// list
		List<XListItem> r2 = new ArrayList<>();
		r2.add(new XListItem(true, "hello1"));
		r2.add(new XListItem(false,"hello2"));
		r2.add(new XListItem(false,"hello3"));
		r2.add(new XListItem(false,"hello4"));
		r2.add(new XListItem(false,"hello5"));
		r2.add(new XListItem(false,"hello6"));
		r2.add(new XListItem(false,"hello7"));
		list.setInput(r2);
		
		// date time
		date.setInput(System.currentTimeMillis());
		
		
		shell.open(); dispatch(() -> shell.isDisposed());
	}
}
