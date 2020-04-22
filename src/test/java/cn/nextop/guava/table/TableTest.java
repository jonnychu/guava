package cn.nextop.guava.table;

import static cn.nextop.guava.support.swt.SwtUtils.creator;
import static cn.nextop.guava.support.swt.SwtUtils.dispatch;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.support.swt.SwtUtils;

public class TableTest {
	public static void main(String[] args) {
		final String name = "Widget Example";
		Shell shell = creator(500, 400, name);
		Composite cmp = SwtUtils.creator(shell);
		cmp.setLayout(new FillLayout());
		Canvas canvas = new Canvas(cmp, SWT.DOUBLE_BUFFERED); //canvas.setLayoutData("cell 0 0, width 10:150:,height 10:200");
		LightweightSystem lws = new LightweightSystem(canvas);
		TestPanel panel = new TestPanel("test");
		DataContent content = new DataContent("a");
		panel.setDataContents(content);
		lws.setContents(panel);
		
		shell.open(); dispatch(() -> shell.isDisposed());
	}
}
