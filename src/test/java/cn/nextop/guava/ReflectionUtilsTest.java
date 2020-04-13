package cn.nextop.guava;

import org.junit.Test;

import cn.nextop.guava.support.reflection.ReflectionUtils;
import cn.nextop.guava.widgets.combo.model.row.IRow;

public class ReflectionUtilsTest {
	public class Row implements IRow {
		//
		private boolean selected;
		private String col2, col3;
		
		/**
		 * 
		 */
		public Row(boolean selected, String col2, String col3) {
			this.selected = selected; this.col2 = col2; this.col3 = col3;
		}
		
		/**
		 * 
		 */
		public String getCol2() {
			return col2;
		}

		public void setCol2(String col2) {
			this.col2 = col2;
		}

		public String getCol3() {
			return col3;
		}

		public void setCol3(String col3) {
			this.col3 = col3;
		}
		
		@Override
		public boolean isSelected() {
			return this.selected;
		}
		
		public void setSelected(boolean selected) {
			this.selected = selected;
		}
	}
	
	@Test
	public void test1() {
		System.out.println(ReflectionUtils.findMethod(Row.class, "getCol3"));
		System.out.println(ReflectionUtils.findField(Row.class, "selected"));
		System.out.println(ReflectionUtils.findField(Row.class, "selected").getType());
	}
}
