package cn.nextop.guava;

import static cn.nextop.guava.support.swt.SwtUtils.creator;
import static cn.nextop.guava.support.swt.SwtUtils.dispatch;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.support.swt.SwtUtils;
import cn.nextop.guava.widgets.table.XTable;
import cn.nextop.guava.widgets.table.builder.external.AbstractXTableBuilder;
import cn.nextop.guava.widgets.table.model.row.IRow;
import net.miginfocom.swt.MigLayout;

/**
 * @author jonny
 */
public class TableExample {

	public static void main(String[] args) {
		TableExample exp = new TableExample();
		final String name = "Widget Example";
		Shell shell = creator(500, 500, name);
		Composite cmp = SwtUtils.creator(shell);
		cmp.setLayout(new MigLayout("insets 5, gap 0 0","[fill,grow]","[fill,grow]"));
		{
			XTable table = new AbstractXTableBuilder<Row>() {
				@Override
				public XTable builder(Composite cmp) {
					XTableBuilder r = new XTableBuilder(cmp);
//					r.colum().pixel(100).text("Column1").align(SWT.CENTER).property("col1");
//					r.colum().pixel(100).text("Column2").align(SWT.CENTER).property("col2");
//					r.colum().pixel(100).text("Column3").align(SWT.CENTER).property("col3");
//					r.colum().pixel(100).text("Column4").align(SWT.CENTER).property("col4");
//					r.colum().pixel(100).text("Column5").align(SWT.CENTER).property("col5");
//					r.colum().pixel(100).text("Column6").align(SWT.CENTER).property("col6");
//					r.colum().pixel(100).text("Column7").align(SWT.CENTER).property("col7");
//					r.colum().pixel(100).text("Column8").align(SWT.CENTER).property("col8");
//					r.colum().pixel(100).text("Column9").align(SWT.CENTER).property("col9");
//					r.colum().pixel(100).text("Column10").align(SWT.CENTER).property("col10");
//					r.colum().pixel(100).text("Column11").align(SWT.CENTER).property("col11");
//					r.colum().pixel(100).text("Column12").align(SWT.CENTER).property("col12");
//					r.colum().pixel(100).text("Column13").align(SWT.CENTER).property("col13");
//					r.colum().pixel(100).text("Column14").align(SWT.CENTER).property("col14");
//					r.colum().pixel(100).text("Column15").align(SWT.CENTER).property("col15");
//					r.colum().pixel(100).text("Column16").align(SWT.CENTER).property("col16");
//					r.colum().pixel(100).text("Column17").align(SWT.CENTER).property("col17");
//					r.colum().pixel(100).text("Column18").align(SWT.CENTER).property("col18");
//					r.colum().pixel(100).text("Column19").align(SWT.CENTER).property("col19");
//					r.colum().pixel(100).text("Column20").align(SWT.CENTER).property("col20");
					
					r.colum().pixel(100).text("Column1").align(SWT.CENTER).property("col1");
					r.colum().pixel(100).text("Column2").align(SWT.CENTER).property("col2");
					r.colum().weight(1).text("Column3").align(SWT.CENTER).property("col3");
					return r.builder();
				}
			}.builder(cmp);
			table.setLayoutData("cell 0 0");
			
			List<IRow> rows = new ArrayList<>();
			for (int i = 0; i < 1000; i++) {
				rows.add(exp.new Row("row"+i, "row"+i, "row"+i, "row"+i, "row"+i, "row"+i, "row"+i, "row"+i, "row"+i, "row"+i
						, "row"+i, "row"+i, "row"+i, "row"+i, "row"+i, "row"+i, "row"+i, "row"+i, "row"+i, "row"+i));
			}
			table.input(rows);
		}
		
		//
		shell.open(); dispatch(() -> shell.isDisposed());
	}
	
	/**
	 * Combo data
	 */
	public class Row implements IRow {
		//
		private boolean selected;
		private String col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20;
		
		/**
		 * 
		 */
		public Row(String col1, String col2, String col3,
				String col4, String col5, String col6,
				String col7, String col8, String col9,
				String col10, String col11, String col12,
				String col13, String col14, String col15,
				String col16, String col17, String col18,
				String col19, String col20) {
			this.col1 = col1; this.col2 = col2; this.col3 = col3;
			this.col4 = col4; this.col5 = col5; this.col6 = col6;
			this.col7 = col7; this.col8 = col8; this.col9 = col9;
			this.col10 = col10; this.col11 = col11; this.col12 = col12;
			this.col13 = col13; this.col14 = col14; this.col15 = col15;
			this.col16 = col16; this.col17 = col17; this.col18 = col18;
			this.col19 = col19; this.col20 = col20;
		}
		
		/**
		 * 
		 */
		public String getCol1() {
			return col1;
		}

		public void setCol1(String col1) {
			this.col1 = col1;
		}

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

		public String getCol4() {
			return col4;
		}

		public void setCol4(String col4) {
			this.col4 = col4;
		}

		public String getCol5() {
			return col5;
		}

		public void setCol5(String col5) {
			this.col5 = col5;
		}

		public String getCol6() {
			return col6;
		}

		public void setCol6(String col6) {
			this.col6 = col6;
		}

		public String getCol7() {
			return col7;
		}

		public void setCol7(String col7) {
			this.col7 = col7;
		}

		public String getCol8() {
			return col8;
		}

		public void setCol8(String col8) {
			this.col8 = col8;
		}

		public String getCol9() {
			return col9;
		}

		public void setCol9(String col9) {
			this.col9 = col9;
		}

		public String getCol10() {
			return col10;
		}

		public void setCol10(String col10) {
			this.col10 = col10;
		}

		public String getCol11() {
			return col11;
		}

		public void setCol11(String col11) {
			this.col11 = col11;
		}

		public String getCol12() {
			return col12;
		}

		public void setCol12(String col12) {
			this.col12 = col12;
		}

		public String getCol13() {
			return col13;
		}

		public void setCol13(String col13) {
			this.col13 = col13;
		}

		public String getCol14() {
			return col14;
		}

		public void setCol14(String col14) {
			this.col14 = col14;
		}

		public String getCol15() {
			return col15;
		}

		public void setCol15(String col15) {
			this.col15 = col15;
		}

		public String getCol16() {
			return col16;
		}

		public void setCol16(String col16) {
			this.col16 = col16;
		}

		public String getCol17() {
			return col17;
		}

		public void setCol17(String col17) {
			this.col17 = col17;
		}

		public String getCol18() {
			return col18;
		}

		public void setCol18(String col18) {
			this.col18 = col18;
		}

		public String getCol19() {
			return col19;
		}

		public void setCol19(String col19) {
			this.col19 = col19;
		}

		public String getCol20() {
			return col20;
		}

		public void setCol20(String col20) {
			this.col20 = col20;
		}
		
		/**
		 * 
		 */
		@Override
		public String displayName() {
			return this.col2;
		}
		
		@Override
		public boolean isSelected() {
			return this.selected;
		}
		
		@Override
		public void setSelected(boolean selected) {
			this.selected = selected;
		}
		
		@Override
		public String toString() {
			return "ID : " + getId().toString() + ", Select : " + isSelected();
		}
	}

}
