package cn.nextop.guava.widgets.datetime.render;

import static org.eclipse.draw2d.Cursors.HAND;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;

/**
 * @author jonny
 */
public abstract class AbstractWidget extends Figure {
	// Default Value
	protected String text = "";
	protected boolean enter = false;
	protected boolean editable = true;
	protected boolean selected = false;
	//
	protected final int margin = 2, arc = 3, oval = 3;
	
	/**
	 * 
	 */
	public AbstractWidget() {
		addMouseListener(new MouseListener.Stub());
		addMouseMotionListener(new MouseMotionListener.Stub());
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
	
	public boolean isEnter() {
		return enter;
	}

	public void setEnter(boolean enter) {
		this.enter = enter;
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
	public void handleMouseExited(MouseEvent event) { super.handleMouseExited(event); this.enter = false; repaint(); }
	
	@Override
	public void handleMouseEntered(MouseEvent event) { super.handleMouseEntered(event); this.enter = true; if(editable) setCursor(HAND); repaint(); }
}
