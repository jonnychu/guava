package cn.nextop.guava.widgets.slider.render.widget;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

import cn.nextop.guava.support.swt.Colors;
import cn.nextop.guava.widgets.slider.render.AbstractXSliderWidget;

/**
 * @author jonny
 */
public class Indicator extends AbstractXSliderWidget {
	//
	private boolean drag;
	
	/**
	 * 
	 */
	public Indicator(String name) {
		super(name, false, false);
	}

	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		final Rectangle r = getClientArea();
		int x = r.x, y = r.y, w = r.width, h = r.height;
		if(!enter) {
			g.setBackgroundColor(Colors.COLOR_WIDGET_THUMB);
		} else {
			if(drag) {
				g.setBackgroundColor(Colors.COLOR_WIDGET_PRESS);
			} else {
				g.setBackgroundColor(Colors.COLOR_WIDGET_THUMB_FOCUS);
			}
		}
		g.setAntialias(SWT.ON);
		PointList points = new PointList(3);
		points.addPoint(x + w / 2, y); points.addPoint(x, y + h);
		points.addPoint(x + w, y + h); g.fillPolygon(points);
	}
	
	@Override
	public void handleMouseDragged(MouseEvent event) {
		super.handleMouseDragged(event); drag = true; repaint();
	}
	
	@Override
	public void handleMouseEntered(MouseEvent event) {
		super.handleMouseEntered(event); enter = true; repaint();
	}
	
	@Override
	public void handleMouseExited(MouseEvent event) {
		super.handleMouseExited(event); enter = false; repaint();
	}
	
	@Override
	public void handleMouseReleased(MouseEvent event) {
		super.handleMouseReleased(event); drag = false; repaint(); 
	}
}