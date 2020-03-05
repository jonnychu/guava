package cn.nextop.guava.widgets.datetime.render.text.acton;

import org.eclipse.draw2d.IFigure;

import cn.nextop.guava.widgets.datetime.render.text.TextPanel;

/**
 * @author jonny
 */
public class TextAction extends AbstractTextAction {

	@Override
	protected boolean updateData(IFigure container, IFigure widget) {
		TextPanel textPanel = (TextPanel)container;
		return true;
	}
}
