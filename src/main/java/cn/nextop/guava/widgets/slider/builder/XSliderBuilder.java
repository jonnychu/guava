package cn.nextop.guava.widgets.slider.builder;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.widgets.Canvas;

import cn.nextop.guava.widgets.AbstractBuilder;
import cn.nextop.guava.widgets.slider.XSlider;
import cn.nextop.guava.widgets.slider.render.panel.XSliderPanel;
import cn.nextop.guava.widgets.slider.render.panel.XSliderPanel.DragListener;
import cn.nextop.guava.widgets.slider.render.widget.Indicator;
import cn.nextop.guava.widgets.slider.render.widget.SliderBar;

/**
 * @author jonny
 */
public class XSliderBuilder extends AbstractBuilder {
	//
	private SliderBar sliderBar;
	private Indicator indicator;
	private XSliderPanel sliderPanel;
	
	@Override
	public IFigure build(Canvas parent) {
		final XSlider slider = cast(parent);
		this.sliderPanel = new XSliderPanel("slider.panel", slider);
		this.sliderPanel.add(indicator = new Indicator("indicator.widget"));
		this.sliderPanel.add(sliderBar = new SliderBar("sliderbar.widget", slider));
		
		//
		DragListener listener = this.sliderPanel.new DragListener();
		this.indicator.addMouseListener(listener);
		this.indicator.addMouseMotionListener(listener);
		
		return this.sliderPanel;
	}
	
	/**
	 * 
	 */
	public SliderBar getSliderBar() {
		return sliderBar;
	}
	
	public Indicator getIndicator() {
		return indicator;
	}

	public XSliderPanel getSliderPanel() {
		return sliderPanel;
	}
}
