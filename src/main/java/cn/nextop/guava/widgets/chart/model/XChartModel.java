package cn.nextop.guava.widgets.chart.model;

import java.util.ArrayList;
import java.util.List;

import cn.nextop.guava.widgets.chart.provider.ISample;

/**
 * 
 * @author jonny
 */
public class XChartModel {
	//
	private List<ISample> data;
	private XChartConfig config;
	
	/**
	 * 
	 */
	public XChartModel() {
		data = new ArrayList<>();
		config = new XChartConfig();
	}
	
	/**
	 * 
	 */
	public List<ISample> getData() {
		return data;
	}

	public void setData(List<ISample> data) {
		this.data = data;
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
