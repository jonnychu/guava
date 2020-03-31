package cn.nextop.guava.widgets.progress.circle1;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.widgets.progress.circle.builder.XCircleProgressBuilder;
import cn.nextop.guava.widgets.progress.circle.model.XCircleProgressModel;
import cn.nextop.guava.widgets.progress.circle1.builder.XInfiniteProgressBuilder;
import cn.nextop.guava.widgets.progress.circle1.model.XInfiniteProgressModel;
import cn.nextop.guava.widgets.progress.circle1.render.panel.XInfiniteProgressPanel;

public class XInfiniteProgress extends Canvas {
	//
	private LightweightSystem lws;
	private XInfiniteProgressPanel panel;
	private XInfiniteProgressModel model;
	private XInfiniteProgressBuilder builder;
	
	/**
	 * 
	 */
	public XInfiniteProgress(Composite parent) {
		super(parent, SWT.DOUBLE_BUFFERED);
		this.lws = new LightweightSystem(this);
		this.model = new XInfiniteProgressModel();
		this.builder = new XInfiniteProgressBuilder();
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
	public XInfiniteProgressModel getModel() { return model; }
}
