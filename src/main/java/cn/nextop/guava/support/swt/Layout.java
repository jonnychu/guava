package cn.nextop.guava.support.swt;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author jonny
 */
public class Layout {
	
	/**
	 * 
	 */
	public static void layout(Composite control, Shell shell, int w, int h) {
		// size
		shell.setSize(w, h);
		final Display display = shell.getDisplay();
		final Composite parent = control.getParent();
		final Rectangle bounds1 = control.getBounds();
		final Rectangle bounds2 = parent.getBounds();
		final Rectangle r1 = display.map(parent, null, bounds2);
		final Rectangle r2 = shell.getMonitor().getClientArea();
		// location
		final int margin = 2; final Point size = shell.getSize();
		int x = r1.x + bounds1.x, y = r1.y + bounds1.y + bounds1.height + margin;
		if(y + size.y > r2.y + r2.height) y = r1.y + bounds1.y - size.y - margin;
		if(x < r2.x) x = r2.x; else if(x + size.x > r2.x + r2.width) x = r2.x + r2.width - size.x;
		shell.setLocation(x, y);
	}
}
