package cn.nextop.guava.widgets.table.builder;

/**
 * @author jonny
 */
public class XTableBuilder {
	
	public XTableColumnBuilder column() {
		return new XTableColumnBuilder(this);
	}
}
