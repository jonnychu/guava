package cn.nextop.guava.widgets.combo.render.text.widget;

import static cn.nextop.guava.support.util.Objects.cast;
import static com.patrikdufresne.fontawesome.FontAwesome.caret_down;
import static com.patrikdufresne.fontawesome.FontAwesome.caret_up;
import static org.eclipse.draw2d.TextUtilities.INSTANCE;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

import com.patrikdufresne.fontawesome.FontAwesome;

import cn.nextop.guava.widgets.combo.XCombo;
import cn.nextop.guava.widgets.combo.XComboPopup;
import cn.nextop.guava.widgets.combo.render.text.TextPanel;

/**
 * @author jonny
 */
public class IconWidget extends Figure {

	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		final Rectangle r = getBounds();
		g.setFont(FontAwesome.getFont(10));
		TextPanel textPanel = cast(getParent());
		final XCombo combo = textPanel.getXCombo();
		final XComboPopup popup = combo.getPopup();
		final String text2 = (popup == null) ? caret_down : caret_up;
		Dimension d1 = INSTANCE.getStringExtents(text2, g.getFont());
		g.drawString(text2, r.x + r.width - d1.width - 8, r.y + (r.height - d1.height) / 2);
	}
}
