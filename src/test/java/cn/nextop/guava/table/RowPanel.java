package cn.nextop.guava.table;

import static cn.nextop.guava.support.Objects.cast;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;

import cn.nextop.guava.widgets.AbstractPanel;

/**
 * @author jonny
 */
public class RowPanel extends AbstractPanel {
	//
	private boolean enter;

	public RowPanel() {
		super("panel.row");
		
		addMouseListener(new MouseListener.Stub());
		addMouseMotionListener(new MouseMotionListener.Stub());
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		RowPanel rowPanel = cast(container);
	}
	
	@Override
	public void handleMouseReleased(MouseEvent event) {
		super.handleMouseReleased(event);
	}
	
	@Override
	public void handleMouseExited(MouseEvent event) { super.handleMouseExited(event); enter = false; repaint(); }
	
	@Override
	public void handleMouseEntered(MouseEvent event) { super.handleMouseEntered(event); enter = true; repaint(); }
}
