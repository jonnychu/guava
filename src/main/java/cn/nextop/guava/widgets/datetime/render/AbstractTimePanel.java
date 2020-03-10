package cn.nextop.guava.widgets.datetime.render;

import java.util.List;

import org.eclipse.draw2d.AbstractHintLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;

/**
 * @author jonny
 */
public abstract class AbstractTimePanel extends Figure {
	//
	private final String name;
	protected final int margin = 8, arc = 5;
	
	//
	public String getName() { return name; }
	
	//
	protected abstract void layoutManager(IFigure container);
	protected Dimension calPreferredSize(IFigure container, int wHint, int hHint) { return null; }
	
	/**
	 * 
	 */
	public AbstractTimePanel(String name) {
		this.name = name;
		if(getLayoutManager() == null) setLayoutManager(new CustomLayout());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<IFigure> getChildren() { return super.getChildren(); }
	
	/**
	 * 
	 */
	protected class CustomLayout extends AbstractHintLayout {

		@Override public void layout(IFigure container) { layoutManager(container); }

		@Override
		protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {
			return calPreferredSize(container, wHint, hHint);
		}
	}
}
