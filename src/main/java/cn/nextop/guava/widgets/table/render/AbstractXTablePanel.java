package cn.nextop.guava.widgets.table.render;

import cn.nextop.guava.widgets.AbstractPanel;
import cn.nextop.guava.widgets.datetime.builder.XDateTimePopupBuilder;

/**
 * @author jonny
 */
public abstract class AbstractXTablePanel extends AbstractPanel {
	//
	protected XDateTimePopupBuilder builder;
	protected final int margin = 8, arc = 5;
	
	/**
	 * 
	 */
	public AbstractXTablePanel(String name) {
		super(name);
	}

	public XDateTimePopupBuilder getBuilder() {
		return builder;
	}
}
