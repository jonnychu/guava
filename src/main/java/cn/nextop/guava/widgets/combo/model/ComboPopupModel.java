package cn.nextop.guava.widgets.combo.model;

import cn.nextop.guava.widgets.combo.layout.PopupLayout;

public class ComboPopupModel {
	//
	private PopupLayout layout;
	
	/**
	 * 
	 */
	public ComboPopupModel() {
		this.layout = new PopupLayout();
	}
	
	/**
	 * 
	 */
	public PopupLayout getLayout() {
		return layout;
	}

	public void setLayout(PopupLayout layout) {
		this.layout = layout;
	}
}
