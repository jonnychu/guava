package cn.nextop.guava.widgets.progress.circle.builder;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.widgets.Canvas;

import cn.nextop.guava.widgets.AbstractBuilder;
import cn.nextop.guava.widgets.progress.circle.XCircleProgress;
import cn.nextop.guava.widgets.progress.circle.render.panel.XCircleProgressPanel;

public class XCircleProgressBuilder extends AbstractBuilder {
	//
	private XCircleProgressPanel panel;
	
	@Override
	public IFigure build(Canvas parent) {
		final XCircleProgress progress = cast(parent);
		this.panel = new XCircleProgressPanel("circleprogress.panel", progress);
		return this.panel;
	}
}
