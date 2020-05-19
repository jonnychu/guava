package cn.nextop.guava.widgets.chart.render;

import java.util.List;

import cn.nextop.guava.widgets.AbstractPanel;
import cn.nextop.guava.widgets.chart.builder.XChartFactory;

/**
 * @author jonny
 */
public abstract class AbstractXChartPanel extends AbstractPanel {
	//
	protected XChartFactory factory;
	protected final int margin1 = 6, margin2 = 4;
	
	/**
	 * 
	 */
	public AbstractXChartPanel(String name) {
		super(name);
	}
	
	public AbstractXChartPanel(String name, XChartFactory factory) {
		super(name); this.factory = factory;
	}

	@Override
	public List<?> getChildren() {
		return super.getChildren();
	}

	public XChartFactory getFactory() {
		return factory;
	}
}
