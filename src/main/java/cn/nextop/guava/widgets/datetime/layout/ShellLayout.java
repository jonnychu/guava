package cn.nextop.guava.widgets.datetime.layout;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.widgets.datetime.XDateTime;
import cn.nextop.guava.widgets.datetime.XDateTimePopup;

/**
 * @author jonny
 */
public class ShellLayout {
	
	/**
	 * 
	 */
	public void layout(XDateTime time) {
		XDateTimePopup popup = time.getPopup();
		final Shell shell = popup.getShell();
		final Display display = shell.getDisplay();
		final Composite parent = time.getParent();
		final Rectangle bounds = time.getBounds();
		final Rectangle r1 = display.map(parent, null, bounds);
		final Rectangle r2 = shell.getMonitor().getClientArea();
		// bound
		shell.setSize(300, 350);
		// location
		final int margin = 2;
		final Point size = shell.getSize();
		int x = r1.x, y = r1.y + r1.height + margin;
		if(y + size.y > r2.y + r2.height) y = r1.y - size.y - margin;
		if(x < r2.x) x = r2.x; else if(x + size.x > r2.x + r2.width) x = r2.x + r2.width - size.x;
		shell.setLocation(x, y);
	}
}
