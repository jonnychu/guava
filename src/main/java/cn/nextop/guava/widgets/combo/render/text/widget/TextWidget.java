package cn.nextop.guava.widgets.combo.render.text.widget;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import cn.nextop.guava.widgets.combo.XCombo;
import cn.nextop.guava.widgets.combo.model.XComboModel;
import cn.nextop.guava.widgets.combo.render.text.TextPanel;

/**
 * @author jonny
 */
public class TextWidget extends Figure {

	/**
	 * 
	 */
	public TextWidget() {
	}

	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		final Rectangle rect = getBounds();
		TextPanel textPanel = cast(getParent());
		XCombo combo = textPanel.getXCombo();
		XComboModel model = combo.getModel();
		//
		final String text = "";
		Dimension d1 = INSTANCE.getStringExtents(text, g.getFont());
		g.drawString(text, rect.x, rect.y + (rect.height - d1.height) / 2);
	}
}
