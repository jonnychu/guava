package cn.nextop.guava;

import static cn.nextop.guava.support.swt.SwtUtils.creator;
import static cn.nextop.guava.support.swt.SwtUtils.dispatch;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.support.swt.SwtUtils;
import cn.nextop.guava.widgets.table.XTable;
import cn.nextop.guava.widgets.table.builder.external.AbstractXTableBuilder;
import cn.nextop.guava.widgets.table.model.XTableModel;
import cn.nextop.guava.widgets.table.model.row.AbstractRow;
import cn.nextop.guava.widgets.table.model.row.IRow;
import cn.nextop.guava.widgets.table.render.widget.external.XTableButtonWidget;
import cn.nextop.guava.widgets.table.render.widget.external.XTableWidget;
import cn.nextop.guava.widgets.table.support.selection.impl.SingleRowSelection;
import net.miginfocom.swt.MigLayout;

/**
 * @author jonny
 */
public class TableExample {

	public static void main(String[] args) {
		TableExample exp = new TableExample();
		final String name = "Table Example";
		Shell shell = creator(500, 500, name);
		Composite cmp = SwtUtils.creator(shell);
		cmp.setLayout(new MigLayout("insets 5, gap 0 0","[fill,grow]","[30][fill,grow]"));
		//
		Builder builder = exp.new Builder();
		XTable table = builder.onClick1((v) -> {
			System.out.println("click1 : "+v.getRowId());
		}).onClick2((v) -> {
			System.out.println("click2 : "+v.getRowId());
		}).builder(cmp);
		table.setLayoutData("cell 0 1");
		Button btn = new Button(cmp, SWT.NONE); btn.setLayoutData("cell 0 0"); btn.setText("click me");
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<IRow> rows = new ArrayList<>();
				for (int i = 0; i < 10000; i++) {
					rows.add(exp.new Row(Byte.valueOf("127"), Short.valueOf(i+""), new Integer(i+1), new Long(1000000+i),
							new Double(2000000000+i), new BigDecimal("3000000000000000."+i), System.currentTimeMillis()));
				}
				table.input(rows);
			}
		});
		
		//
		shell.open(); dispatch(() -> shell.isDisposed());
	}
	
	public class Builder extends AbstractXTableBuilder<Row> {
		
		final XTableWidget btnOk = new XTableButtonWidget().text("Action1");
		final XTableWidget btnClose = new XTableButtonWidget().text("Action2");
		
		public Builder onClick1(Consumer<IRow> consumer) {
			btnOk.action(consumer); return this;
		}
		
		public Builder onClick2(Consumer<IRow> consumer) {
			btnClose.action(consumer); return this;
		}
		
		@Override
		public XTable builder(Composite cmp) {
			XTableBuilder r = new XTableBuilder(cmp);
			XTableModel model = r.getXTableModel();
			model.setSelection(new SingleRowSelection());
			r.colum().pixel(100).text("Column1").align(SWT.CENTER).sortable().property("rowId");
			r.colum().pixel(100).text("Column2").align(SWT.LEFT).sortable().property("col2").number("#,###");
			r.colum().pixel(100).text("Column4").align(SWT.RIGHT).property("col3").sortable().number("#,###");
			r.colum().pixel(100).text("Column5").align(SWT.RIGHT).property("col4").sortable().number("#,###");
			r.colum().pixel(100).text("Column6").align(SWT.RIGHT).property("col5").sortable().number("#,###");
			r.colum().pixel(200).text("Column7").align(SWT.RIGHT).property("col6").sortable().number("#,###.0000");
			r.colum().pixel(150).text("Column8").align(SWT.RIGHT).property("col7").sortable().datetime("yyyy-MM-dd HH:mm:ss");
			r.colum().pixel(150).text("Action").align(SWT.CENTER).render(btnOk, btnClose);
			return r.builder();
		}
		
	}
	
	/**
	 * Combo data
	 */
	public class Row extends AbstractRow {
		//
		private Byte col1;
		private Short col2;
		private Integer col3;
		private Long col4;
		private Double col5;
		private BigDecimal col6;
		private Long col7;
		
		/**
		 * 
		 */
		public Row(Byte col1, Short col2, Integer col3,
				Long col4, Double col5, BigDecimal col6,
				long col7) {
			this.col1 = col1; this.col2 = col2; this.col3 = col3;
			this.col4 = col4; this.col5 = col5; this.col6 = col6;
			this.col7 = col7;
		}
		
		public Byte getCol1() {
			return col1;
		}

		public void setCol1(Byte col1) {
			this.col1 = col1;
		}

		public Short getCol2() {
			return col2;
		}

		public void setCol2(Short col2) {
			this.col2 = col2;
		}

		public Integer getCol3() {
			return col3;
		}

		public void setCol3(Integer col3) {
			this.col3 = col3;
		}

		public Long getCol4() {
			return col4;
		}

		public void setCol4(Long col4) {
			this.col4 = col4;
		}

		public Double getCol5() {
			return col5;
		}

		public void setCol5(Double col5) {
			this.col5 = col5;
		}

		public BigDecimal getCol6() {
			return col6;
		}

		public void setCol6(BigDecimal col6) {
			this.col6 = col6;
		}

		public Long getCol7() {
			return col7;
		}

		public void setCol7(Long col7) {
			this.col7 = col7;
		}
	}
}
