package cn.nextop.guava.widgets.datetime.render;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;

/**
 * @author jonny
 */
public abstract class AbstractWidget extends Figure {
	//
	protected String text;
	protected boolean editable;
	protected boolean selected;
	//
	protected final int margin = 2, arc = 3;
	
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

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
