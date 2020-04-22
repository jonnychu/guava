package cn.nextop.guava.widgets.table.builder.external;

import java.lang.reflect.ParameterizedType;

import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.support.property.Property;
import cn.nextop.guava.widgets.table.XTable;
import cn.nextop.guava.widgets.table.model.column.Column;
import cn.nextop.guava.widgets.table.model.column.Columns;
import cn.nextop.guava.widgets.table.model.config.XTableConfig;

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
		public XTableConfig getXComboConfig() {
			return this.table.getModel().getXTableConfig();
		}
		
		/**
		 * 
		 */
		public XTableBuilder(Composite cmp) {
			this.columns = new Columns();
			this.table = new XTable(cmp);
		}
		
		public XTableBuilder text(String text) {
			this.column.setText(text); return this;
		}
		
		public XTableBuilder pixel(int pixel) {
			this.column.setPixel(pixel); return this;
		}
		
		public XTableBuilder weight(int weight) {
			this.column.setWeight(weight); return this;
		}
		
		public XTableBuilder colAlign(int align) {
			this.column.setColAlign(align); return this;
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
		 * 
		 */
		public XTable builder() {
			table.getModel().setColumns(this.columns); 
			table.getFactory().buildHeader();
			return table;
		}
	}
}
