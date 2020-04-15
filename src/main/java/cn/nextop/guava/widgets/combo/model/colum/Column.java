package cn.nextop.guava.widgets.combo.model.colum;

import org.eclipse.swt.SWT;

import cn.nextop.guava.widgets.combo.render.popup.widget.DefaultCellWidget;
import cn.nextop.guava.widgets.combo.render.popup.widget.DefaultColumnWidget;
import cn.nextop.guava.widgets.combo.support.property.Property;

public class Column<T> {
	//
	private int weight = 1;
	private int height = 24;
	private String text = "";
	private int colAlign = SWT.CENTER;
	private int cellAlign = SWT.CENTER;
	//
	private Property<T> property;
	private Class<?> cellWidget;
	private Class<?> columnwidget;
	
	/**
	 * 
	 */
	public Column() {
		this.cellWidget = DefaultCellWidget.class;
		this.columnwidget = DefaultColumnWidget.class;
	}
	
	/**
	 * 
	 */
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getColAlign() {
		return colAlign;
	}

	public void setColAlign(int colAlign) {
		this.colAlign = colAlign;
	}

	public int getCellAlign() {
		return cellAlign;
	}

	public void setCellAlign(int cellalign) {
		this.cellAlign = cellalign;
	}
	
	public Property<T> getProperty() {
		return property;
	}

	public void setProperty(Property<T> property) {
		this.property = property;
	}
	
	public Class<?> getCellWidget() {
		return cellWidget;
	}

	public void setCellWidget(Class<?> cellWidget) {
		this.cellWidget = cellWidget;
	}

	public Class<?> getColumnwidget() {
		return columnwidget;
	}

	public void setColumnwidget(Class<?> columnwidget) {
		this.columnwidget = columnwidget;
	}
}
