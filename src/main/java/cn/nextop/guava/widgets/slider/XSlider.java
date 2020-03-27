package cn.nextop.guava.widgets.slider;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;
import static org.eclipse.swt.SWT.HORIZONTAL;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import cn.nextop.guava.widgets.slider.builder.XSliderBuilder;
import cn.nextop.guava.widgets.slider.model.XSliderModel;
import cn.nextop.guava.widgets.slider.render.panel.XSliderPanel;
import cn.nextop.guava.widgets.spinner.builder.XSpinnerBuilder;

public class XSlider extends Canvas implements ActionListener {
	//
	private XSliderPanel panel;
	private XSliderModel model;
	private final int orientation;
	private LightweightSystem lws;
	private XSliderBuilder builder;
	
	/**
	 * SWT.HORIZONTAL, SWT.VERTICAL
	 */
	public XSlider(Composite parent, int orientation) {
		super(parent, SWT.DOUBLE_BUFFERED);
		this.orientation = orientation;
		this.model = new XSliderModel();
		this.builder = new XSliderBuilder();
		this.lws = new LightweightSystem(this);
		this.panel = cast(this.builder.build(this));
		//
		this.lws.setContents(this.panel);
	}
	
	/**
	 * 
	 */
	public XSliderModel getModel() {
		return model;
	}
	
	public XSliderBuilder getBuilder() {
		return this.builder;
	}
	
	public boolean isHorz() {
		return orientation == HORIZONTAL ? true : false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionName()) {}
	}
}