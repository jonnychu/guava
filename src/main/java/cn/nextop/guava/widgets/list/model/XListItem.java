package cn.nextop.guava.widgets.list.model;

/**
 * @author jonny
 */
public class XListItem {
	//
	private String value;
	private boolean select;
	
	/**
	 * 
	 */
	public XListItem(boolean select, String value) {
		this.value = value; this.select = select;
	}
	
	/**
	 * 
	 */
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean isSelect() {
		return select;
	}
	public void setSelect(boolean select) {
		this.select = select;
	}
}
