package cn.nextop.guava.widgets.chart;

import static org.eclipse.swt.SWT.DOUBLE_BUFFERED;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.widgets.chart.builder.XChartFactory;
import cn.nextop.guava.widgets.chart.model.XChartModel;
import cn.nextop.guava.widgets.chart.render.panel.XChartPanel;

/**
 * 
 * @author jonny
 */
public class XChart extends Canvas {
	//
	private XChartModel model;
	private XChartPanel panel;
	private XChartFactory factory;
	private LightweightSystem lws;
	
	/**
	 * 
	 */
	public XChart(Composite parent) {
		super(parent, DOUBLE_BUFFERED);
		this.model = new XChartModel();
		this.factory = new XChartFactory();
		this.lws = new LightweightSystem(this);
		this.lws.setContents(panel = factory.build(this));
	}
	
	public void input() {
		
	}

	public XChartModel getModel() {
		return model;
	}
}
