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
	
	public class XComboBuilder {
		//
		private XTable table;
		private Columns colums;
		private Column<T> colum;
		
		/**
		 * 
		 */
		public XTableConfig getXComboConfig() {
			return this.table.getModel().getConfig();
		}
		
		/**
		 * 
		 */
		public XComboBuilder(Composite cmp) {
			this.colums = new Columns();
			this.table = new XTable(cmp);
		}
		
		public XComboBuilder text(String text) {
			this.colum.setText(text); return this;
		}
		
		public XComboBuilder weight(int weight) {
			this.colum.setWeight(weight); return this;
		}
		
		public XComboBuilder colAlign(int align) {
			this.colum.setColAlign(align); return this;
		}
		
		public XComboBuilder align(int align) {
			this.colum.setCellAlign(align); return this;
		}
		
		public XComboBuilder colum() {
			this.colums.add(this.colum = new Column<>()); return this;
		}
		
		public XComboBuilder property(String name) {
			this.colum.setProperty(new Property<T>(getTClass(), name)); return this;
		}
		
		/**
		 * 
		 */
		public XTable builder() {
			table.getModel().setColums(this.colums); return table;
		}
	}
}
