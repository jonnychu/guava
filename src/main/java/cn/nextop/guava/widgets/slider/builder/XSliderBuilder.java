package cn.nextop.guava.widgets.slider.builder;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;
import static com.patrikdufresne.fontawesome.FontAwesome.caret_down;
import static com.patrikdufresne.fontawesome.FontAwesome.caret_left;
import static com.patrikdufresne.fontawesome.FontAwesome.caret_right;
import static com.patrikdufresne.fontawesome.FontAwesome.caret_up;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.widgets.Canvas;

import cn.nextop.guava.widgets.AbstractBuilder;
import cn.nextop.guava.widgets.slider.render.panel.XSliderPanel;
import cn.nextop.guava.widgets.spinner.XSpinner;
import cn.nextop.guava.widgets.spinner.render.panel.XSpinnerPanel;
import cn.nextop.guava.widgets.spinner.render.widget.XSpinnerButtonWidget;
import cn.nextop.guava.widgets.spinner.render.widget.XSpinnerTextWidget;

/**
 * @author jonny
 */
public class XSliderBuilder extends AbstractBuilder {
	//
	private XSliderPanel sliderPanel;
	
	@Override
	public IFigure build(Canvas parent) {
		final XSpinner spinner = cast(parent);
		final boolean isHorz = spinner.isHorz();
		String s1 = isHorz ? caret_right : caret_up;
		String s2 = isHorz ? caret_left : caret_down;
		return this.sliderPanel;
	}
}
