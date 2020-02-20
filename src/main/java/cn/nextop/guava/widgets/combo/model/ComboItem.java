package cn.nextop.guava.widgets.combo.model;

public class ComboItem {
	private String value;
	private boolean select;
	
	public ComboItem(boolean select, String value) {
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
	
	@Override
	public String toString() {
		return "[ select:"+select+", value:"+value+"]";
	}
}
