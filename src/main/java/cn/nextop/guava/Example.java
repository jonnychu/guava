package cn.nextop.guava;

import static cn.nextop.guava.utils.SwtUtils.async;
import static cn.nextop.guava.utils.SwtUtils.creator;
import static cn.nextop.guava.utils.SwtUtils.dispatch;

import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.nebula.widgets.formattedtext.BigDecimalFormatter;
import org.eclipse.nebula.widgets.formattedtext.LongFormatter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.utils.SwtUtils;
import cn.nextop.guava.widgets.datetime.XDateTime;
import cn.nextop.guava.widgets.progress.circle.XCircleProgress;
import cn.nextop.guava.widgets.progress.circle1.XInfiniteProgress;
import cn.nextop.guava.widgets.slider.XSlider;
import cn.nextop.guava.widgets.spinner.XSpinner;
import cn.nextop.guava.widgets.table.XTable;
import cn.nextop.guava.widgets.table.builder.XTableAdapter;
import cn.nextop.guava.widgets.table.builder.XTableBuilder;
import cn.nextop.guava.widgets.table.model.basic.row.XTableRow;
import net.miginfocom.swt.MigLayout;


public class Example {
	public static void main(String[] args) {
		final String name = "Widget Example";
		Shell shell = creator(500, 500, name);
		Composite cmp = SwtUtils.creator(shell);
		cmp.setLayout(new MigLayout("insets 5, gap 0 0","[fill,grow,center][fill,grow,center]","[fill,grow][fill,grow][fill,grow][fill,grow][fill,grow][fill,grow][fill,grow]"));
		//datetime
		XDateTime date = new XDateTime(cmp); date.setLayoutData("cell 0 0, width 10:150:,height 23!, span 2"); date.setInput(System.currentTimeMillis());
		//spinner 1
		XSpinner<BigDecimal> spinner1 = new XSpinner<>(cmp, SWT.VERTICAL, new BigDecimalFormatter("##0.00","##0")); 
		spinner1.setLayoutData("cell 0 1, width 10:150:,height 23!, span 2"); 
		spinner1.setValue(new BigDecimal("100.00"), new BigDecimal("-100.00"), new BigDecimal("0.01"), new BigDecimal("1.00"));
		//spinner 2
		XSpinner<Long> spinner2 = new XSpinner<>(cmp, SWT.HORIZONTAL, new LongFormatter("-########0","########0")); 
		spinner2.setLayoutData("cell 0 2, width 10:150:,height 50!, span 2"); 
		spinner2.setValue(new Long(999999999), new Long(-999999999), new Long(100), new Long(100));
		//slider
		XSlider slider = new XSlider(cmp, SWT.VERTICAL); slider.setLayoutData("cell 0 3, width 10:150:,height 24!, span 2");
		slider.setValue(0, 100, 23);
		//circle progress
		XCircleProgress progress = new XCircleProgress(cmp); progress.setLayoutData("cell 0 4, width 100!,height 100!");
		progress.init(0f, 100f, 25f);
		Button btnOk1 = new Button(cmp, SWT.NONE); btnOk1.setLayoutData("cell 0 5, height 30!, top");
		btnOk1.setText("Start Progress"); btnOk1.addSelectionListener(new SelectionAdapter() {
			boolean finished = false; @Override public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e); if(finished) return;
				progress.init(0f, 100f, 0f); Timer timer = new Timer(); timer.scheduleAtFixedRate(new TimerTask() {
					int i = 0; @Override public void run() { 
						async(null, () -> { progress.setInput(i++); if(i > 100) { timer.cancel(); finished = false; } else { finished = true; } });
						}
				}, 250, 50);
			}
		});
		//infinite progress
		XInfiniteProgress progress1 = new XInfiniteProgress(cmp); progress1.setLayoutData("cell 1 4, width 100!,height 100!");
		Button btnOk2 = new Button(cmp, SWT.NONE); btnOk2.setLayoutData("cell 1 5, height 30!, top");
		btnOk2.setText("Start Progress"); btnOk2.addSelectionListener(new SelectionAdapter() {
			boolean running = false; @Override public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e); if (!running) {
					running = true;	progress1.start(); btnOk2.setText("Stop Progress");
				} else {
					running = false; progress1.stop(); btnOk2.setText("Start Progress");
				}
					
			}
		});
		
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
