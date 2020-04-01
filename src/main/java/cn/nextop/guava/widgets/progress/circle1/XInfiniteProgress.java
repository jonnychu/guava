package cn.nextop.guava.widgets.progress.circle1;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.widgets.progress.circle1.builder.XInfiniteProgressBuilder;
import cn.nextop.guava.widgets.progress.circle1.model.XInfiniteProgressModel;
import cn.nextop.guava.widgets.progress.circle1.render.panel.XInfiniteProgressPanel;

/**
 * @author jonny
 */
public class XInfiniteProgress extends Canvas implements PropertyChangeListener {
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
		this.model.addPropListener(this);
		addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				stop();
			}
		});
	}
	
	/**
	 * 
	 */
	public void stop() { this.model.stop(); }
	
	public void start() { this.model.start(); }
	
	public XInfiniteProgressModel getModel() { return model; }
	
	@Override public void propertyChange(PropertyChangeEvent evt) {
		this.panel.repaint();
	}
}
