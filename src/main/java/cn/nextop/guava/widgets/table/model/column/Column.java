package cn.nextop.guava.widgets.table.model.column;

import static cn.nextop.guava.support.Objects.cast;

import org.eclipse.swt.SWT;

import cn.nextop.guava.support.property.Property;
import cn.nextop.guava.widgets.table.render.AbstractXTableCellWidget;
import cn.nextop.guava.widgets.table.render.AbstractXTableColumnWidget;
import cn.nextop.guava.widgets.table.render.widget.DefaultCellWidget;
import cn.nextop.guava.widgets.table.render.widget.DefaultColumnWidget;

/**
 * @author jonny
 */
public class Column<T> {
	//
	private int height = 24;
	private String text = "";
	private int pixel = 30, weight = 0;
	private int colAlign = SWT.CENTER;
	private int cellAlign = SWT.CENTER;
	//
	private Property<T> property;
	private Class<? extends AbstractXTableCellWidget> cellWidget;
	private Class<? extends AbstractXTableColumnWidget> columnwidget;
	
	/**
	 * 
	 */
	public Column() {
		this.cellWidget = cast(DefaultCellWidget.class);
		this.columnwidget = cast(DefaultColumnWidget.class);
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
	
	public int getPixel() {
		return pixel;
	}

	public void setPixel(int pixel) {
		this.pixel = pixel;
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

	public void setCellWidget(Class<? extends AbstractXTableCellWidget> cellWidget) {
		this.cellWidget = cellWidget;
	}

	public Class<?> getColumnwidget() {
		return columnwidget;
	}

	public void setColumnwidget(Class<? extends AbstractXTableColumnWidget> columnwidget) {
		this.columnwidget = columnwidget;
	}
}
