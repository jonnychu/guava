package cn.nextop.guava.widgets.datetime.render;

import org.eclipse.draw2d.AbstractHintLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;

/**
 * @author jonny
 */
public abstract class AbstractPanel extends Figure {
	//
	protected final int margin = 8, arc = 5;
	//
	protected abstract void layoutManager(IFigure container);
	
	/**
	 * 
	 */
	public AbstractPanel() { setLayoutManager(new CustomLayout()); }

	/**
	 * 
	 */
	protected class CustomLayout extends AbstractHintLayout {

		@Override public void layout(IFigure container) { layoutManager(container); }

		@Override
		protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) { return null; }
	}
}
