package cn.nextop.guava.widgets.progress.circle1.builder;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.widgets.Canvas;

import cn.nextop.guava.widgets.AbstractBuilder;
import cn.nextop.guava.widgets.progress.circle1.XInfiniteProgress;
import cn.nextop.guava.widgets.progress.circle1.render.panel.XInfiniteProgressPanel;

/**
 * @author jonny
 */
public class XInfiniteProgressBuilder extends AbstractBuilder {
	
	@Override
	public IFigure build(Canvas parent) {
		final XInfiniteProgress progress = cast(parent);
		return new XInfiniteProgressPanel("infinite.progress.panel", progress);
	}
}
