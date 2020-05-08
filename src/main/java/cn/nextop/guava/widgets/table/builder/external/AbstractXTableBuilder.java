package cn.nextop.guava.widgets.table.builder.external;

import java.lang.reflect.ParameterizedType;

import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.support.property.Property;
import cn.nextop.guava.widgets.table.XTable;
import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.model.column.Column;
import cn.nextop.guava.widgets.table.model.column.Columns;
import cn.nextop.guava.widgets.table.model.config.XTableConfig;
import cn.nextop.guava.widgets.table.render.widget.external.XTableWidget;
import cn.nextop.guava.widgets.table.support.formatter.XTableFormatter;
import cn.nextop.guava.widgets.table.support.formatter.impl.XTableDateFormatter;
import cn.nextop.guava.widgets.table.support.formatter.impl.XTableDatetimeFormatter;
import cn.nextop.guava.widgets.table.support.formatter.impl.XTableNumberFormatter;
import cn.nextop.guava.widgets.table.support.glossary.Sort;

/**
 * @author jonny
 */
public abstract class AbstractXTableBuilder<T> {
	
	public abstract XTable builder(Composite cmp);
	
	@SuppressWarnings("unchecked")
	public Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
	
	public class XTableBuilder {
		//
		private XTable table;
		private Columns columns;
		private Column<T> column;
		
		/**
		 * 
		 */
		public XTableBuilder(Composite cmp) {
			this.columns = new Columns();
			this.table = new XTable(cmp);
		}
		
		/**
		 * 
		 */
		public XTableBuilder text(String text) {
			this.column.setText(text); return this;
		}
		
		public XTableBuilder pixel(int pixel) {
			this.column.setPixel(pixel); return this;
		}
		
		public XTableBuilder weight(int weight) {
			this.column.setWeight(weight); return this;
		}
		
		public XTableBuilder sortable() {
			this.column.setSort(Sort.NONE); return this;
		}
		
		public XTableBuilder align(int align) {
			this.column.setCellAlign(align); return this;
		}
		
		public XTableBuilder minimum(int minimum) {
			this.column.setMinimum(minimum); return this;
		}
		
		public XTableBuilder colum() {
			this.columns.add(this.column = new Column<>()); return this;
		}
		
		public XTableBuilder property(String name) {
			this.column.setProperty(new Property<T>(getTClass(), name)); return this;
		}
		
		/**
		 * model
		 */
		public XTableModel getXTableModel() {
			return this.table.getModel();
		}
		
		/**
		 * config
		 */
		public XTableConfig getXTableConfig() {
			return this.table.getModel().getXTableConfig();
		}
		
		/**
		 * render cell
		 */
		public XTableBuilder render(XTableWidget ...widgets) {
			this.column.setCellRenderWidgets(widgets); return this;
		}
		
		/**
		 * 
		 */
		public XTableBuilder formatter(XTableFormatter<T> formatter) {
			this.column.setFormatter(formatter); return this;
		}
		
		public XTableBuilder date(String pattern) {
			this.column.setFormatter(new XTableDateFormatter<>(pattern)); return this;
		}
		
		public XTableBuilder number(String pattern) {
			this.column.setFormatter(new XTableNumberFormatter<>(pattern)); return this;
		}
		
		public XTableBuilder datetime(String pattern) {
			this.column.setFormatter(new XTableDatetimeFormatter<>(pattern)); return this;
		}
		
		/**
		 * 
		 */
		public XTable builder() {
			table.getModel().setColumns(this.columns); 
			table.getFactory().buildHeader(); return table;
		}
	}
}
