package cn.nextop.guava.widgets.table.model.config;

import org.eclipse.swt.graphics.Color;

import cn.nextop.guava.support.swt.Colors;

/**
 * @author jonny
 */
public class XTableConfig {
	//
	private Color FG_GRAY = Colors.COLOR_GRAY;
	private Color BG_WHITE = Colors.COLOR_WHITE;
	private Color BG_GRAY_L = Colors.COLOR_LIGHT_GRAY;
	
	//
	private int itemHeight = 24, headerHeight = 26;
	private int stepIncrement = itemHeight, pageIncrement = itemHeight * 12;
	
	/**
	 * 
	 */
	public XTableConfig() {
	}
	
	//
	public int getItemHeight() {
		return itemHeight;
	}

	public void setItemHeight(int itemHeight) {
		this.itemHeight = itemHeight;
	}

	public int getHeaderHeight() {
		return headerHeight;
	}

	public void setHeaderHeight(int headerHeight) {
		this.headerHeight = headerHeight;
	}

	public int getStepIncrement() {
		return stepIncrement;
	}

	public void setStepIncrement(int stepIncrement) {
		this.stepIncrement = stepIncrement;
	}

	public int getPageIncrement() {
		return pageIncrement;
	}

	public void setPageIncrement(int pageIncrement) {
		this.pageIncrement = pageIncrement;
	}

	public Color getBG_WHITE() {
		return BG_WHITE;
	}

	public void setBG_WHITE(Color bG_WHITE) {
		BG_WHITE = bG_WHITE;
	}

	public Color getBG_GRAY_L() {
		return BG_GRAY_L;
	}

	public void setBG_GRAY_L(Color bG_GRAY_L) {
		BG_GRAY_L = bG_GRAY_L;
	}

	public Color getFG_GRAY() {
		return FG_GRAY;
	}

	public void setFG_GRAY(Color fG_GRAY) {
		FG_GRAY = fG_GRAY;
	}
}
