package cn.nextop.guava.widgets.combo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonny
 */
public class ComboModel {
	//
	private List<ComboItem> input;
	
	/**
	 * 
	 */
	public ComboModel() {
		this.input = new ArrayList<ComboItem>();
	}
	
	public ComboItem getSelection() {
		for (ComboItem item : input) {
			if(item.isSelect()) return item;
		}
		return null;
	}
	
	public void setSelection(ComboItem item) {
		for (ComboItem i : input) {
			if(!item.getValue().equals(i.getValue())) 
				i.setSelect(false);
			else
				i.setSelect(true);
		}
	}
	
	/**
	 * 
	 */
	public List<ComboItem> getInput() {
		return this.input;
	}

	public void setInput(List<ComboItem> input) {
		this.input.clear(); this.input.addAll(input);
	}
}
