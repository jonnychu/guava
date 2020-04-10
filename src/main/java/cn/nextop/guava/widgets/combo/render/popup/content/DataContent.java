package cn.nextop.guava.widgets.combo.render.popup.content;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.AbstractPanel;
import cn.nextop.guava.widgets.combo.XComboPopup;
import cn.nextop.guava.widgets.combo.model.XComboModel;
import cn.nextop.guava.widgets.combo.model.colum.Column;
import cn.nextop.guava.widgets.combo.model.config.XComboConfig;
import cn.nextop.guava.widgets.combo.model.row.IRow;
import cn.nextop.guava.widgets.combo.render.AbstractCellWidget;
import cn.nextop.guava.widgets.combo.render.popup.PopupPanel;

public class DataContent extends AbstractPanel {
	//
	private PopupPanel panel;
	private List<Column<?>> columns;
	private AbstractCellWidget[][] widgets;
	
	/**
	 * 
	 */
	public DataContent(String name, PopupPanel panel, List<Column<?>> columns) {
		super(name); this.columns = columns; this.panel = panel;
		//
		XComboPopup popup = panel.getXComboPopup();
		XComboModel model = popup.getXCombo().getModel();
		final List<IRow> rows = model.getRows().getRows();
		widgets = new AbstractCellWidget[rows.size()][columns.size()];
		for (int i = 0; i < widgets.length; i++) {
			IRow row = rows.get(i);
			for (int j = 0; j < widgets[i].length; j++) {
				Column<?> column = columns.get(j);
				Class<?> clazz = column.getCellWidget();
				try {
					this.widgets[i][j] = cast(clazz.newInstance());
					this.widgets[i][j].setColumn(column);
					this.widgets[i][j].setRow(row);
					add(this.widgets[i][j]);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize(int wHint, int hHint) {
		XComboPopup popup = panel.getXComboPopup();
		XComboModel model = popup.getXCombo().getModel();
		final int size = model.getRows().getRows().size();
		final XComboConfig config = model.getXComboConfig();
		return new Dimension(wHint, size * config.getItemHeight());
	}
	
	@Override
	protected void layoutManager(IFigure container) {
		if(widgets == null || widgets.length == 0) return;
		//
		DataContent content = cast(container);
		XComboPopup popup = panel.getXComboPopup();
		XComboModel model = popup.getXCombo().getModel();
		XComboConfig config = model.getXComboConfig();
		//
		final Rectangle r = content.getClientArea();
		int tw = 0; for (int i = 0; i < columns.size(); i++) {
			tw = tw +  columns.get(i).getWeight();
		}
		
		//
		final int aw = r.width / tw, ih = config.getItemHeight();
		for (int i = 0; i < widgets.length; i++) {
			int cx = 0, rw = r.width;
			for (int j = 0; j < widgets[i].length; j++) {
				AbstractCellWidget cw = widgets[i][j];
				if(j == widgets[i].length - 1) {
					cw.setBounds(new Rectangle(r.x + cx, r.y + ih * i, rw, ih));
				} else {
					cw.setBounds(new Rectangle(r.x + cx, r.y + ih * i, aw, ih));
				}
				cx = cx + aw; rw = rw - aw;
			}
		}
	}
}
