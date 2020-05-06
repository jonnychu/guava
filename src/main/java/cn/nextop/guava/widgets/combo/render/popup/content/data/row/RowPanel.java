package cn.nextop.guava.widgets.combo.render.popup.content.data.row;

import static cn.nextop.guava.support.Objects.cast;
import static cn.nextop.guava.support.swt.CGUtils.fillRect;

import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.support.swt.Colors;
import cn.nextop.guava.widgets.combo.XComboPopup;
import cn.nextop.guava.widgets.combo.action.SelectionAction;
import cn.nextop.guava.widgets.combo.model.XComboModel;
import cn.nextop.guava.widgets.combo.model.colum.Column;
import cn.nextop.guava.widgets.combo.model.config.XComboConfig;
import cn.nextop.guava.widgets.combo.model.row.IRow;
import cn.nextop.guava.widgets.combo.render.AbstractCellWidget;
import cn.nextop.guava.widgets.combo.render.AbstractComboPanel;
import cn.nextop.guava.widgets.combo.render.popup.PopupPanel;

/**
 * @author jonny
 */
public class RowPanel extends AbstractComboPanel {
	//
	private IRow row;
	private boolean enter;
	private PopupPanel panel;
	private List<Column<?>> columns;
	private AbstractCellWidget[] widgets;
	
	public PopupPanel getPopupPanel() {
		return panel;
	}

	public IRow getRow() {
		return row;
	}

	public RowPanel(PopupPanel panel, List<Column<?>> columns, IRow row) {
		super(""); this.row = row;
		this.panel = panel; this.columns = columns;
		this.widgets = new AbstractCellWidget[columns.size()];
		for (int i = 0; i < columns.size(); i++) {
			Column<?> column = columns.get(i);
			Class<?> clazz = column.getCellWidget();
			try {
				this.widgets[i] = cast(clazz.newInstance());
				this.widgets[i].setRow(row); widgets[i].setParent(panel); 
				this.widgets[i].setColumn(column); add(this.widgets[i]);
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
		
		addMouseListener(new MouseListener.Stub());
		addMouseMotionListener(new MouseMotionListener.Stub());
	}
	
	@Override
	protected void paintClientArea(Graphics g) {
		if(enter) fillRect(g, getClientArea(), Colors.COLOR_GRAY);
		else fillRect(g, getClientArea(), Colors.COLOR_WHITE); super.paintClientArea(g);
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		RowPanel rowPanel = cast(container);
		XComboPopup popup = panel.getXComboPopup();
		XComboModel model = popup.getXCombo().getModel();
		XComboConfig config = model.getXComboConfig();
		//
		final Rectangle r = rowPanel.getClientArea();
		int tw = 0; for (int i = 0; i < columns.size(); i++) {
			tw = tw +  columns.get(i).getWeight();
		}
		
		//
		int aw = r.width / tw, rw = r.width;
		int ih = config.getItemHeight(), cx = 0;
		for (int j = 0; j < widgets.length; j++) {
			AbstractCellWidget cw = widgets[j];
			int wt = cw.getColumn().getWeight();
			if(j == widgets.length - 1) {
				cw.setBounds(new Rectangle(r.x + cx, r.y, rw, ih));
			} else {
				cw.setBounds(new Rectangle(r.x + cx, r.y, aw * wt, ih));
			}
			cx = cx + aw * wt; rw = rw - aw * wt;
		}
	}
	
	@Override
	public void handleMouseReleased(MouseEvent event) {
		super.handleMouseReleased(event);
		new SelectionAction().onAction(this, null);
	}
	
	@Override
	public void handleMouseExited(MouseEvent event) { super.handleMouseExited(event); enter = false; repaint(); }
	
	@Override
	public void handleMouseEntered(MouseEvent event) { super.handleMouseEntered(event); enter = true; repaint(); }
}
