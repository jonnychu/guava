package cn.nextop.guava.widgets.datetime.render;

import cn.nextop.guava.widgets.AbstractPanel;
import cn.nextop.guava.widgets.datetime.support.builder.XDateTimePopupBuilder;

/**
 * @author jonny
 */
public abstract class AbstractTimePanel extends AbstractPanel {
	//
	protected XDateTimePopupBuilder builder;
	protected final int margin = 8, arc = 5;
	
	/**
	 * 
	 */
	public AbstractTimePanel(String name) {
		super(name);
	}

	public XDateTimePopupBuilder getFactory() {
		return builder;
	}
}
