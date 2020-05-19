package cn.nextop.guava.widgets.chart.render.panel;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.support.swt.CGUtils;
import cn.nextop.guava.support.swt.Colors;
import cn.nextop.guava.widgets.chart.builder.XChartFactory;
import cn.nextop.guava.widgets.chart.render.AbstractXChartPanel;

/**
 * @author jonny
 */
public class PlotPanel extends AbstractXChartPanel {

	public PlotPanel(String name, XChartFactory factory) {
		super(name, factory);
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		CGUtils.fillRect(g, getBounds(), Colors.COLOR_WHITE);
		CGUtils.drawRect(g, getBounds(), Colors.COLOR_BLACK);
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		
	}
}
