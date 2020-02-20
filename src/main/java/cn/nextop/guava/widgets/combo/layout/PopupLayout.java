package cn.nextop.guava.widgets.combo.layout;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.widgets.combo.Combo;
import cn.nextop.guava.widgets.combo.ComboPopup;
import cn.nextop.guava.widgets.combo.model.ComboModel;

public class PopupLayout {
	
	private final int maxH = 200;
	
	/**
	 * 
	 */
	public void layout(Combo combo) {
		ComboPopup popup = combo.getPopup();
		ComboModel model = combo.getModel();
		final Shell shell = popup.getShell();
		final Display display = shell.getDisplay();
		final Composite parent = combo.getParent();
		final Rectangle bounds = combo.getBounds();
		final Rectangle r1 = display.map(parent, null, bounds);
		final Rectangle r2 = shell.getMonitor().getClientArea();
		// bound
		int totalH = model.getInput().size(); if(totalH == 0) totalH = maxH;
		shell.setSize(bounds.width, 100);
		// location
		final int margin = 2;
		final Point size = shell.getSize();
		int x = r1.x, y = r1.y + r1.height + margin;
		if(y + size.y > r2.y + r2.height) y = r1.y - size.y - margin;
		if(x < r2.x) x = r2.x; else if(x + size.x > r2.x + r2.width) x = r2.x + r2.width - size.x;
		shell.setLocation(x, y);
	}
}
