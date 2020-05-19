package cn.nextop.guava.widgets.chart.render.panel;

import static cn.nextop.guava.support.Objects.cast;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.chart.builder.XChartFactory;
import cn.nextop.guava.widgets.chart.model.XChartConfig;
import cn.nextop.guava.widgets.chart.render.AbstractXChartPanel;

/**
 * 
 * @author jonny
 */
public class XChartPanel extends AbstractXChartPanel {
	
	public XChartPanel(String name, XChartFactory factory) {
		super(name, factory);
	}
	
	@Override
	public boolean isOpaque() { return true; }
	
	@Override
	protected void layoutManager(IFigure container) {
		final XChartPanel p = cast(container);
		XChartFactory factory = p.getFactory();
		final PlotPanel plot = factory.getPlot();
		final GridPanel xGrid = factory.getXGrid();
		final GridPanel yGrid = factory.getYGrid();
		final AixsPanel xAixs = factory.getXAixs();
		final AixsPanel yAixs = factory.getYAixs();
		XChartConfig config = factory.getXChartConfig();
		
		//
		final Rectangle r = getClientArea();
		final int axisWH = config.getAxisWH();
		int x = r.x, y = r.y, w = r.width, h = r.height;
		Rectangle r1 = new Rectangle(x + margin1, y + margin2, w - axisWH - 2 * margin1, h - axisWH - 2 * margin2);
		plot.setBounds(r1); xGrid.setBounds(r1); yGrid.setBounds(r1);
		Rectangle r2 = new Rectangle(r1.x + r1.width, r1.y - margin2, axisWH, r1.height + 2 * margin2); yAixs.setBounds(r2);
		Rectangle r3 = new Rectangle(r1.x - margin1, r1.y + r1.height, r1.width + 2 * margin1, axisWH); xAixs.setBounds(r3);
	}
}
