package cn.nextop.guava.widgets.table.render;

import java.util.List;

import cn.nextop.guava.widgets.AbstractPanel;
import cn.nextop.guava.widgets.table.builder.internal.XTableFactory;

/**
 * @author jonny
 */
public abstract class AbstractXTablePanel extends AbstractPanel {
	//
	protected XTableFactory factory;
	protected final int margin = 8, arc = 5;
	
	/**
	 * 
	 */
	public AbstractXTablePanel(String name) {
		super(name);
	}

	public AbstractXTablePanel(String name, XTableFactory factory) {
		super(name); this.factory = factory;
	}
	
	public XTableFactory getFactory() {
		return factory;
	}

	public void setFactory(XTableFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public List<?> getChildren() {
		return super.getChildren();
	}
}
