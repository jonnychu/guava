package cn.nextop.guava.widgets.table.render.widget;

import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import cn.nextop.guava.widgets.table.render.AbstractXTableColumnWidget;

/**
 * @author jonny
 */
public class DefaultColumnResizeWidget extends AbstractXTableColumnWidget {
	
	/**
	 * 
	 */
	public DefaultColumnResizeWidget() {
		DragListener listener = new DragListener();
		addMouseListener(listener); addMouseMotionListener(listener);
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
		public void mouseEntered(MouseEvent me) {
			super.mouseEntered(me); setCursor(Cursors.SIZEWE);
		}
		
		@Override
		public void mouseExited(MouseEvent me) {
			super.mouseExited(me); setCursor(null);
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
