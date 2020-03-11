package cn.nextop.guava.widgets.table.model.basic.group;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;

import cn.nextop.guava.widgets.table.model.basic.column.XTableColumn;

/**
 * @author jonny
 */
public class XTableGroup {
	//
	private String text = "";
	private int height = 26;
	private int align = SWT.CENTER;
	private List<XTableColumn> columns;
	
	/**
	 * 
	 */
	public XTableGroup() {
		this.columns = new ArrayList<>();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getAlign() {
		return align;
	}

	public void setAlign(int align) {
		this.align = align;
	}

	public List<XTableColumn> getColumns() {
		return columns;
	}

	public void addAll(XTableColumn ...columns) {
		this.columns.addAll(asList(columns));
	}
	
	public void addAll(List<XTableColumn> columns) {
		this.columns.addAll(columns);
	}
}
