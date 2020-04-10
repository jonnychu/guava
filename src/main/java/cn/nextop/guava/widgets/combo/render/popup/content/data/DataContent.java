package cn.nextop.guava.widgets.combo.render.popup.content.data;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.combo.XComboPopup;
import cn.nextop.guava.widgets.combo.model.XComboModel;
import cn.nextop.guava.widgets.combo.model.colum.Column;
import cn.nextop.guava.widgets.combo.model.config.XComboConfig;
import cn.nextop.guava.widgets.combo.model.row.IRow;
import cn.nextop.guava.widgets.combo.render.AbstractComboPanel;
import cn.nextop.guava.widgets.combo.render.popup.PopupPanel;
import cn.nextop.guava.widgets.combo.render.popup.content.data.row.RowPanel;

/**
 * @author jonny
 */
public class DataContent extends AbstractComboPanel {
	//
	private PopupPanel panel;
	private RowPanel[] rowPanel;
	
	/**
	 * 
	 */
	public DataContent(String name, PopupPanel panel, List<Column<?>> columns) {
		super(name); this.panel = panel;
		//
		XComboPopup popup = panel.getXComboPopup();
		XComboModel model = popup.getXCombo().getModel();
		final List<IRow> rows = model.getRows().getRows();
		//
		this.rowPanel = new RowPanel[rows.size()];
		for (int i = 0; i < this.rowPanel.length; i++) {
			this.rowPanel[i] = new RowPanel(panel, columns, rows.get(i));
			add(this.rowPanel[i]);
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
		if(rowPanel.length == 0) return;
		DataContent content = cast(container);
		XComboPopup popup = panel.getXComboPopup();
		XComboModel model = popup.getXCombo().getModel();
		final XComboConfig cfg = model.getXComboConfig();
		//
		final int height = cfg.getItemHeight();
		final Rectangle r = content.getClientArea();
		for (int i = 0; i < rowPanel.length; i++) {
			rowPanel[i].setBounds(new Rectangle(r.x, r.y + height * i, r.width, height));
		}
	}
}
