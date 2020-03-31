package cn.nextop.guava.widgets.progress.circle1.render.panel;

import static cn.nextop.guava.utils.SwtUtils.getDisplay;
import static java.lang.String.valueOf;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Display;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.progress.circle.render.AbstractXCircleProgressPanel;
import cn.nextop.guava.widgets.progress.circle1.XInfiniteProgress;

/**
 * @author jonny
 */
public class XInfiniteProgressPanel extends AbstractXCircleProgressPanel {
	//
	private XInfiniteProgress progress;
	
	/**
	 * 
	 */
	public XInfiniteProgressPanel(String name, XInfiniteProgress progress) {
		super(name); this.progress = progress;
	}
	
	@Override
	protected void paintClientArea(Graphics g) {
		super.paintClientArea(g);
		final Rectangle r = getClientArea();
		final int x = r.x, y = r.y, w = r.width, h = r.height;
		g.setAdvanced(true); g.setAntialias(SWT.ON); // open dpi
		
		int r1 = w / 2, cx = x + w / 2, cy = y + h / 2;
		double x1 = cx + r1 * Math.cos(10 * Math.PI / 180); 
		double y1 = cy + r1 * Math.sin(10 * Math.PI / 180);
		g.drawOval((int)x1, (int)y1, 20, 20);
		
		//
		g.setAdvanced(false); g.setAntialias(SWT.OFF); // close dpi
	}
	
	@Override
	protected void layoutManager(IFigure container) {}
}
