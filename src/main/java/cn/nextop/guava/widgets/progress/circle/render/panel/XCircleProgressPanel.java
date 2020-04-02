package cn.nextop.guava.widgets.progress.circle.render.panel;

import static cn.nextop.guava.utils.SwtUtils.getDisplay;
import static cn.nextop.guava.widgets.table.support.util.Objects.cast;
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
import cn.nextop.guava.widgets.progress.circle.XCircleProgress;
import cn.nextop.guava.widgets.progress.circle.render.AbstractXCircleProgressPanel;

/**
 * @author jonny
 */
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
		g.setBackgroundColor(Colors.COLOR_PROGRESS);
		g.fillPath(p1); p1.close(); p1.dispose();
		
		final Path p2 = new Path(dispay);
		p2.addArc(x + margin, y + margin, w - margin * 2, h - margin * 2, angle1, a2);
		p2.lineTo((w - margin * 2) / 2 , (h - margin * 2) / 2); 
		g.setBackgroundColor(Colors.COLOR_GRAY);
		g.fillPath(p2); p2.close(); p2.dispose();
		
		g.setBackgroundColor(Colors.COLOR_WHITE);
		g.fillOval(margin + thickness, margin + thickness, w - margin * 2 - thickness * 2, h - margin * 2 - thickness * 2);
		
		String text = valueOf((int)((value / range) * 100)) + "%";
		Dimension d1 = INSTANCE.getTextExtents(text, g.getFont());
		g.drawText(text, (w - d1.width) / 2 + 2, (h - d1.height) / 2);
		
		//
		g.setAdvanced(false); g.setAntialias(SWT.OFF); // close dpi
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		XCircleProgressPanel panel = cast(container);
		final Rectangle r = getClientArea();
		int w = r.width, h = r.height, r1 = w < h ? w : h;
		if(w < h) panel.setBounds(new Rectangle(r.x, r.y, r1, r1));
		else panel.setBounds(new Rectangle(r.x, r.y, r1, r1));
	}
}
