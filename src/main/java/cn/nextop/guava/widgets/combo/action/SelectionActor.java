package cn.nextop.guava.widgets.combo.action;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.SWT;

import cn.nextop.guava.support.Objects;
import cn.nextop.guava.widgets.AbstractActor;
import cn.nextop.guava.widgets.combo.XCombo;
import cn.nextop.guava.widgets.combo.XComboPopup;
import cn.nextop.guava.widgets.combo.model.XComboModel;
import cn.nextop.guava.widgets.combo.model.config.XComboConfig;
import cn.nextop.guava.widgets.combo.model.row.IRow;
import cn.nextop.guava.widgets.combo.render.popup.PopupPanel;
import cn.nextop.guava.widgets.combo.render.popup.content.data.row.RowPanel;
import cn.nextop.guava.widgets.combo.render.text.TextPanel;

/**
 * @author jonny
 */
public class SelectionActor extends AbstractActor {

	@Override
	protected void updateUI(IFigure container, IFigure widget) {
		RowPanel panel = Objects.cast(container);
		PopupPanel popupPanel = panel.getPopupPanel();
		XComboPopup popup = popupPanel.getXComboPopup();
		//
		XCombo combo = popup.getXCombo();
		TextPanel textPanel = combo.getTextPanel();
		popupPanel.repaint(); textPanel.repaint();
	}

	@Override
	protected boolean updateData(IFigure container, IFigure widget) {
		RowPanel panel = Objects.cast(container);
		PopupPanel popupPanel = panel.getPopupPanel();
		XComboPopup popup = popupPanel.getXComboPopup();
		//
		XCombo combo = popup.getXCombo();
		XComboModel model = combo.getModel();
		XComboConfig cfg = model.getXComboConfig();
		List<IRow> rows = model.getRows().getRows();
		//
		if(!model.isEnable()) return true;
		final IRow select = panel.getRow();
		if(cfg.getStyle() == SWT.SINGLE) {
			for (IRow iRow : rows) {
				if(iRow == select) {
					select.setSelected(true);
				} else {
					iRow.setSelected(false);
				}
			}
		} else {
			select.setSelected(!select.isSelected());
		}
		return true;
	}

}
