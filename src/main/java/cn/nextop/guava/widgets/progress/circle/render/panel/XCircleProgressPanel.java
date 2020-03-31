package cn.nextop.guava.widgets.progress.circle.render.panel;

import static cn.nextop.guava.utils.SwtUtils.getDisplay;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.widgets.Display;

import cn.nextop.guava.utils.CGUtils;
import cn.nextop.guava.utils.Colors;
import cn.nextop.guava.widgets.progress.circle.XCircleProgress;

public class XCircleProgressPanel extends AbstractXCircleProgressPanel {
	//
	private XCircleProgress progress;
	
	/**
	 * 
	 */
	public XCircleProgressPanel(String name, XCircleProgress progress) {
		super(name); this.progress = progress;
	}
	
	@Override
	protected void paintClientArea(Graphics g) {
		super.paintClientArea(g);
		final Rectangle r = getClientArea();
		final int x = r.x, y = r.y, w = r.width, h = r.height;
		g.setAdvanced(true); g.setAntialias(SWT.ON); // open dpi
		
		//
		final float value = progress.getInput();
		final float range = progress.getValueRange();
		final float a1 = (value / range) * 360f, a2 = 360f - a1;
		
		//
		Display dispay = getDisplay();
		final Path p1 = new Path(dispay);
		p1.addArc(x + margin, y + margin, w - margin * 2, h - margin * 2, angle1, -a1);
		p1.lineTo((w - margin * 2) / 2 , (h - margin * 2) / 2);
		g.setBackgroundColor(Colors.COLOR_CYAN);
		g.fillPath(p1); p1.close(); p1.dispose();
		
		final Path p2 = new Path(dispay);
		p2.addArc(x + margin, y + margin, w - margin * 2, h - margin * 2, angle1, a2);
		p2.lineTo((w - margin * 2) / 2 , (h - margin * 2) / 2); 
		g.setBackgroundColor(Colors.COLOR_GRAY);
		g.fillPath(p2); p2.close(); p2.dispose();
		
		g.setBackgroundColor(Colors.COLOR_WHITE);
		g.fillOval(margin + thickness, margin + thickness, w - margin * 2 - thickness * 2, h - margin * 2 - thickness * 2);
	}
	
	@Override
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
		g.setBackgroundColor(Colors.COLOR_BLACK);
		g.drawRectangle(CGUtils.getBorderRect(getBounds()));
	}
	
	@Override
	protected void layoutManager(IFigure container) {}
}
