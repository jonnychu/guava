package cn.nextop.guava.support.swt;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author jonny
 */
public class SwtUtils {
	
	/**
	 * Composite
	 */
	public static Composite creator(final Shell shell) {
		Composite parent = new Composite(shell, SWT.NONE);
		parent.setLayout(new FillLayout()); return parent;
	}
	
	/*
	 * 
	 */
	public static final Display getDisplay() {
		final Display display = Display.getCurrent();
		return display != null ? display : Display.getDefault();
	}
	
	public static void sync(Display display, Runnable task) {
		if(display == null) display = getDisplay(); display.syncExec(task);
	}
	
	public static void async(Display display, Runnable task) {
		if(display == null) display = getDisplay(); display.asyncExec(task);
	}
	
	public static <T> T sync(Display display, Supplier<T> task) {
		final AtomicReference<T> result = new AtomicReference<>();
		sync(display, () -> { result.set(task.get()); }); return result.get();
	}
	
	public static <T> T async(Display display, Supplier<T> task) {
		final AtomicReference<T> result = new AtomicReference<>();
		async(display, () -> { result.set(task.get()); });  return result.get();
	}
	
	/**
	 * Shell
	 */
	public static Shell shell(Shell shell) {
		Shell parent = new Shell(shell, SWT.NO_TRIM | SWT.ON_TOP);
		parent.setLayout(new FillLayout()); return parent;
	}
	
	public static Shell creator(int w, int h, String title) {
		final Shell r = new Shell(); r.setSize(w,h);
		final Rectangle r1 = r.getMonitor().getBounds(), r2 = r.getBounds();
		r.setLocation(r1.x + (r1.width - r2.width) / 2, r1.y + (r1.height - r2.height) / 2);
		r.setText(title); r.setLayout(new FillLayout()); return r;
	}
	
	public static void center(final Shell shell) {
		final Rectangle r1 = shell.getMonitor().getBounds(), r2 = shell.getBounds();
		shell.setLocation(r1.x + (r1.width - r2.width) / 2, r1.y + (r1.height - r2.height) / 2);
	}
	
	/**
	 * Dispatch
	 */
	public static void dispatch(final Supplier<Boolean> condition) {
		final Display display = Display.getCurrent();
		while (!condition.get()) if(!display.readAndDispatch()) display.sleep(); 
	}
}
