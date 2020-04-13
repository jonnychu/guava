package cn.nextop.guava.widgets.combo.render.popup.content.data.row;

import static cn.nextop.guava.support.Objects.cast;

import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener.Stub;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.support.swt.CGUtils;
import cn.nextop.guava.support.swt.Colors;
import cn.nextop.guava.widgets.combo.XComboPopup;
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
	private boolean enter;
	private PopupPanel panel;
	private List<Column<?>> columns;
	private AbstractCellWidget[] widgets;
	
	public RowPanel(PopupPanel panel, List<Column<?>> columns, IRow row) {
		super(""); addMouseMotionListener(new Stub());
		this.panel = panel; this.columns = columns;
		this.widgets = new AbstractCellWidget[columns.size()];
		for (int i = 0; i < columns.size(); i++) {
			Column<?> column = columns.get(i);
			Class<?> clazz = column.getCellWidget();
			try {
				this.widgets[i] = cast(clazz.newInstance());
				this.widgets[i].setColumn(column);
				this.widgets[i].setRow(row); add(this.widgets[i]);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void paintClientArea(Graphics g) {
		if(enter) CGUtils.fillRect(g, getClientArea(), Colors.COLOR_GRAY);
		else CGUtils.fillRect(g, getClientArea(), Colors.COLOR_WHITE);
		super.paintClientArea(g);
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
		final int aw = r.width / tw, ih = config.getItemHeight();
		int cx = 0, rw = r.width;
		for (int j = 0; j < widgets.length; j++) {
			AbstractCellWidget cw = widgets[j];
			if(j == widgets.length - 1) {
				cw.setBounds(new Rectangle(r.x + cx, r.y, rw, ih));
			} else {
				cw.setBounds(new Rectangle(r.x + cx, r.y, aw, ih));
			}
			cx = cx + aw; rw = rw - aw;
		}
	}
	
	@Override
	public void handleMouseExited(MouseEvent event) {
		enter = false; repaint();
	}
	
	@Override
	public void handleMouseEntered(MouseEvent event) {
		enter = true; repaint();
	}
}
