package cn.nextop.guava.widgets.table.render.table.widget;

import static org.eclipse.draw2d.Cursors.HAND;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.swt.SWT;

/**
 * @author jonny
 */
public abstract class AbstractColumnWidget extends Figure {
	// Default Value
	protected String text = "";
	protected boolean enter = false;
	protected boolean editable = true;
	protected boolean selected = false;
	//
	protected final int margin = 2;
	protected int width = 20, align = SWT.LEFT;
	
	/**
	 * 
	 */
	public AbstractColumnWidget() {
		this(true, true);
	}
	
	public AbstractColumnWidget(boolean m1, boolean m2) {
		if(m1) addMouseListener(new MouseListener.Stub());
		if(m2) addMouseMotionListener(new MouseMotionListener.Stub());
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
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getAlign() {
		return align;
	}

	public void setAlign(int align) {
		this.align = align;
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
	public void handleMouseEntered(MouseEvent event) { super.handleMouseEntered(event); this.enter = true; if(this.isEnabled()) setCursor(HAND); repaint(); }
}
