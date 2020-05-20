package cn.nextop.guava.widgets.chart.render.panel;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.chart.builder.XChartFactory;
import cn.nextop.guava.widgets.chart.render.AbstractXChartPanel;

/**
 * @author jonny
 */
public class TickPanel extends AbstractXChartPanel {
	//
	private AixsPanel aixs;
	private int pan = 3;
	
	/**
	 * 
	 */
	public TickPanel(String name, XChartFactory factory, AixsPanel aixs) {
		super(name, factory); this.aixs = aixs;
	}

	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		
	}
}