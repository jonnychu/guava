package cn.nextop.guava.widgets.chart.provider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonny
 */
public class SimpleDataProvider {
	//
	private List<ISample> data;
	
	public SimpleDataProvider() {
		this.data = new ArrayList<ISample>(1024);
	}
}
