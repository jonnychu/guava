package cn.nextop.guava;

import static cn.nextop.guava.utils.SwtUtils.creator;
import static cn.nextop.guava.utils.SwtUtils.dispatch;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.utils.SwtUtils;
import cn.nextop.guava.widgets.datetime.XDateTime;
import cn.nextop.guava.widgets.spinner.XSpinner;
import cn.nextop.guava.widgets.table.XTable;
import cn.nextop.guava.widgets.table.builder.XTableAdapter;
import cn.nextop.guava.widgets.table.builder.XTableBuilder;
import cn.nextop.guava.widgets.table.model.basic.row.XTableRow;
import net.miginfocom.swt.MigLayout;


public class Example {
	public static void main(String[] args) {
		final String name = "Widget Example";
		Shell shell = creator(500, 400, name);
		Composite cmp = SwtUtils.creator(shell);
		cmp.setLayout(new MigLayout("insets 5, gap 0 0","[fill,grow]","[fill,grow][fill,grow][fill,grow][fill,grow]"));
		XDateTime date = new XDateTime(cmp); date.setLayoutData("cell 0 0, width 10:150:,height 23!");
		XSpinner spinner1 = new XSpinner(cmp, SWT.HORIZONTAL); spinner1.setLayoutData("cell 0 1, width 10:150:,height 23!");
		XSpinner spinner2 = new XSpinner(cmp, SWT.VERTICAL); spinner2.setLayoutData("cell 0 2, width 10:150:,height 23!");
		
		// date time
		date.setInput(System.currentTimeMillis());
		
		
		//
//		Example example = new Example();
//		ExampleAdapter adapter = example.new ExampleAdapter();
//		XTable table = adapter.build(cmp); table.setLayoutData("cell 0 1");
//		
//		List<Row> rows = new ArrayList<>(); 
//		rows.add(example.new Row("name1","name2","name3","name4","name5"));
//		rows.add(example.new Row("name1","name2","name3","name4","name5"));
//		rows.add(example.new Row("name1","name2","name3","name4","name5"));
//		rows.add(example.new Row("name1","name2","name3","name4","name5"));
//		rows.add(example.new Row("name1","name2","name3","name4","name5"));
//		rows.add(example.new Row("name1","name2","name3","name4","name5"));
//		rows.add(example.new Row("name1","name2","name3","name4","name5"));
//		rows.add(example.new Row("name1","name2","name3","name4","name5"));
//		rows.add(example.new Row("name1","name2","name3","name4","name5"));
//		rows.add(example.new Row("name1","name2","name3","name4","name5"));
//		table.inputs(rows);
		shell.open(); dispatch(() -> shell.isDisposed());
	}
	
	public class ExampleAdapter extends XTableAdapter<Row> {

		@Override
		public XTable build(Composite cmp) {
			XTableBuilder r = new XTableBuilder(cmp);
			r.column().text("no1").property(Row.class, "name1").pixel(100).build();
			r.column().text("no2").property(Row.class, "name2").pixel(100).build();
			r.column().text("no3").property(Row.class, "name3").pixel(200).build();
			r.column().text("no4").property(Row.class, "name4").pixel(100).build();
			r.column().text("no5").property(Row.class, "name5").pixel(100).build();
			return r.builder();
		}
	}
	
	public class Row implements XTableRow.RowIdAware {
		private String name1;
		private String name2;
		private String name3;
		private String name4;
		private String name5;
		
		@Override
		public Object rowId() {
			return null;
		}
		
		public Row(String name1, String name2, String name3, String name4, String name5) {
			this.name1 = name1;
			this.name2 = name2;
			this.name3 = name3;
			this.name4 = name4;
			this.name5 = name5;
		}
		
		public String getName1() {
			return name1;
		}
		public void setName1(String name1) {
			this.name1 = name1;
		}
		public String getName2() {
			return name2;
		}
		public void setName2(String name2) {
			this.name2 = name2;
		}
		public String getName3() {
			return name3;
		}
		public void setName3(String name3) {
			this.name3 = name3;
		}
		public String getName4() {
			return name4;
		}
		public void setName4(String name4) {
			this.name4 = name4;
		}
		public String getName5() {
			return name5;
		}
		public void setName5(String name5) {
			this.name5 = name5;
		}
	}
}
