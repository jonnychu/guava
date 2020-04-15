package cn.nextop.guava.widgets.combo.render.text.widget;

import static cn.nextop.guava.support.Objects.cast;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.combo.XCombo;
import cn.nextop.guava.widgets.combo.model.XComboModel;
import cn.nextop.guava.widgets.combo.model.config.XComboConfig;
import cn.nextop.guava.widgets.combo.model.row.IRow;
import cn.nextop.guava.widgets.combo.render.text.TextPanel;

/**
 * @author jonny
 */
public class TextWidget extends Figure {
	//
	private final static String P1 = "Selected(";
	private final static String P2 = "Selected All";
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		final Rectangle r = getBounds();
		TextPanel textPanel = cast(getParent());
		final XCombo combo = textPanel.getXCombo();
		final XComboModel model = combo.getModel();
		List<IRow> selection = model.getSelection();
		List<IRow> allRow = model.getRows().getRows();
		final StringBuilder name = new StringBuilder();
		final XComboConfig cfg = model.getXComboConfig();
		if(selection != null) {
			final int size = selection.size();
			if(size == allRow.size()) {
				name.append(P2);
			} else if(size > cfg.getTextDisplaySize()) { 
				name.append(P1).append(size).append(")");
			} else { 
				selection.stream()
				.forEach(e -> name.append(e.displayName()).append(" "));
			}
		}
		//
		final String text = name.toString();
		Dimension d1 = INSTANCE.getStringExtents(text, g.getFont());
		g.drawString(text, r.x + 8, r.y + (r.height - d1.height) / 2);
	}
}
