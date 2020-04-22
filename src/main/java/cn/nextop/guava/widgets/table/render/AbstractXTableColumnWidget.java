package cn.nextop.guava.widgets.table.render;

import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.table.model.column.Column;

/**
 * @author jonny
 */
public abstract class AbstractXTableColumnWidget extends Figure {
	// Default Value
	protected Column<?> column;
	protected String text = "";
	protected boolean editable = true;
	protected boolean selected = false;
	//
	protected final int margin = 8, arc = 3, oval = 5;
	
	/**
	 * 
	 */
	public AbstractXTableColumnWidget() {
		DragListener listener = new DragListener();
		addMouseListener(listener); addMouseMotionListener(listener);
	}
	
	/**
	 * 
	 */
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Column<?> getColumn() {
		return column;
	}

	public void setColumn(Column<?> column) {
		this.column = column;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	@Override
	public void handleMouseMoved(MouseEvent event) {
		super.handleMouseMoved(event);
		Rectangle r = getEdge(((AbstractXTableColumnWidget)event.getSource()).getBounds());
		if(r.contains(event.getLocation())) setCursor(Cursors.SIZEWE); else setCursor(null);
	}
	
	protected Rectangle getEdge(Rectangle r) {
		if(r == null) return null;
		return new Rectangle(r.x + r.width - 5, r.y, 5, r.y + r.height);
	}
	
	/**
	 * 
	 */
	protected class DragListener extends MouseMotionListener.Stub implements MouseListener {
		//
		protected Point start;
		protected boolean armed;
		protected int revertValue;
		
		@Override
		public void mousePressed(MouseEvent me) {
			start = me.getLocation(); armed = true;
			revertValue = column.getPixel(); me.consume();
		}

		@Override
		public void mouseDragged(MouseEvent me) {
			if (!armed) return;
			final Point p = me.getLocation();
			final int mSzie = column.getMinimum();
			final Dimension diff = p.getDifference(start);
			if(revertValue + diff.width <= mSzie) column.setPixel(mSzie);
			else column.setPixel(revertValue + diff.width);	me.consume();
		}
		
		@Override
		public void mouseDoubleClicked(MouseEvent me) {}
		
		@Override
		public void mouseReleased(MouseEvent me) {
			if (!armed) return; armed = false; me.consume();
		}
	}
}
