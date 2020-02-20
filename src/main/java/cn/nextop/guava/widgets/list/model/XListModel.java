package cn.nextop.guava.widgets.list.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonny
 */
public class XListModel {
	//
	private List<XListItem> input;
	
	/**
	 * 
	 */
	public XListModel() {
		this.input = new ArrayList<XListItem>();
	}
	
	/**
	 * 
	 */
	public List<XListItem> getInput() {
		return this.input;
	}

	public void setInput(List<XListItem> input) {
		this.input.clear(); this.input.addAll(input);
	}
}
