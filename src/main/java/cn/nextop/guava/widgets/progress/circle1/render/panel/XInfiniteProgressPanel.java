package cn.nextop.guava.widgets.progress.circle1.render.panel;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.progress.circle1.XInfiniteProgress;
import cn.nextop.guava.widgets.progress.circle1.model.XInfiniteProgressModel;
import cn.nextop.guava.widgets.progress.circle1.render.AbstractXInfiniteProgressPanel;

/**
 * @author jonny
 */
public class XInfiniteProgressPanel extends AbstractXInfiniteProgressPanel {
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
		g.setBackgroundColor(Colors.COLOR_PROGRESS);
		XInfiniteProgressModel model = progress.getModel();
		double angle = model.getAngle(), p1 = Math.PI / 180;
		int r1 = w / 2 - radius - margin, cx = x + w / 2, cy = y + h / 2;
		//
		int index = 0; for (int i = 0; i < 8; i++) {
			angle = angle + index; index = index + 45;
			final double x1 = cx + r1 * Math.cos(angle * p1) - radius;
			final double y1 = cy + r1 * Math.sin(angle * p1) - radius;
			g.fillOval((int)x1, (int)y1, radius * 2, radius * 2);
		}
		//
		g.setAdvanced(false); g.setAntialias(SWT.OFF); // close dpi
	}
	
	@Override
	protected void layoutManager(IFigure container) {}
}
