package cn.nextop.guava;

import static cn.nextop.guava.utils.SwtUtils.async;
import static cn.nextop.guava.utils.SwtUtils.creator;
import static cn.nextop.guava.utils.SwtUtils.dispatch;
import static cn.nextop.guava.utils.SwtUtils.sync;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.nebula.widgets.formattedtext.BigDecimalFormatter;
import org.eclipse.nebula.widgets.formattedtext.LongFormatter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import cn.nextop.guava.utils.SwtUtils;
import cn.nextop.guava.widgets.combo.XCombo;
import cn.nextop.guava.widgets.combo.builder.AbstractXComboBuilder;
import cn.nextop.guava.widgets.combo.model.config.XComboConfig;
import cn.nextop.guava.widgets.combo.model.row.IRow;
import cn.nextop.guava.widgets.datetime.XDateTime;
import cn.nextop.guava.widgets.progress.circle.XCircleProgress;
import cn.nextop.guava.widgets.progress.circle1.XInfiniteProgress;
import cn.nextop.guava.widgets.slider.XSlider;
import cn.nextop.guava.widgets.spinner.XSpinner;
import net.miginfocom.swt.MigLayout;


public class Example {
	public static void main(String[] args) {
		Example example = new Example();
		final String name = "Widget Example";
		Shell shell = creator(500, 500, name);
		Composite cmp = SwtUtils.creator(shell);
		cmp.setLayout(new MigLayout("insets 5, gap 0 0","[fill,grow,center][fill,grow,center]","[fill,grow][fill,grow][fill,grow][fill,grow][fill,grow][fill,grow][fill,grow][fill,grow]"));
		//datetime
		XDateTime date = new XDateTime(cmp); date.setLayoutData("cell 0 0, width 10:150:,height 23!, span 2"); date.setInput(System.currentTimeMillis());
		//spinner 1
		XSpinner<BigDecimal> spinner1 = new XSpinner<>(cmp, SWT.VERTICAL, new BigDecimalFormatter("-##0.00","##0.00")); 
		spinner1.setLayoutData("cell 0 1, width 10:150:,height 23!, span 2"); 
		spinner1.setValue(new BigDecimal("-100.00"), new BigDecimal("100.00"), new BigDecimal("0.01"), new BigDecimal("1.00"));
		//spinner 2
		XSpinner<Long> spinner2 = new XSpinner<>(cmp, SWT.HORIZONTAL, new LongFormatter("-########0","########0")); 
		spinner2.setLayoutData("cell 0 2, width 10:150:,height 50!, span 2"); 
		spinner2.setValue(new Long(-999999999), new Long(999999999), new Long(100), new Long(100));
		//slider
		XSlider slider = new XSlider(cmp, SWT.VERTICAL); slider.setLayoutData("cell 0 3, width 10:150:,height 24!, span 2");
		slider.setValue(0, 100, 23);
		//circle progress
		XCircleProgress progress = new XCircleProgress(cmp); progress.setLayoutData("cell 0 4");
		progress.init(0f, 100f, 25f); Button btnOk1 = new Button(cmp, SWT.NONE); btnOk1.setLayoutData("cell 0 5, height 30!, top");
		btnOk1.setText("Start Progress"); CircleProgressModel model = example.new CircleProgressModel(progress);
		btnOk1.addSelectionListener(new SelectionAdapter() {
			boolean running = false; @Override public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e); if (!running) {
					running = true;	model.start(); btnOk1.setText("Stop Progress");
				} else {
					running = false; model.stop(); btnOk1.setText("Start Progress");
				}
			}
		});
		shell.addDisposeListener(new DisposeListener() { @Override public void widgetDisposed(DisposeEvent e) { model.stop(); }});
		//infinite progress
		XInfiniteProgress progress1 = new XInfiniteProgress(cmp); progress1.setLayoutData("cell 1 4");
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
		{
			XCombo combo = new AbstractXComboBuilder<Row>() {
				
				@Override
				public XCombo builder(Composite cmp) {
					XComboBuilder r = new XComboBuilder(cmp);
					r.colum().text("Select").align(SWT.LEFT).bool().property("isSelected").weight(1);
					r.colum().text("Column1").align(SWT.CENTER).property("getCol2").weight(1);
					r.colum().text("Column2").align(SWT.RIGHT).property("getCol3").weight(1);
					return r.builder();
				}
			}.builder(cmp); 
			
			List<IRow> rows = new ArrayList<>();
			rows.add(example.new Row(true, "row11", "row12"));
			rows.add(example.new Row(true, "row21", "row22"));
			rows.add(example.new Row(true, "row31", "row32"));
			rows.add(example.new Row(true, "row41", "row42"));
			rows.add(example.new Row(true, "row51", "row52"));
			rows.add(example.new Row(true, "row61", "row62"));
			rows.add(example.new Row(true, "row71", "row72"));
			rows.add(example.new Row(true, "row81", "row82"));
			rows.add(example.new Row(true, "row91", "row92"));
			rows.add(example.new Row(true, "row101", "row102"));
			combo.input(rows);
			combo.setLayoutData("cell 0 6, width 10:150:,height 24!");
		}

		
		//
		{
			XCombo combo = new AbstractXComboBuilder<Row>() {
				
				@Override
				public XCombo builder(Composite cmp) {
					//
					XComboBuilder r = new XComboBuilder(cmp);
					XComboConfig config = r.getXComboConfig();
					config.setHeader(false); config.setPopupWidth(200); config.setPopupHeight(200);;
					
					r.colum().text("Select").align(SWT.LEFT).bool().property("isSelected").weight(2);
					r.colum().text("Column1").align(SWT.CENTER).property("getCol2").weight(8);
					return r.builder();
				}
			}.builder(cmp); 
			
			List<IRow> rows = new ArrayList<>();
			rows.add(example.new Row(true, "row11", "row12"));
			rows.add(example.new Row(true, "row21", "row22"));
			rows.add(example.new Row(true, "row31", "row32"));
			rows.add(example.new Row(true, "row41", "row42"));
			rows.add(example.new Row(true, "row51", "row52"));
			combo.input(rows);
			combo.setLayoutData("cell 1 6, width 10:150:,height 24!");
		}

		{
			XCombo combo = new AbstractXComboBuilder<Row>() {
				
				@Override
				public XCombo builder(Composite cmp) {
					//
					XComboBuilder r = new XComboBuilder(cmp);
					XComboConfig config = r.getXComboConfig();
					config.setHeader(false); config.setPopupWidth(200); config.setPopupHeight(200);;
					
					r.colum().text("Column1").align(SWT.LEFT).property("getCol2").weight(8);
					return r.builder();
				}
			}.builder(cmp); 
			
			List<IRow> rows = new ArrayList<>();
			rows.add(example.new Row(true, "row11", "row12"));
			rows.add(example.new Row(true, "row21", "row22"));
			rows.add(example.new Row(true, "row31", "row32"));
			rows.add(example.new Row(true, "row41", "row42"));
			rows.add(example.new Row(true, "row51", "row52"));
			rows.add(example.new Row(true, "row51", "row52"));
			combo.input(rows);
			combo.setLayoutData("cell 0 7, width 10:150:,height 24!");
		}
		
		//
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
	
	protected class CircleProgressModel {
		private int i = 0;
		private Timer timer;
		private boolean running, cancel;
		private XCircleProgress progress;
		
		public CircleProgressModel(XCircleProgress progress) {
			this.progress = progress;
		}
		
		/**
		 * 
		 */
		public void stop() { sync(null, () -> { suspend(); }); }
		
		public void start() {
			if (running && !cancel)	return;
			running = true; timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					async(null, () -> {
						progress.setInput(i++);
						if (i > 100) {
							timer.cancel(); i = 0;
							running = false; cancel = false;
						} else {
							running = true;
						}
					});
				}
			}, 250, 50);
		}

		public void suspend() {
			if (timer == null) return; timer.cancel(); timer = null; running = false; cancel = false;
		}
	}
	
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
}
