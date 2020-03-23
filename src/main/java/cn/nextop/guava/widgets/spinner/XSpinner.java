package cn.nextop.guava.widgets.spinner;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.widgets.spinner.builder.XSpinnerBuilder;
import cn.nextop.guava.widgets.spinner.render.panel.XSpinnerPanel;

public class XSpinner extends Canvas {
	//
	private XSpinnerPanel panel;
	private final int orientation;
	private LightweightSystem lws;
	private XSpinnerBuilder builder;
	
	/**
	 * SWT.HORIZONTAL, SWT.VERTICAL
	 */
	public XSpinner(Composite parent, int orientation) {
		super(parent, SWT.DOUBLE_BUFFERED);
		this.orientation = orientation;
		this.builder = new XSpinnerBuilder();
		this.lws = new LightweightSystem(this);
		this.panel = cast(this.builder.build(this));
		this.lws.setContents(this.panel);
	}
	
	/**
	 * 
	 */
	public XSpinnerBuilder getBuilder() {
		return this.builder;
	}
	
	public boolean isHorz() {
		return orientation == SWT.HORIZONTAL ? true : false;
	}
}
