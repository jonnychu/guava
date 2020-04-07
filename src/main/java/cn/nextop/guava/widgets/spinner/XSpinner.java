package cn.nextop.guava.widgets.spinner;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;
import static org.eclipse.swt.SWT.HORIZONTAL;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.nebula.widgets.formattedtext.AbstractFormatter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.widgets.spinner.builder.XSpinnerBuilder;
import cn.nextop.guava.widgets.spinner.model.XSpinnerModel;
import cn.nextop.guava.widgets.spinner.render.panel.XSpinnerPanel;

/**
 * @author jonny
 */
public class XSpinner<T extends Number> extends Canvas implements ActionListener {
	//
	private XSpinnerText text;
	private XSpinnerPanel panel;
	private XSpinnerModel<T> model;
	private final int orientation;
	private LightweightSystem lws;
	private XSpinnerBuilder builder;
	private AbstractFormatter formatter;
	
	/**
	 * SWT.HORIZONTAL, SWT.VERTICAL
	 */
	public XSpinner(Composite parent, int orientation, AbstractFormatter formatter) {
		super(parent, SWT.DOUBLE_BUFFERED);
		this.formatter = formatter;
		this.orientation = orientation;
		Class<?> type = formatter.getValueType();
		this.model = new XSpinnerModel<>(type);
		this.builder = new XSpinnerBuilder();
		this.lws = new LightweightSystem(this);
		this.panel = cast(this.builder.build(this));
		
		//
		this.lws.setContents(this.panel);
		this.text = new XSpinnerText(this);
	}
	
	/**
	 * 
	 */
	public XSpinnerText getText() {
		return text;
	}
	
	public XSpinnerModel<?> getModel() {
		return model;
	}
	
	public XSpinnerBuilder getBuilder() {
		return this.builder;
	}
	
	public AbstractFormatter getFormatter() {
		return formatter;
	}
	
	public boolean isHorz() {
		return orientation == HORIZONTAL ? true : false;
	}
	
	public void setValue(T min, T max, T step, T value) {
		model.init(min, max, step, value); this.panel.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionName()) {
		case "button.up":
			model.increment(); builder.getTxtSpinner().repaint(); break;
		case "button.down":
			model.decrement(); builder.getTxtSpinner().repaint(); break;
		default: throw new RuntimeException("NO !!!"+e);
		}
	}
}
