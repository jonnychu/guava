package cn.nextop.guava.widgets.progress.circle;

import static cn.nextop.guava.support.util.Objects.cast;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.widgets.progress.circle.builder.XCircleProgressBuilder;
import cn.nextop.guava.widgets.progress.circle.model.XCircleProgressModel;
import cn.nextop.guava.widgets.progress.circle.render.panel.XCircleProgressPanel;

/**
 * @author jonny
 */
public class XCircleProgress extends Canvas {
	//
	private LightweightSystem lws;
	private XCircleProgressPanel panel;
	private XCircleProgressModel model;
	private XCircleProgressBuilder builder;
	
	/**
	 * 
	 */
	public XCircleProgress(Composite parent) {
		super(parent, SWT.DOUBLE_BUFFERED);
		this.lws = new LightweightSystem(this);
		this.model = new XCircleProgressModel();
		this.builder = new XCircleProgressBuilder();
		this.panel = cast(this.builder.build(this));
		//
		this.lws.setContents(this.panel);
	}
	
	/**
	 * 
	 */
	public float getInput() {
		return this.model.getValue();
	}
	
	public void setInput(float value) {
		this.model.setValue(value); this.panel.repaint();
	}
	
	public float getValueRange() {
		return this.model.getMax() - this.model.getMin();
	}
	
	public void init(float min, float max, float value) {
		this.model.init(min, max, value);
	}
	
	/**
	 * 
	 */
	public XCircleProgressModel getModel() { return model; }
}
