package cn.nextop.guava.widgets.combo.render;

import cn.nextop.guava.widgets.AbstractPanel;
import cn.nextop.guava.widgets.datetime.support.builder.XDateTimePopupBuilder;

/**
 * @author jonny
 */
public abstract class AbstractComboPanel extends AbstractPanel {
	//
	protected XDateTimePopupBuilder builder;
	protected final int margin = 8, arc = 5;
	
	/**
	 * 
	 */
	public AbstractComboPanel(String name) {
		super(name);
	}

	public XDateTimePopupBuilder getBuilder() {
		return builder;
	}
}
