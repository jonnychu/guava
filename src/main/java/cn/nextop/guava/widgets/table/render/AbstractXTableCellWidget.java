package cn.nextop.guava.widgets.table.render;

import static org.eclipse.draw2d.Cursors.HAND;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;

import cn.nextop.guava.widgets.table.model.column.Column;
import cn.nextop.guava.widgets.table.model.row.IRow;

/**
 * @author jonny
 */
public abstract class AbstractXTableCellWidget extends Figure {
	// Default Value
	protected IRow row;
	protected Column<?> column;
	protected String text = "";
	protected boolean enter = false;
	protected boolean editable = true;
	protected boolean selected = false;
	//
	protected final int margin = 8, arc = 3, oval = 5;
	
	/**
	 * 
	 */
	public AbstractXTableCellWidget() {
		this(true, true);
	}
	
	public AbstractXTableCellWidget(boolean m1, boolean m2) {
		if(m1) addMouseListener(new MouseListener.Stub());
		if(m2) addMouseMotionListener(new MouseMotionListener.Stub());
	}
	
	/**
	 * 
	 */
	public IRow getRow() {
		return row;
	}

	public void setRow(IRow row) {
		this.row = row;
	}
	
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
	public void handleMouseExited(MouseEvent event) { super.handleMouseExited(event); this.enter = false; repaint(); }
	
	@Override
	public void handleMouseEntered(MouseEvent event) { super.handleMouseEntered(event); this.enter = true; if(this.isEnabled()) setCursor(HAND); repaint(); }
}
