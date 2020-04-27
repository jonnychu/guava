package cn.nextop.guava.widgets.table.render.widget.external;

import static cn.nextop.guava.support.swt.Colors.COLOR_GRAY;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.support.swt.Colors;

/**
 * @author jonny
 */
public class XTableButtonWidget extends XTableWidget {
	//
	private boolean arm = false;
	
	public XTableButtonWidget() {
		addMouseListener(new MouseListener.Stub());
		addMouseMotionListener(new MouseMotionListener.Stub());
	}

	@Override
	protected void paintClientArea(Graphics g) {
		super.paintClientArea(g);
		final Rectangle r = getClientArea();
		if(!this.enter) g.setBackgroundColor(COLOR_GRAY);
		else g.setBackgroundColor(Colors.COLOR_DARK_GRAY);
		g.fillRoundRectangle(getBounds(), this.arc, this.arc);
		Dimension d1 = INSTANCE.getStringExtents(text, g.getFont());
		if(!this.press) {
			g.drawText(this.text, r.x + (r.width - d1.width) / 2, r.y + (r.height - d1.height) / 2);
		} else {
			g.drawText(this.text, r.x + (r.width - d1.width) / 2 + this.offset, r.y + (r.height - d1.height) / 2 + + this.offset);
		}
	}

	@Override
	public void handleMouseEntered(MouseEvent event) {
		super.handleMouseEntered(event); this.enter = true; repaint();
	}
	
	@Override
	public void handleMouseExited(MouseEvent event) {
		super.handleMouseExited(event); this.arm = false; this.press = false; this.enter = false; repaint();
	}
	
	@Override
	public void handleMousePressed(MouseEvent event) {
		super.handleMousePressed(event); this.arm = true; this.press = true; repaint();
	}
	
	@Override
	public void handleMouseReleased(MouseEvent event) {
		super.handleMouseReleased(event);
		this.press = false; repaint();
		if(this.action != null && this.arm) {
			this.action.accept(getRow());
		}
		this.arm = false;
	}
}
