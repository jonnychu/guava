package cn.nextop.guava.widgets.chart.builder;

import org.eclipse.swt.widgets.Canvas;

import cn.nextop.guava.support.Objects;
import cn.nextop.guava.widgets.AbstractBuilder;
import cn.nextop.guava.widgets.chart.XChart;
import cn.nextop.guava.widgets.chart.model.XChartConfig;
import cn.nextop.guava.widgets.chart.render.panel.AixsPanel;
import cn.nextop.guava.widgets.chart.render.panel.AixsPanel.Orientation;
import cn.nextop.guava.widgets.chart.render.panel.GridPanel;
import cn.nextop.guava.widgets.chart.render.panel.PlotPanel;
import cn.nextop.guava.widgets.chart.render.panel.XChartPanel;

/**
 * 
 * @author jonny
 */
public class XChartFactory extends AbstractBuilder {
	//
	private XChart chart;
	private PlotPanel plot;
	private XChartPanel chartPanel;
	private AixsPanel xAixs, yAixs;
	private GridPanel xGrid, yGrid;
	
	@Override
	public XChartPanel build(Canvas parent) {
		this.chart = Objects.cast(parent);
		this.chartPanel = new XChartPanel("chart.panel", this);
		this.chartPanel.add(this.plot = new PlotPanel("plot", this));
		this.chartPanel.add(this.xGrid = new GridPanel("grid.x", this));
		this.chartPanel.add(this.yGrid = new GridPanel("grid.y", this));
		this.chartPanel.add(this.yAixs = new AixsPanel("aixs.y", this, Orientation.VERTICAL));
		this.chartPanel.add(this.xAixs = new AixsPanel("aixs.x", this, Orientation.HORIZONTAL));
		return this.chartPanel;
	}
	
	/**
	 * 
	 */
	public XChart getChart() {
		return chart;
	}

	public AixsPanel getXAixs() {
		return xAixs;
	}

	public AixsPanel getYAixs() {
		return yAixs;
	}

	public GridPanel getXGrid() {
		return xGrid;
	}

	public GridPanel getYGrid() {
		return yGrid;
	}

	public PlotPanel getPlot() {
		return plot;
	}
	
	public XChartPanel getChartPanel() {
		return chartPanel;
	}
	
	public XChartConfig getXChartConfig() {
		return this.chart.getModel().getXCharConfig();
	}
}
