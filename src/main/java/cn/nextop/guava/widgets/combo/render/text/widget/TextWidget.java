package cn.nextop.guava.widgets.combo.render.text.widget;

import static cn.nextop.guava.widgets.table.support.util.Objects.cast;
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
public class TextWidget extends Figure {

	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		final Rectangle r = getBounds();
		TextPanel textPanel = cast(getParent());
		XCombo combo = textPanel.getXCombo();
		XComboPopup popup = combo.getPopup();
//		XComboModel model = combo.getModel();
		//
		{
			final String text1 = "select all";
			Dimension d1 = INSTANCE.getStringExtents(text1, g.getFont());
			g.drawString(text1, r.x + 8, r.y + (r.height - d1.height) / 2);
		}
		{
			g.setFont(FontAwesome.getFont(10));
			final String text2 = (popup == null) ? caret_down : caret_up;
			Dimension d1 = INSTANCE.getStringExtents(text2, g.getFont());
			g.drawString(text2, r.x + r.width - d1.width - 8, r.y + (r.height - d1.height) / 2);
		}
	}
}
