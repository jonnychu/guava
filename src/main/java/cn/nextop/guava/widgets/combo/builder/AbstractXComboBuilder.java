package cn.nextop.guava.widgets.combo.builder;

import java.lang.reflect.ParameterizedType;

import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.support.property.Property;
import cn.nextop.guava.widgets.combo.XCombo;
import cn.nextop.guava.widgets.combo.model.colum.Column;
import cn.nextop.guava.widgets.combo.model.colum.Columns;
import cn.nextop.guava.widgets.combo.model.config.XComboConfig;
import cn.nextop.guava.widgets.combo.render.popup.widget.BoolCellWidget;

/**
 * @author jonny
 */
public abstract class AbstractXComboBuilder<T> {
	
	public abstract XCombo builder(Composite cmp);
	
	@SuppressWarnings("unchecked")
	public Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
	
	public class XComboBuilder {
		//
		private XCombo combo;
		private Columns colums;
		private Column<T> colum;
		
		/**
		 * 
		 */
		public XComboConfig getXComboConfig() {
			return this.combo.getModel().getXComboConfig();
		}
		
		/**
		 * 
		 */
		public XComboBuilder(Composite cmp) {
			this.colums = new Columns();
			this.combo = new XCombo(cmp);
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
		
		public XComboBuilder bool() {
			this.colum.setCellWidget(BoolCellWidget.class); return this;
		}
		
		public XComboBuilder property(String name) {
			this.colum.setProperty(new Property<T>(getTClass(), name)); return this;
		}
		
		public XCombo builder() {
			combo.getModel().setColums(this.colums);
			return combo;
		}
	}
}
