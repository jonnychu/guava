package cn.nextop.guava.widgets.table.model.column;

import static cn.nextop.guava.support.Objects.cast;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.swt.SWT;

import cn.nextop.guava.support.Sequence;
import cn.nextop.guava.support.property.Property;
import cn.nextop.guava.widgets.table.model.cell.ColCell;
import cn.nextop.guava.widgets.table.model.cell.RowCell;
import cn.nextop.guava.widgets.table.model.row.IRow;
import cn.nextop.guava.widgets.table.render.AbstractXTableCellWidget;
import cn.nextop.guava.widgets.table.render.AbstractXTableColumnWidget;
import cn.nextop.guava.widgets.table.render.widget.external.XTableWidget;
import cn.nextop.guava.widgets.table.support.formatter.XTableFormatter;
import cn.nextop.guava.widgets.table.support.formatter.impl.XTableDefaultFormatter;
import cn.nextop.guava.widgets.table.support.glossary.Sort;

/**
 * @author jonny
 */
public class Column<T> {
	//
	private int colId;
	private int height = 24;
	private String text = "";
	private Sort sort = Sort.NONE;
	private int colAlign = SWT.CENTER;
	private int cellAlign = SWT.CENTER;
	private int pixel = 30, weight = 0, minimum = 20;
	//
	private ColCell colCell;
	private RowCell rowCell;
	private Property<T> property;
	private XTableFormatter<T> formatter;
	//
	private final PropertyChangeSupport listeners;
	public static final String PROPERTY_SORT = "COLUMN_SORT";
	public static final String PROPERTY_RESIZE = "COLUMN_RESIZE";
	
	/**
	 * 
	 */
	public Column() {
		//
		this.colCell = new ColCell();
		this.rowCell = new RowCell();
		this.colId = Sequence.nextInt();
		this.formatter = new XTableDefaultFormatter<>();
		this.listeners = new PropertyChangeSupport(this);
	}
	
	/**
	 * 
	 */
	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}
	
	public int getColId() {
		return colId;
	}

	public void setColId(int colId) {
		this.colId = colId;
	}
	
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
	
	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	
	public int getPixel() {
		return pixel;
	}

	public void setPixel(int pixel) {
		int ov = this.pixel;
		this.pixel = pixel;
		fire(PROPERTY_RESIZE, ov, getPixel());
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
	
	public XTableFormatter<T> getFormatter() {
		return formatter;
	}

	public void setFormatter(XTableFormatter<T> formatter) {
		this.formatter = formatter;
	}
	
	public XTableWidget[] getCellRenderWidgets() {
		return rowCell.getCellRenderWidgets();
	}
	
	public void setCellRenderWidgets(XTableWidget[] widgets) {
		this.rowCell.setCellRenderWidgets(widgets);
	}
	
	public Class<? extends AbstractXTableCellWidget> getCellWidget() {
		return rowCell.getCellWidget();
	}
	
	public void setCellWidget(Class<? extends AbstractXTableCellWidget> cellWidget) {
		this.rowCell.setCellWidget(cellWidget);;
	}

	public Class<? extends AbstractXTableColumnWidget> getColumnwidget() {
		return colCell.getColumnwidget();
	}

	public void setColumnwidget(Class<? extends AbstractXTableColumnWidget> columnwidget) {
		this.colCell.setColumnwidget(columnwidget);
	}
	
	/**
	 * 
	 */
	public void fire(String string, int oldValue, int newValue) {
		listeners.firePropertyChange(string, oldValue, newValue);
	}
	
	public void fire(String string, Sort oldValue, Sort newValue) {
		listeners.firePropertyChange(string, oldValue, newValue);
	}
	
	public void addPropListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}
	
	public void removePropListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}
	
	public void sort(List<IRow> rows) {
		Collections.sort(rows, new Comparator<IRow>() {
			@Override
			public int compare(IRow o1, IRow o2) {
				Class<?> clazz = property.getType();
				Object v1 = property.getValue(cast(o1));
				Object v2 = property.getValue(cast(o2));
				if(v1 == null && v2 == null) return 0;
				if(v1 == null) return -1; if(v2 == null) return +1;
				int asc = 1; switch (sort) {
					case ASC: asc = 1; break;
					case DESC: asc = -1; break;
					default: return o1.getRowId() - o2.getRowId() > 0 ? -1 : 1;
				}
				
				//
				if (clazz.equals(int.class)) {
					return asc * ((int) v1 - (int) v2);
				} else if (clazz.equals(byte.class)) {
					return asc * ((byte) v1 - (byte) v2);
				} else if (clazz.equals(short.class)) {
					return asc * ((short) v1 - (short) v2);
				} else if (clazz.equals(long.class)) {
					return (int) (asc * ((long) v1 - (long) v2));
				} else if (clazz.equals(float.class)) {
					return (int) (asc * ((float) v1 - (float) v2));
				} else if (clazz.equals(double.class)) {
					return (int) (asc * ((double) v1 - (double) v2));
				} else if (clazz.equals(Byte.class)) {
					return asc * ((Byte)v1).compareTo((Byte)v2);
				} else if (clazz.equals(Long.class)) {
					return asc * ((Long)v1).compareTo((Long)v2);
				} else if (clazz.equals(Short.class)) {
					return asc * ((Short)v1).compareTo((Short)v2);
				} else if (clazz.equals(Float.class)) {
					return asc * ((Float)v1).compareTo((Float)v2);
				} else if (clazz.equals(Double.class)) {
					return asc * ((Double)v1).compareTo((Double)v2);
				} else if (clazz.equals(Integer.class)) {
					return asc * ((Integer)v1).compareTo((Integer)v2);
				} else if (clazz.equals(BigDecimal.class)) {
					return asc * ((BigDecimal)v1).compareTo((BigDecimal)v2);
				} else {
					return v1.toString().compareTo(v2.toString());
				}
			}
		});
	}
}
