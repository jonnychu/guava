package cn.nextop.guava.widgets.spinner.render;

import static org.eclipse.draw2d.Cursors.HAND;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;

/**
 * @author jonny
 */
public abstract class AbstractXSpinnerWidget extends Figure {
	// Default Value
	protected String name;
	protected String text = "";
	protected boolean enter = false;
	protected boolean editable = true;
	protected boolean selected = false;
	//
	protected final int margin = 2, arc = 3, oval = 5;
	
	/**
	 * 
	 */
	public AbstractXSpinnerWidget(String name) {
		this(name, true, true);
	}
	
	public AbstractXSpinnerWidget(String name, boolean m1, boolean m2) {
		this.name = name;
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
