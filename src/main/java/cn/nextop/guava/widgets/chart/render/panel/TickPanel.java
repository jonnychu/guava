package cn.nextop.guava.widgets.chart.render.panel;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.chart.builder.XChartFactory;
import cn.nextop.guava.widgets.chart.render.AbstractXChartPanel;

/**
 * @author jonny
 */
public class TickPanel extends AbstractXChartPanel {
	//
	private AixsPanel aixs;
	
	/**
	 * 
	 */
	public TickPanel(String name, XChartFactory factory, AixsPanel aixs) {
		super(name, factory); this.aixs = aixs;
	}

	@Override
	protected void layoutManager(IFigure container) {
		
	}
}