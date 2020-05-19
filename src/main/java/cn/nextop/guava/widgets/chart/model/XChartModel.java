package cn.nextop.guava.widgets.chart.model;

/**
 * 
 * @author jonny
 */
public class XChartModel {
	//
	private XChartConfig config;
	
	/**
	 * 
	 */
	public XChartModel() {
		config = new XChartConfig();
	}
	
	/**
	 * 
	 */
	public XChartConfig getXCharConfig() {
		return config;
	}

	public void setXCharConfig(XChartConfig config) {
		this.config = config;
	}
}
