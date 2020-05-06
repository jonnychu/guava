package cn.nextop.guava.widgets.progress.circle.builder;

import static cn.nextop.guava.support.util.Objects.cast;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.widgets.Canvas;

import cn.nextop.guava.widgets.AbstractBuilder;
import cn.nextop.guava.widgets.progress.circle.XCircleProgress;
import cn.nextop.guava.widgets.progress.circle.render.panel.XCircleProgressPanel;

/**
 * @author jonny
 */
public class XCircleProgressBuilder extends AbstractBuilder {
	
	@Override
	public IFigure build(Canvas parent) {
		final XCircleProgress progress = cast(parent);
		return new XCircleProgressPanel("circle.progress.panel", progress);
	}
}
