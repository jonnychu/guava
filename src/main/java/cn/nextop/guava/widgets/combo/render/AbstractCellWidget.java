package cn.nextop.guava.widgets.combo.render;

import static cn.nextop.guava.support.swt.Colors.COLOR_DARK_GRAY;
import static org.eclipse.draw2d.Cursors.HAND;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;

import cn.nextop.guava.support.Objects;
import cn.nextop.guava.widgets.combo.XComboPopup;
import cn.nextop.guava.widgets.combo.model.XComboModel;
import cn.nextop.guava.widgets.combo.model.colum.Column;
import cn.nextop.guava.widgets.combo.model.row.IRow;
import cn.nextop.guava.widgets.combo.render.popup.PopupPanel;

/**
 * @author jonny
 */
public abstract class AbstractCellWidget extends Figure {
	// Default Value
	protected IRow row;
	protected Figure parent;
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
	public AbstractCellWidget() {
		this(true, true);
	}
	
	public AbstractCellWidget(boolean m1, boolean m2) {
		if(m1) addMouseListener(new MouseListener.Stub());
		if(m2) addMouseMotionListener(new MouseMotionListener.Stub());
	}
	
	@Override
	protected void paintClientArea(Graphics g) {
		super.paintClientArea(g);
		final PopupPanel panel = Objects.cast(parent);
		final XComboPopup popup = panel.getXComboPopup();
		final XComboModel model = popup.getXCombo().getModel();
		if(!model.isEnable()) g.setForegroundColor(COLOR_DARK_GRAY);
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
	
	public Figure getParent() {
		return parent;
	}

	public void setParent(Figure parent) {
		this.parent = parent;
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
